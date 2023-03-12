package model;

import org.json.JSONArray;
import org.json.JSONObject;

// Represent the basic functions and parameters for tic-tac-toe
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

    // REQUIRE: index is between 0 - 8, value is either "X" or "O"
    // MODIFY: this
    // EFFECT: sets a specified index on the 2d array to a specific value
    //         if the given slot is empty
    public void changeBoard(int index, String value) {
        board[index / ROW][(index % COLUMN)] = value;
    }

    // EFFECT: checks the status of the game
    //         return "row" if a row is completed
    //         return "column" if a column is completed
    //         return "diagonal" if a diagonal is completed
    //         return "It is a tie" if the game is a tie
    //         return "" if the game have not ended
    public String checkStatus() {
        if (checkRows()) {
            return "row";
        } else if (checkColumns()) {
            return "column";
        } else if (checkDiagonal()) {
            return "diagonal";
        } else if (checkTie()) {
            return "It is a tie";
        } else {
            return "";
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
        return (board[0][0].equals(board[1][1])
                && board[1][1].equals(board[2][2])
                && !board[1][1].equals(""))
                || (board[0][2].equals(board[1][1])
                && board[1][1].equals(board[2][0])
                && !board[1][1].equals(""));
    }


    // EFFECTS: returns true if not every element on the list is "" false otherwise
    private boolean checkTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                if ((board[i][j]).equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    // REQUIRE: index is a valid index on the 2d array
    // EFFECTS: return true if the given index is taken false otherwise
    public boolean slotTaken(int index) {
        return !board[index / ROW][(index % COLUMN)].equals("");
    }

    // REQUIRE: row and column is within the parameter ROW and COLUMN
    // EFFECTS: get the string in 2d array of given the row and column
    public String getSlot(int row, int column) {
        return board[row][column];
    }

    // EFFECTS: returns items in the 2d array as many JSON Object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                json.put(String.valueOf((i * 3 + j)),board[i][j]);
            }
        }
        return json;
    }

}