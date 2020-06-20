    package game;

    import java.util.Scanner;
    import java.util.Random;

    public class TicTacToe {

        Scanner scr = new Scanner(System.in);
        private char userMark, computerMark;
        private int gameType;
        private String gameStatus;
        private Players player1, player2;
        private Players.ComputerAI computer1, computer2;
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
                        "Please select an option: ");
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
            gameStatus = "ongoing";
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
            System.out.print("\nEnter the name of Player1: ");
            String p1 = scr.next();
            System.out.print("\nEnter the name of Player2: ");
            String p2 = scr.next();

            Random rand = new Random();

            int rand_int = rand.nextInt(2);

            if (rand_int == 0) {
                System.out.println(p1 + " will go first and " +
                        "be X's." + p2 + " will be O's");
                player1 = new Players(p1, playersMark[0]);
                player2 = new Players(p2, playersMark[1]);
                playerTurn = 0;
            } else {
                System.out.println(p2 + " will go first and " +
                        "be X's." + p1 + " will be O's");
                player1 = new Players(p1, playersMark[1]);
                player2 = new Players(p2, playersMark[0]);
                playerTurn = 1;
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
                player1 = new Players("Player", playersMark[0]);
                computer1 = new Players.ComputerAI("Computer", playersMark[1]);

            } else {
                player1 = new Players("Player", playersMark[1]);
                computer1 = new Players.ComputerAI("Computer", playersMark[0]);
            }
            gameStatus = "ongoing";
            playerTurn = pos;

        }

        private void initCvC() {

            computer1 = new Players.ComputerAI("Computer1", playersMark[0]);
            computer2 = new Players.ComputerAI("Computer2", playersMark[1]);
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

                        while (gameStatus.equals("ongoing")) {
                            displayBoard();
                            player1.getPlayerMove(gameStatus, gameBoard, scr);
                            setSquare(player1);
                            gameStatus = checkGameStatus();

                            displayBoard();
                            player2.getPlayerMove(gameStatus, gameBoard, scr);
                            setSquare(player2);
                            gameStatus = checkGameStatus();
                        }
                    }
                    displayBoard();
                    System.out.println();
                    displayOutcome();
                    postGameMenu();

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

        public void setSquare(Players currentPlayer) {

            int row = currentPlayer.move.row;
            int col = currentPlayer.move.col;
            gameBoard[row][col] = currentPlayer.currentMark;
            if (playerTurn == 0) {
                playerTurn = 1;
            } else {
                playerTurn = 0;
            }

        }

        public String checkGameStatus() {

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
                        return "P1";    // a playerTurn = 1 would signify P1 win and
                    } else {            // P turn = 2 would signify P2 win regardless if
                        return "P2";    // computer or actual player
                    }
                }
            }

            // check for column victory
            for (int c = 0; c < 3; c++) {
                if (gameBoard[0][c] != ' ' && gameBoard[0][c] == gameBoard[1][c] &&
                        gameBoard[1][c] == gameBoard[2][c]) {
                    if (playerTurn == 1) {  // since playerTurn is changed after every
                        return "P1";    // a playerTurn = 1 would signify P1 win and
                    } else {            // P turn = 2 would signify P2 win regardless if
                        return "P2";    // computer or actual player
                    }
                }
            }

            // check for diagonal victory
            if (gameBoard[0][0] != ' ' && gameBoard[0][0] == gameBoard[1][1] &&
                gameBoard[1][1] == gameBoard[2][2]) {
                if (playerTurn == 1) {  // since playerTurn is changed after every
                    return "P1";    // a playerTurn = 1 would signify P1 win and
                } else {            // P turn = 2 would signify P2 win regardless if
                    return "P2";    // computer or actual player
                }
            }


            if (gameBoard[2][0] != ' ' && gameBoard[2][0] == gameBoard[1][1] &&
            gameBoard[1][1] == gameBoard[0][2]) {
                if (playerTurn == 1) {  // since playerTurn is changed after every
                    return "P1";    // a playerTurn = 1 would signify P1 win and
                } else {            // P turn = 2 would signify P2 win regardless if
                    return "P2";    // computer or actual player
                }
            }

            if (isFilled) {
                return "Draw";
            } else {
                return "ongoing";
            }

        }

        private void displayOutcome() {
            /*
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

             */
        }

        private void postGameMenu() {

        }

        private void gameReset() {

            resetBoard();

        }

        public void callPlayerStats(int playerNum) {

            //player[playerNum].displayPlayerStats();

        }


    }

