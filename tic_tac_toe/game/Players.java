    package game;

    import java.util.Scanner;

    class Players {
        String name;
        int wins;
        int losses;
        int draws;
        char currentMark;
        boolean winner;
        Move move = new Move();

        public Players(String name, char currentMark) {
            this.name = name;
            this.currentMark = currentMark;
            wins = 0;
            losses = 0;
            draws = 0;
            winner = false;
        }

        void getPlayerMove(String status, char[][] gameBoard, Scanner scr) {
            System.out.print("\nEnter a move: ");

            move.row = scr.nextInt();
            move.col = scr.nextInt();

            while (!move.validateMove(gameBoard)) {
                move.row = scr.nextInt();
                move.col = scr.nextInt();
            }
        }

        void displayPlayerStats() {

            System.out.printf("Name: %s\n" +
                            "Wins: %d\nLosses: %d\nDraws: %d\nCurrent Mark: %c\n",
                            name, wins, losses, draws, currentMark);

        }

        void displayFinalStats() {

            System.out.println("\nFinal Stats");
            String stats = String.format("Wins: %d\nLosses: %d\nDraws: %d\n", wins, losses, draws);
            System.out.println(stats);
            System.out.println("Thanks for playing!");

        }

        static class ComputerAI extends Players {

            public ComputerAI(String name, char currentMark) {
                super(name, currentMark);
            }
        }

    }