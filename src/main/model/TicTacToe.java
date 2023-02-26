package model;

public class TicTacToe {

    private static final int ROW = 3;
    private static final int COLUMN = 3;

    private String[][] board;


    // MODIFY: this
    // EFFECTS: creates a 2d array with numbers 0-8 filled in each index
    public TicTacToe() {
        board = new String[ROW][COLUMN];
        initializeBoard();
    }

    // MODIFY: this
    // EFFECTS: sets every index of the 2d array to empty string
    private void initializeBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                board[i][j] = "";
            }
        }
    }

    // MODIFY: this
    // EFFECT: prints the 2d array with spaces in between each index
    //         prints number between 0-8 if slot is not taken
    public void printBoard() {
        int count = 0;
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                if (board[i][j].equals("")) {
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
        board[index / ROW][(index % COLUMN)] = value;
    }

    // EFFECT: checks the status of the game
    //         prints "Rows" if a row is completed
    //         prints "Columns" if a column is completed
    //         prints "Diagonal" if a diagonal is completed
    //         prints "game have not ended"" if the game have not ended
    public void checkStatus() {
        if (checkRows()) {
            System.out.println("Rows");
        } else if (checkColumns()) {
            System.out.println("Columns");
        } else if (checkDiagonal()) {
            System.out.println("Diagonal");
        } else if (checkTie()) {
            System.out.println("It is a tie");
        } else {
            System.out.println("game have not ended");
        }

    }

    // EFFECTS: returns true if a row is completed return false otherwise
    private boolean checkRows() {
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][1].equals("")) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns true if a column is completed return false otherwise
    private boolean checkColumns() {
        for (int i = 0; i < COLUMN; i++) {
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[1][i].equals("")) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns true if a diagonal is completed return false otherwise
    private boolean checkDiagonal() {
        boolean result =
                (board[0][0].equals(board[1][1])
                        && board[1][1].equals(board[2][2])
                        && !board[1][1].equals(""))
                        || (board[0][2].equals(board[1][1])
                        && board[1][1].equals(board[2][0])
                        && !board[1][1].equals(""));
        return result;
    }


    // EFFECTS: returns true if not every element on the list is "" false otherwise
    private boolean checkTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                if (board[i][j] == "") {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean slotTaken(int index) {
        return board[index / ROW][(index % COLUMN)] != "";
    }

}