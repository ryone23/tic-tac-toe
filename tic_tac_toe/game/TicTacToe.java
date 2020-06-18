    package game;

    public class TicTacToe {

        private char userMark, computerMark;
        private String gameStatus;
        private Players[] player = new Players[2];
        Move playerMove;
        char[] playersMark = new char[] {'X','O'};
        private int playerTurn;
        char[][] gameBoard = new char[3][3];

        public TicTacToe(int pos) {
            pos--;
            if (pos == 0) {
                userMark = playersMark[0];
                computerMark = playersMark[1];
            } else {
                userMark = playersMark[1];
                computerMark = playersMark[0];
            }
            gameStatus = "ongoing";
            playerTurn = pos;
            player[0] = new Players("User", 0, 0, 0, userMark);
            player[1] = new Players("Computer", 0, 0, 0,  computerMark);
            resetBoard();

        }

        private void resetBoard () {

            gameBoard[0] = new char[]{' ', ' ', ' '};
            gameBoard[1] = new char[]{' ', ' ', ' '};
            gameBoard[2] = new char[]{' ', ' ', ' '};

        }

        public void displayBoard () {

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

        public boolean validateUserMove(int row, int col) {

            boolean validMove = true;
            if (row < 0 || row > 3 || col < 0 || col > 3) {
                validMove = false;
                System.out.println("Invalid move. Enter digits in " +
                        "in the range [0,2].");
            }
            return validMove;
        }

        public boolean emptyCell(int row, int col) {

            boolean empty = true;
            if (gameBoard[row][col] != ' ') {
                empty = false;
                System.out.println("This cell is occupied!" +
                        " Choose another one!");
            }
            return empty;
        }

        public void setSquare(int row, int col) {

            gameBoard[row][col] = playersMark[playerTurn];
            if (playerTurn == 0) {
                playerTurn = 1;
            } else {
                playerTurn = 0;
            }

        }

        public void checkGameStatus() {

            boolean isFilled = true;
            boolean userWins = false;
            boolean computerWins = false;
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
                        userWins = true;    // a playerTurn = 1 would signify user win
                    } else {
                        computerWins = true;
                    }
                }
            }

            // check for column victory
            for (int c = 0; c < 3; c++) {
                if (gameBoard[0][c] != ' ' && gameBoard[0][c] == gameBoard[1][c] &&
                        gameBoard[1][c] == gameBoard[2][c]) {
                    if (playerTurn == 1) {  // since playerTurn is changed after every
                        userWins = true;    // a playerTurn = 1 would signify user win
                    } else {
                        computerWins = true;
                    }
                }
            }

            // check for diagonal victory
            if (gameBoard[0][0] != ' ' && gameBoard[0][0] == gameBoard[1][1] &&
                gameBoard[1][1] == gameBoard[2][2]) {
                if (playerTurn == 1) {  // since playerTurn is changed after every
                    userWins = true;    // a playerTurn = 1 would signify user win
                } else {
                    computerWins = true;
                }
            }


            if (gameBoard[2][0] != ' ' && gameBoard[2][0] == gameBoard[1][1] &&
            gameBoard[1][1] == gameBoard[0][2]) {
                if (playerTurn == 1) {  // since playerTurn is changed after every
                    userWins = true;    // a playerTurn = 1 would signify user win
                } else {
                    computerWins = true;
                }
            }

            if (userWins) {
                player[0].wins++;
                player[1].losses++;
                gameStatus = "User wins";

            } else if (computerWins) {
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

        public void gameRest(int pos) {

            pos--;
            userMark = playersMark[pos];
            if (pos == 0) {
                computerMark = playersMark[1];
            } else {
                computerMark = playersMark[0];
            }
            gameStatus = "ongoing";
            playerTurn = pos;
            resetBoard();

        }

        public void displayPlayerStats() {

            System.out.printf("Name: %s\n" +
                    "Wins: %d\nLosses: %d\nDraws: %d\nCurrent Mark: %c\n",
                    player[0].name, player[0].wins, player[0].losses,
                    player[0].draws, player[0].playerMark);

        }

        public void displayFinalStats() {

            System.out.println("\nFinal Stats");
            String stats = String.format("Wins: %d\nLosses: %d\nDraws: %d\n",
                    player[0].wins, player[0].losses,
                    player[0].draws);
            System.out.println(stats);
            System.out.println("Thanks for playing!");

        }
        public String getGameStatus() {
            return gameStatus;
        }


    }

    class Move {
        int row;
        int col;


    }

    class Players {
        String name;
        int wins;
        int losses;
        int draws;
        char playerMark;

        public Players(String name, int wins, int losses, int draws, char playerMark) {
            this.name = name;
            this.wins = wins;
            this.losses = losses;
            this.draws = draws;
            this.playerMark = playerMark;
        }

    }