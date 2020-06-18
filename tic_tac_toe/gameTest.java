
import java.util.Arrays;
import java.util.Scanner;

public class gameTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] cellArray = new char[3][3];
        cellArray[0] = new char[]{' ', ' ', ' '};
        cellArray[1] = new char[]{' ', ' ', ' '};
        cellArray[2] = new char[]{' ', ' ', ' '};

        String status;


        displayBoard(cellArray);
        status = gameStatus(cellArray);
        int[] cords = new int[]{0, 0};
        int rowPos = cords[0];
        int colPos = cords[1];

        while (status == "ongoing") {

            getUserMove(cellArray, scanner, cords);
            rowPos = cords[0];
            colPos = cords[1];

            while (cellArray[rowPos][colPos] != ' ') {
                System.out.println("This cell is occupied!" +
                        " Choose another one!");
                getUserMove(cellArray, scanner, cords);

                rowPos = cords[0];
                colPos = cords[1];
            }

            cellArray[rowPos][colPos] = 'X';

            displayBoard(cellArray);
            status = gameStatus(cellArray);

            if (status.equals("Game not finished")) {

                getUserMove(cellArray, scanner, cords);

                rowPos = cords[0];
                colPos = cords[1];
                while (cellArray[rowPos][colPos] != ' ') {
                    System.out.println("This cell is occupied!" +
                            " Choose another one!");
                    getUserMove(cellArray, scanner, cords);

                    rowPos = cords[0];
                    colPos = cords[1];
                }

                cellArray[rowPos][colPos] = 'O';

                displayBoard(cellArray);
                status = gameStatus(cellArray);
            } else {
                break;
            }

        }

        switch (status) {
            case "Draw":
                System.out.println("Draw");
                break;
            case "Xwins":
                System.out.println("X wins");
                break;
            case "Owins":
                System.out.println("O wins");
                break;
            default:
                System.out.println("Game not finished");
        }




    }

    public static void getUserMove(char[][] cellArray, Scanner scn, int[] cords) {
        int xCord = 0;
        int yCord = 0;
        do {
            System.out.print("Enter the coordinates: ");
            while (!scn.hasNextInt()) {
                System.out.println("You should enter numbers!");
                scn.next();
                System.out.print("Enter the coordinates: ");
            }
            xCord = scn.nextInt();
            while (!scn.hasNextInt()) {
                System.out.println("You should enter numbers!");
                scn.next();
                System.out.print("Enter the coordinates: ");
            }
            yCord = scn.nextInt();

            if ((xCord < 1) || (xCord > 3) || (yCord < 1) || (yCord > 3)) {
                System.out.println("Coordinates should be from 1 to 3!");
            }

        } while ((xCord < 1) || (xCord > 3) || (yCord < 1) || (yCord > 3));

        if (xCord == 1 && yCord == 1) {
            cords[0] = 2;
            cords[1] = 0;
        } else if (xCord == 1 && yCord == 2) {
            cords[0] = 1;
            cords[1] = 0;
        } else if (xCord == 1 && yCord == 3) {
            cords[0] = 0;
            cords[1] = 0;
        } else if (xCord == 2 && yCord == 1) {
            cords[0] = 2;
            cords[1] = 1;
        } else if (xCord == 2 && yCord == 2) {
            cords[0] = 1;
            cords[1] = 1;
        } else if (xCord == 2 && yCord == 3) {
            cords[0] = 0;
            cords[1] = 1;
        } else if (xCord == 3 && yCord == 1) {
            cords[0] = 2;
            cords[1] = 2;
        } else if (xCord == 3 && yCord == 2) {
            cords[0] = 1;
            cords[1] = 2;
        } else if (xCord == 3 && yCord == 3) {
            cords[0] = 0;
            cords[1] = 2;
        }

    }

    public static void displayBoard(char[][] cellArray) {

        String border = "---------";
        System.out.println(border);
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(cellArray[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println(border);

    }

    public static String gameStatus(char[][] cellArray) {
        Boolean isFilled = false;
        int boardCount = 0;
        for (int i = 0; (i < 3); i++) {
            for (int j = 0; j < 3; j++) {
                if (cellArray[i][j]  != ' ') {
                    boardCount++;
                }
            }
        }
        if (boardCount == 9) {
            isFilled = true;
        }
        Boolean impossible = false;
        int totalX = 0;
        int totalO = 0;

        for (int i = 0; (i < 3); i++) {
            for (int j = 0; j < 3; j++) {
                if (cellArray[i][j] == 'X') {
                    totalX++;
                } else if (cellArray[i][j] == 'O') {
                    totalO++;
                }
            }
        }
        int diff = totalX - totalO;

        if (Math.abs(diff) > 1) {
            impossible = true;
        }

        Boolean xWins = false;
        Boolean oWins = false;
        char[] xRow = {'X', 'X', 'X'};
        char[] oRow = {'O', 'O', 'O'};

        for (int i = 0; i < 3; i++) {
            if (Arrays.equals(cellArray[i], xRow)) {
                xWins = true;
            }
            if (Arrays.equals(cellArray[i], oRow)) {
                oWins = true;
            }
        }

        int xCount = 0;
        int oCount = 0;

        for (int i = 0; i < 3; i++) {
            if (cellArray[i][0] == 'X') {
                xCount++;
            } else if (cellArray[i][0] == 'O') {
                oCount++;
            }
        }
        if (xCount == 3) {
            xWins = true;
        } else if (oCount == 3) {
            oWins = true;
        }
        xCount = 0;
        oCount = 0;

        for (int i = 0; i < 3; i++) {
            if (cellArray[i][1] == 'X') {
                xCount++;
            } else if (cellArray[i][1] == 'O') {
                oCount++;
            }
        }
        if (xCount == 3) {
            xWins = true;
        } else if (oCount == 3) {
            oWins = true;
        }
        xCount = 0;
        oCount = 0;

        for (int i = 0; i < 3; i++) {
            if (cellArray[i][2] == 'X') {
                xCount++;
            } else if (cellArray[i][2] == 'O') {
                oCount++;
            }
        }
        if (xCount == 3) {
            xWins = true;
        } else if (oCount == 3) {
            oWins = true;
        }

        xCount = 0;
        oCount = 0;

        for (int i = 0; i < 3; i++) {
            if (cellArray[i][i] == 'X') {
                xCount++;
            } else if (cellArray[i][i] == 'O') {
                oCount++;
            }
        }

        if (xCount == 3) {
            xWins = true;
        } else if (oCount == 3) {
            oWins = true;
        }

        xCount = 0;
        oCount = 0;

        for (int i = 2; i > -1; i--) {
            if (cellArray[i][3 - i - 1] == 'X') {
                xCount++;
            } else if (cellArray[i][3 - i - 1] == 'O') {
                oCount++;
            }
        }

        if (xCount == 3) {
            xWins = true;
        } else if (oCount == 3) {
            oWins = true;
        }
        
        if (xWins) {
            return "Xwins";
        } else if (oWins) {
            return "Owins";
        } else if (isFilled) {
            return "Draw";
        } else {
            return "Game not finished";
        }

    }

}