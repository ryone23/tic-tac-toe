package game;

class Move {
    int row;
    int col;


    boolean validateMove(char[][] gameBoard) {

        boolean validMove = true;
        String validMoveRegex = "[012]";

        if (!String.valueOf(row).matches(validMoveRegex)
        || !String.valueOf(col).matches(validMoveRegex)) {
            validMove = false;
            System.out.println("Invalid move. Enter digits in " +
                    "in the range [0,2].");
        } else if (gameBoard[row][col] != ' ') {
            validMove = false;
            System.out.println("This cell is occupied!" +
                    " Choose another one!");
        }
        return validMove;
    }
}
