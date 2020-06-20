    package game;

    class Players {
        String name;
        int wins;
        int losses;
        int draws;
        char currentMark;
        boolean winner;
        Move plyrMove;

        public Players(String name, char currentMark) {
            this.name = name;
            this.currentMark = currentMark;
            wins = 0;
            losses = 0;
            draws = 0;
            winner = false;
        }

        void playerMove(int row, int col, char[][] gameBoard) {

            gameBoard[row][col] = this.currentMark;

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

        class ComputerAI extends Players {

            public ComputerAI(String name, char currentMark) {
                super(name, currentMark);
            }
        }

    }