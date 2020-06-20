import java.util.Arrays;
import game.TicTacToe;

public class gameTest {
    public static void main(String[] args) {

        TicTacToe game = new TicTacToe();
        game.setUpGame();
        /*
            game.callPlayerStats(0);
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
                game.gameReset(pos);
            }
        }

        game.callFinalStats();

         */
    }



}