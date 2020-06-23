    package game;

    import java.util.Scanner;
    import java.lang.Math;

    class Players {
        String name;
        int wins;
        int losses;
        int draws;
        char currentMark;
        boolean winner;
        boolean isComputer = false;
        Move move = new Move();

        public Players() {
            wins = 0;
            losses = 0;
            draws = 0;
            winner = false;
        }


        void getMove(String status, char[][] gameBoard) {

            Scanner scr = new Scanner(System.in);

            System.out.println("\n" + name + "'s Turn");
            System.out.print("Enter a move: ");

            move.row = scr.nextInt();
            move.col = scr.nextInt();

            while (!move.validateMove(gameBoard)) {
                move.row = scr.nextInt();
                move.col = scr.nextInt();
            }
        }

        void displayPlayerStats() {

            System.out.printf("\nName: %s\n" +
                            "Wins: %d\nLosses: %d\nDraws: %d\nCurrent Mark: %c\n",
                    name, wins, losses, draws, currentMark);

        }


        static class ComputerAI extends Players {

            char opponentMark;

            public ComputerAI() { super();}

            void getMove(String status, char[][] gameBoard) {
                System.out.println("\n" + name + "'s Turn");

                if (currentMark == 'X') {
                    opponentMark = 'O';
                } else {
                    opponentMark = 'X';
                }

                int bestValue = -1000;
                int moveValue;

                move.row = -1;
                move.col = -1;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (gameBoard[i][j] == ' ') {

                            gameBoard[i][j] = currentMark;

                            moveValue = minimax(gameBoard, 2, false, Integer.MIN_VALUE, Integer.MAX_VALUE);

                            gameBoard[i][j] = ' ';

                            if (moveValue > bestValue) {
                                move.row = i;
                                move.col = j;
                                bestValue = moveValue;
                            }
                        }
                    }
                }

            }

            int minimax(char[][] gameBoard, int depth, boolean isMax, int alpha, int beta) {

                int score;

                if (boardFilled(gameBoard) || depth == 0) {
                    score = evaluate(gameBoard);

                } else {

                    if (isMax) {


                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (gameBoard[i][j] == ' ') {

                                    gameBoard[i][j] = currentMark;

                                    score = minimax(gameBoard, depth, false, alpha, beta);

                                    if (score > alpha) {
                                        alpha = score;
                                    }

                                    gameBoard[i][j] = ' ';

                                    if (alpha >= beta) {
                                        break;
                                    }

                                }
                            }
                        }
                        return alpha;

                    } else {

                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (gameBoard[i][j] == ' ') {

                                    gameBoard[i][j] = opponentMark;

                                    score = minimax(gameBoard, depth, true, alpha, beta);

                                    if (score < beta) {
                                        beta = score;
                                    }

                                    gameBoard[i][j] = ' ';

                                    if (alpha >= beta) {
                                        break;
                                    }

                                }
                            }
                        }
                        return beta;

                    }
                }

                return score;
            }

            int evaluate(char[][] gameBoard) {
                int score = 0;

                score += evaluateLine(gameBoard,0, 0, 0, 1, 0, 2);  // row 0
                score += evaluateLine(gameBoard,1, 0, 1, 1, 1, 2);  // row 1
                score += evaluateLine(gameBoard,2, 0, 2, 1, 2, 2);  // row 2
                score += evaluateLine(gameBoard,0, 0, 1, 0, 2, 0);  // col 0
                score += evaluateLine(gameBoard,0, 1, 1, 1, 2, 1);  // col 1
                score += evaluateLine(gameBoard,0, 2, 1, 2, 2, 2);  // col 2
                score += evaluateLine(gameBoard,0, 0, 1, 1, 2, 2);  // diagonal
                score += evaluateLine(gameBoard,0, 2, 1, 1, 2, 0);  // alternate diagonal
                return score;
            }

            private int evaluateLine(char[][] gameBoard,int r1, int c1, int r2, int c2, int r3, int c3) {
                int score = 0;

                if (gameBoard[r1][c1] == currentMark) {
                    score = 1;
                } else if (gameBoard[r1][c1] == opponentMark) {
                    score = -1;
                }

                if (gameBoard[r2][c2] == currentMark) {
                    if (score == 1) {
                        score = 10;
                    } else if (score == -1) {
                        return 0;
                    } else {
                        score = 1;
                    }
                } else if (gameBoard[r2][c2] == opponentMark) {
                    if (score == -1) {
                        score = -10;
                    } else if (score == 1) {
                        return 0;
                    } else {
                        score = -1;
                    }
                }

                if (gameBoard[r3][c3] == currentMark) {
                    if (score > 0) {
                        score *= 10;
                    } else if (score < 0) {
                        return 0;
                    } else {
                        score = 1;
                    }

                } else if (gameBoard[r3][c3] == opponentMark) {
                    if (score < 0) {
                        score *= 10;
                    } else if (score > 0) {
                        return 0;
                    } else {
                        score = -1;
                    }
                }

                return score;
            }

            boolean boardFilled(char[][] gameBoard) {

                for (int i = 0; (i < 3); i++) {
                    for (int j = 0; j < 3; j++) {
                        if (gameBoard[i][j] != ' ') {
                            return true;
                        }
                    }
                }
                return false;

            }

        }
    }