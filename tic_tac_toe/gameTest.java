import java.util.Arrays;
import java.util.Scanner;
import game.TicTacToe;

public class gameTest {
    public static void main(String[] args) {

        Scanner scr = new Scanner(System.in);
        String posRegex = "[12]";

        System.out.println("This program is a game of " +
                "Tic Tac Toe. Would you like to go " +
                "1st or 2nd?");
        int pos = scr.nextInt();

        while(!String.valueOf(pos).matches(posRegex)) {

            System.out.println("Enter either 1 or 2." +
                    " Would you like to go 1st or 2nd?");
            pos = scr.nextInt();
        }

        TicTacToe game = new TicTacToe(pos);

        int rowPos;
        int colPos;
        boolean keepPlaying = true;

        while (keepPlaying) {

            while (game.getGameStatus().equals("ongoing")) {

                game.displayBoard();
                System.out.print("\nEnter a move: ");
                rowPos = scr.nextInt();
                colPos = scr.nextInt();

                while (!game.validateUserMove(rowPos, colPos)
                        || !game.emptyCell(rowPos, colPos)) {
                    rowPos = scr.nextInt();
                    colPos = scr.nextInt();
                }

                game.setSquare(rowPos, colPos);
                game.checkGameStatus();

            }

            System.out.println();

            switch (game.getGameStatus()) {
                case "Draw":
                    System.out.println("Draw");
                    break;
                case "User wins":
                    System.out.println("User Wins");
                    break;
                case "Computer wins":
                    System.out.println("Computer Wins");
                    break;
                default:
                    System.out.println("Error");
            }

            game.displayPlayerStats();
            System.out.println("\nWould you like to continue playing? (Yes/No)");
            String answer = scr.next();
            while (!answer.equals("Yes") && !answer.equals("No")) {
                System.out.println("Please choose 'Yes' or 'No'.");
                answer = scr.next();
            }
            if (answer.equals("No")) {
                keepPlaying = false;
            } else {

                System.out.println("Do you want to go 1st or 2nd?");
                pos = scr.nextInt();

                while(!String.valueOf(pos).matches(posRegex)) {

                    System.out.println("Enter either 1 or 2." +
                            " Would you like to go 1st or 2nd?");
                    pos = scr.nextInt();
                }
                game.gameRest(pos);
            }
        }

        game.displayFinalStats();
    }



}