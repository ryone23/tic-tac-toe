    package game;

    import java.util.Scanner;
    import java.util.Random;

    public class TicTacToe {

        Scanner scr = new Scanner(System.in);
        Random rand = new Random();
        private char userMark, computerMark;
        int rand_int;
        private int gameType;
        private String gameStatus;
        private Players player1, player2;
        private Players.ComputerAI computer1, computer2;
        char[] playersMark = new char[] {'X','O'};
        private int pNum;
        char[][] gameBoard = new char[3][3];
        private Players[] currentPlayer = new Players[2];

        public TicTacToe() {
                player1 = new Players();
                player2 = new Players();
                computer1 = new Players.ComputerAI();
                computer2 = new Players.ComputerAI();

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
                    System.out.println("Player vs. Player chosen.");
                    System.out.print("\nEnter the name of Player1: ");
                    String p1 = scr.next();
                    System.out.print("\nEnter the name of Player2: ");
                    String p2 = scr.next();

                    player1.name = p1;
                    player2.name = p2;

                    initPvP();
                    break;

                case 2:

                    System.out.print("Player vs. Computer chosen.\n" +
                            "Enter the name of Player1: ");
                    p1 = scr.next();

                    player1.name = p1;
                    computer1.name = "Computer";

                    initPvC();
                    break;

                case 3:

                    System.out.println("Computer vs. Computer chosen.");

                    Random rand = new Random();

                    computer1.name = "Elon Musk";
                    computer2.name = "Bill Gates";

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

            rand_int = rand.nextInt(2);

            if (rand_int == 0) {
                System.out.println(player1.name + " will go first and " +
                        "be X's. " + player2.name + " will be O's");
                player1.currentMark = playersMark[0];
                player2.currentMark = playersMark[1];
                currentPlayer[0]= player1;
                currentPlayer[1] = player2;

            } else {
                System.out.println(player2.name + " will go first and " +
                        "be X's. " + player1.name + " will be O's");
                player1.currentMark = playersMark[1];
                player2.currentMark = playersMark[0];
                currentPlayer[1]= player1;
                currentPlayer[0] = player2;

            }

        }

        private void initPvC() {


            String prompt = "Would you like to go 1st or 2nd?";
            System.out.println(prompt);
            String posRegex = "[12]";

            int pos = scr.nextInt();

            while(!String.valueOf(pos).matches(posRegex)) {

                System.out.print("Invalid input. Please choose '1' or '2': ");
                pos = scr.nextInt();

            }

            pos--;
            if (pos == 0) {
                player1.currentMark = playersMark[0];
                computer1.currentMark = playersMark[1];
                currentPlayer[0]= player1;
                currentPlayer[1] = computer1;

            } else {
                player1.currentMark = playersMark[1];
                computer1.currentMark = playersMark[0];
                currentPlayer[1]= player1;
                currentPlayer[0] = computer1;
            }
            gameStatus = "ongoing";

        }

        private void initCvC() {

            rand_int = rand.nextInt(2);

            if (rand_int == 0) {
                System.out.println(computer1.name + " will go first and " +
                        "be X's. " + computer2.name + " will be O's");
                computer1.currentMark = playersMark[0];
                computer2.currentMark = playersMark[1];
                currentPlayer[0]= computer1;
                currentPlayer[1] = computer2;

            } else {
                System.out.println(computer2.name + " will go first and " +
                        "be X's. " + computer1.name + " will be O's");
                computer1.currentMark = playersMark[1];
                computer2.currentMark = playersMark[0];
                currentPlayer[1]= computer1;
                currentPlayer[0] = computer2;

            }
        }

        private void resetBoard () {

            gameBoard[0] = new char[]{' ', ' ', ' '};
            gameBoard[1] = new char[]{' ', ' ', ' '};
            gameBoard[2] = new char[]{' ', ' ', ' '};

        }

        private void gameStart() {

            while (gameStatus.equals("ongoing")) {

                displayBoard();
                pNum = 0;
                currentPlayer[0].getMove(gameStatus, gameBoard, scr);
                setSquare();
                gameStatus = checkGameStatus();

                if (gameStatus.equals("ongoing")) {
                    displayBoard();
                    pNum = 1;
                    currentPlayer[1].getMove(gameStatus, gameBoard, scr);
                    setSquare();
                    gameStatus = checkGameStatus();
                }
            }
            displayBoard();
            System.out.println();
            displayOutcome();
            resetBoard();
            System.out.println("---Game Over---");
            postGameMenu();
            getPostGameChoice();

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

        public void setSquare() {

            int row = currentPlayer[pNum].move.row;
            int col = currentPlayer[pNum].move.col;
            gameBoard[row][col] = currentPlayer[pNum].currentMark;

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
                    return "Winner";
                }
            }

            // check for column victory
            for (int c = 0; c < 3; c++) {
                if (gameBoard[0][c] != ' ' && gameBoard[0][c] == gameBoard[1][c] &&
                        gameBoard[1][c] == gameBoard[2][c]) {
                    return "Winner";
                }
            }

            // check for diagonal victory
            if (gameBoard[0][0] != ' ' && gameBoard[0][0] == gameBoard[1][1] &&
                gameBoard[1][1] == gameBoard[2][2]) {
                return "Winner";
            }


            if (gameBoard[2][0] != ' ' && gameBoard[2][0] == gameBoard[1][1] &&
            gameBoard[1][1] == gameBoard[0][2]) {
                return "Winner";
            }

            if (isFilled) {
                return "Draw";
            } else {
                return "ongoing";
            }

        }

        private void displayOutcome() {

            switch(gameStatus) {
                case "Draw":
                    System.out.println("Draw");
                    currentPlayer[0].draws++;
                    currentPlayer[1].draws++;
                    break;

                case "Winner":
                    System.out.println(currentPlayer[pNum].name + " Wins!");
                    currentPlayer[pNum].wins++;
                    if (pNum == 0) {
                        currentPlayer[1].losses++;
                    } else {
                        currentPlayer[0].losses++;
                    }
                    break;

                default:
                    System.out.println("Error");

            }


        }

        private void postGameMenu() {
            System.out.println();
            System.out.println("Options");
            System.out.println("1. New Game");
            System.out.println("2. Change Game Type");
            System.out.println("3. Display Stats for Current Game Type");
            System.out.println("4. Exit");
            System.out.println();
            System.out.print("Please select an option: ");
        }

        private void getPostGameChoice() {
            int postGameChoice = scr.nextInt();
            String postGCRegex = "[1234]";

            while(!String.valueOf(postGameChoice).matches(postGCRegex)) {
                System.out.print("Enter either 1, 2, 3, or 4.\n" +
                        "Please select an option: ");
                postGameChoice = scr.nextInt();
            }

            switch (postGameChoice) {
                case 1:
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
                            System.out.println("Error");
                            break;

                    }

                    gameStart();
                    break;

                case 2:
                    System.out.println("------------------------");
                    System.out.println("       Game Types       ");
                    System.out.println("------------------------");
                    System.out.println("1. Player vs. Player");
                    System.out.println("2. Player vs. Computer");
                    System.out.println("3. Computer vs. Computer");
                    System.out.println();
                    System.out.print("Please select an option: ");
                    setUpGame();
                    break;

                case 3:

                    currentPlayer[0].displayPlayerStats();
                    currentPlayer[1].displayPlayerStats();
                    postGameMenu();
                    getPostGameChoice();
                    break;

                case 4:
                    System.out.println("\nThank You For Playing!");
                    break;

                default:
                    System.out.println("Error");
                    break;

            }
        }


    }

