package cinema;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int givenNumberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int givenNumberOfSeatsInRow = scanner.nextInt();
        int chosenRowNumber = 0;
        int chosenSeatNumber = 0;
        int purchasedTickets = 0;
        int currentIncome = 0;
        String[][] cinema = makeCinema(givenNumberOfRows, givenNumberOfSeatsInRow);
        exitLoop:
        while(true) {
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            switch (choice) {
                case 1: {
                    printCinemaScheme(cinema);
                    break;
                }
                case 2: {
                    do {
                        do {
                            System.out.println("Enter a row number:");
                            chosenRowNumber = scanner.nextInt();
                            if (chosenRowNumber > givenNumberOfRows) {
                                System.out.println("Wrong input!");
                            }
                        } while ( chosenRowNumber > givenNumberOfRows);
                        
                        System.out.println("Enter a seat number in that row:");
                        chosenSeatNumber = scanner.nextInt() ;
                        if (cinema[chosenRowNumber][chosenSeatNumber] == "B") {
                        System.out.println("That ticket has already been purchased!");
                        } else {
                        int chosenSeatTicketPrice = getTicketPrice(cinema, chosenRowNumber);
                        cinema[chosenRowNumber][chosenSeatNumber] = "B";
                        //printCinemaScheme(cinema);
                        System.out.printf("Ticket price: $%d \n", chosenSeatTicketPrice);
                        currentIncome += chosenSeatTicketPrice;
                        purchasedTickets++;
                        break;
                        }
                    } while (cinema[chosenRowNumber][chosenSeatNumber] == "B");
                    
                    break;
                }
                case 3: {
                    System.out.println("Number of purchased tickets: " + purchasedTickets);
                    float percentage = (((float) purchasedTickets/(givenNumberOfRows * givenNumberOfSeatsInRow)) * 100);
                    String percentages = String.format("%.2f", percentage);
                    System.out.println("\nPercentage: " + percentages + "%");
                    System.out.println("Current income: $" + currentIncome);
                    System.out.println("Total income: $" + getIncome(givenNumberOfRows, givenNumberOfSeatsInRow));
                    break;
                }
                case 0: {
                    // cinema[chosenRowNumber][chosenSeatNumber] = "B";
                    // printCinemaScheme(cinema);
                    break exitLoop;
                }
            }   
            
        }
        
        // [Row Number] [Seat Number]
       
        // printCinemaScheme(cinema);
    }

    private static String[][] makeCinema(int numberOfRows, int numberOfSeatsInRow) {
        String[][] cinemaScheme = new String[numberOfRows + 1][numberOfSeatsInRow + 1];

            //puts "S" in whole table
            for(int i = 0; i <= numberOfRows; i++){
                for(int j = 0; j <= numberOfSeatsInRow; j++){
                    cinemaScheme[i][j] = "S";
                }
            }
            //number of seats label
            for(int k = 0; k <= numberOfSeatsInRow; k++ ){
                if(k == 0){
                    cinemaScheme[0][k] = " ";
                } else{
                    cinemaScheme[0][k] = String.valueOf(k);
                }
            }

            //number of rows label
            for(int m = 0; m <= numberOfRows; m++ ){
                if(m == 0){
                    cinemaScheme[m][0] = " ";
                } else{
                    cinemaScheme[m][0] = String.valueOf(m);
                }
            }

        //System.out.println(Arrays.deepToString(cinemaScheme));
        return cinemaScheme;
        }

    //returs profit of when whole cinema seats are taken
    private static int getIncome(int numberOfRows, int numberOfSeatsInRow){
       int numberOfSeats = numberOfRows * numberOfSeatsInRow;
       int firstHalfOfSeats = numberOfRows / 2 * numberOfSeatsInRow;
       int seconHalfOfSeats = numberOfSeats - firstHalfOfSeats;
       int profit;
        if(numberOfSeats < 60){
          profit = numberOfSeats * 10;
        }
        else {
            profit = firstHalfOfSeats * 10 + seconHalfOfSeats * 8;
        }
        return profit;
    }

    private static void printCinemaScheme(String [][] cinema){
        System.out.println("Cinema:");
        for(int n = 0; n < cinema.length; n++){
            for(int m = 0; m < cinema[n].length; m++){
                System.out.print(cinema[n][m] + " ");
            }
            System.out.println();
        }
    }

    private static int getTicketPrice(String[][] cinema, int rowNumber){
        int numberOfRows = Integer.parseInt(cinema[cinema.length - 1][0]);
        int i = cinema[0].length - 1;
        int numberOfSeats = Integer.parseInt(cinema[0][i]);
        int numberOfSeatsInCinema = numberOfRows * numberOfSeats;
        int ticketPrice = 0;
        if(numberOfSeatsInCinema < 60){
            ticketPrice = 10;
        } else if (rowNumber <= numberOfRows / 2){
            ticketPrice = 10;
        } else {
            ticketPrice = 8;
        }

        return ticketPrice;
    }


}
