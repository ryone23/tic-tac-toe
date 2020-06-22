    package game;

    import java.util.Scanner;

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


        void getMove(String status, char[][] gameBoard, Scanner scr) {

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

            public ComputerAI() {super(); isComputer = true; }

            void getMove(String status, char[][] gameBoard) {
                System.out.println(name + "'s Turn");

                move.findBestMove();
            }

        }

    }