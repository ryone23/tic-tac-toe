    package game;

    import java.util.Scanner;
    import java.util.Random;

    public class TicTacToe {

        Scanner scr = new Scanner(System.in);
        private char userMark, computerMark;
        private int gameType;
        private String gameStatus;
        Move plyMove;
        private Players[] player = new Players[2];
        char[] playersMark = new char[] {'X','O'};
        private int playerTurn;
        char[][] gameBoard = new char[3][3];

        public TicTacToe() {


        }

        public void gameMenu () {

            System.out.println("Welcome to Tic Tac Toe!");
            System.out.println();
            System.out.println("------------------------");
            System.out.println("       Game Types       ");
            System.out.println("------------------------");
            System.out.println("1. Player vs. Player");
            System.out.println("2. Player vs. Computer");
            System.out.println("3. Computer vs. Computer");
            System.out.println();
            System.out.print("Please select an option: ");

        }

        public void setUpGame () {

            gameMenu();
            gameType = scr.nextInt();
            while(!validGameOption(gameType)) {

                System.out.print("Enter either 1, 2, or 3.\n" +
                        "Please select an option");
                gameType = scr.nextInt();
                validGameOption(gameType);
            }

            switch (gameType) {
                case 1:
                    initPvP();
                    break;

                case 2:
                    initPvC();
                    break;

                case 3:
                    initCvC();
                    break;

                default:
                    break;

            }

            resetBoard();
            gameStart();
        }

        boolean validGameOption (int option) {

            boolean valid = false;
            String optionRegex = "[123]";

            if (String.valueOf(option).matches(optionRegex)) {
                valid = true;
            }

            return valid;

        }

        private void initPvP() {

            String prompt = "Player vs. Player chosen.";
            System.out.println(prompt);
            System.out.print("Enter the name of Player1: ");
            String player1 = scr.next();
            System.out.print("\nEnter the name of Player2: ");
            String player2 = scr.next();

            Random rand = new Random();

            int rand_int = rand.nextInt(1);

            if (rand_int == 0) {
                System.out.println(player1 + " will go first and " +
                        "be X's." + player2 + " will be O's");
                player[0] = new Players(player1, playersMark[0]);
                player[1] = new Players(player2, playersMark[1]);
            } else {
                System.out.println(player2 + " will go first and " +
                        "be X's." + player1 + " will be O's");
                player[0] = new Players(player1, playersMark[1]);
                player[1] = new Players(player2, playersMark[0]);
            }

        }

        private void initPvC() {

            String prompt = "Player vs. Computer chosen.\n" +
                    "Would you like to go 1st or 2nd?";
            System.out.println(prompt);
            String posRegex = "[12]";

            int pos = scr.nextInt();

            while(!String.valueOf(pos).matches(posRegex)) {

                System.out.print("Invalid input. Please choose '1' or '2': ");
                pos = scr.nextInt();

            }

            pos--;
            if (pos == 0) {
                player[0] = new Players("Player", playersMark[0]);
                player[1] = new Players("Computer", playersMark[1]);

            } else {
                player[0] = new Players("Player", playersMark[1]);
                player[1] = new Players("Computer", playersMark[0]);
            }
            gameStatus = "ongoing";
            playerTurn = pos;

        }

        private void initCvC() {

            player[0] = new Players("Computer1", playersMark[0]);
            player[1] = new Players("Computer2", playersMark[1]);
            gameStatus = "ongoing";
        }

        private void resetBoard () {

            gameBoard[0] = new char[]{' ', ' ', ' '};
            gameBoard[1] = new char[]{' ', ' ', ' '};
            gameBoard[2] = new char[]{' ', ' ', ' '};

        }

        private void gameStart() {

            boolean keepPlaying = true;

            switch (gameType) {
                case 1:
                    while (keepPlaying) {

                        while (getGameStatus().equals("ongoing")) {
                            displayBoard();
                            System.out.print("\nEnter a move: ");

                            plyMove.row = scr.nextInt();
                            plyMove.col = scr.nextInt();

                            while (!validateUserMove() || !emptyCell()) {
                               plyMove.row = scr.nextInt();
                               plyMove.col = scr.nextInt();
                            }

                            setSquare();
                            checkGameStatus();
                        }
                    }
                    displayBoard();
                    System.out.println();
                    displayOutcome();

                    break;

                case 2:

                    break;

                case 3:

                    break;

                default:
                    break;


            }

        }

        private void displayBoard () {

            String border = "---------";
            System.out.println(border);
            for (int i = 0; i < 3; i++) {
                System.out.print("| ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(gameBoard[i][j] + " ");
                }
                System.out.println("|");
            }
            System.out.println(border);

        }

        private boolean validateUserMove() {

            boolean validMove = true;

            if (plyMove.row < 0 || plyMove.row > 3 || plyMove.col < 0 || plyMove.col > 3) {
                validMove = false;
                System.out.println("Invalid move. Enter digits in " +
                        "in the range [0,2].");
            }
            return validMove;
        }

        private boolean emptyCell() {

            boolean empty = true;
            if (gameBoard[plyMove.row][plyMove.col] != ' ') {
                empty = false;
                System.out.println("This cell is occupied!" +
                        " Choose another one!");
            }
            return empty;
        }

        public void setSquare() {

            player[playerTurn].playerMove(plyMove.row, plyMove.col, gameBoard);
            if (playerTurn == 0) {
                playerTurn = 1;
            } else {
                playerTurn = 0;
            }

        }

        public void checkGameStatus() {

            boolean isFilled = true;
            int boardCount = 0;
            for (int i = 0; (i < 3); i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameBoard[i][j]  != ' ') {
                        boardCount++;
                    }
                }
            }
            if (boardCount != 9) {
                isFilled = false;
            }

            // check for row victory
            for (int r = 0; r < 3; r++) {
                if (gameBoard[r][0] != ' ' && gameBoard[r][0] == gameBoard[r][1] &&
                    gameBoard[r][1] == gameBoard[r][2]) {
                    if (playerTurn == 1) {  // since playerTurn is changed after every
                        player[0].winner = true;    // a playerTurn = 1 would signify user win
                    } else {
                        player[1].winner = true;
                    }
                }
            }

            // check for column victory
            for (int c = 0; c < 3; c++) {
                if (gameBoard[0][c] != ' ' && gameBoard[0][c] == gameBoard[1][c] &&
                        gameBoard[1][c] == gameBoard[2][c]) {
                    if (playerTurn == 1) {  // since playerTurn is changed after every
                        player[0].winner = true;    // a playerTurn = 1 would signify user win
                    } else {
                        player[1].winner = true;
                    }
                }
            }

            // check for diagonal victory
            if (gameBoard[0][0] != ' ' && gameBoard[0][0] == gameBoard[1][1] &&
                gameBoard[1][1] == gameBoard[2][2]) {
                if (playerTurn == 1) {  // since playerTurn is changed after every
                    player[0].winner = true;    // a playerTurn = 1 would signify user win
                } else {
                    player[1].winner = true;
                }
            }


            if (gameBoard[2][0] != ' ' && gameBoard[2][0] == gameBoard[1][1] &&
            gameBoard[1][1] == gameBoard[0][2]) {
                if (playerTurn == 1) {  // since playerTurn is changed after every
                    player[0].winner = true;    // a playerTurn = 1 would signify user win
                } else {
                    player[1].winner = true;
                }
            }

            if (player[0].winner) {
                player[0].wins++;
                player[1].losses++;
                gameStatus = "User wins";

            } else if (player[1].winner) {
                player[0].losses++;
                player[1].wins++;
                gameStatus = "Computer wins";

            } else if (isFilled) {
                player[0].draws++;
                player[1].draws++;
                gameStatus = "Draw";

            } else {
                gameStatus = "ongoing";
            }


        }

        private void displayOutcome() {

            switch(gameStatus) {
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
        }

        private void gameReset() {

            resetBoard();

        }

        public void callPlayerStats(int playerNum) {

            player[playerNum].displayPlayerStats();

        }

        public void callFinalStats() {

            player[0].displayFinalStats();
            player[1].displayFinalStats();

        }

        public String getGameStatus() {
            return gameStatus;
        }


    }

    class Move {
        int row;
        int col;
    }
