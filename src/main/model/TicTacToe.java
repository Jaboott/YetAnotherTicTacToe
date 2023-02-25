package model;

public class TicTacToe {

    private static final int ROW = 3;
    private static final int COLUMN = 3;

    private String[][] board;


    // MODIFY: this
    // EFFECTS: creates a 2d array with numbers 0-8 filled in each index
    public TicTacToe() {
        board = new String[ROW][COLUMN];
    }

    // MODIFY: this
    // EFFECT: prints the 2d array with spaces in between each index
    //         prints number between 0-8 if slot is not taken
    public void printBoard() {
        int count = 0;
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                if (board[i][j] == null) {
                    System.out.print(count + "     ");
                } else {
                    System.out.print(board[i][j] + "     ");
                }
                count++;
            }
            System.out.println("\n");
        }
    }

    // REQUIRE: index is between 0 - 8, value is either "X" or "O"
    // MODIFY: this
    // EFFECT: sets a specified index on the 2d array to a specific value
    //         if the given slot is empty
    public void changeBoard(int index, String value) {
        if (board[index / ROW][(index % COLUMN)] == null) {
            board[index / ROW][(index % COLUMN)] = value;
        } else {
            System.out.println("The given slot is already taken!");
        }
    }


    private boolean checkRows() {
        for (int i = 0; i < ROW; i++) {
            if ((board[i][0] == board[i][1]) && (board[i][1] == board[i][2])) {
                return true;
            }
        }
        return false;
    }


}
