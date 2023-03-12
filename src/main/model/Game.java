package model;

import org.json.JSONObject;

public class Game {
    private TicTacToe board;
    private int winner;
    private String message;

    public int getWinner() {
        return winner;
    }

    public TicTacToe getBoard() {
        return board;
    }

    public String getMessage() {
        return message;
    }

    public void setBoard(TicTacToe board) {
        this.board = board;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("board", board.toJson());
        json.put("winner", winner);
        json.put("message", message);
        return json;
    }


}
