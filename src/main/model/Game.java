package model;

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
}
