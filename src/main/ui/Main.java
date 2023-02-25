package ui;

import model.TicTacToe;

public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.changeBoard(1, "X");
        game.changeBoard(4, "X");
        game.changeBoard(7, "X");
        game.checkStatus();
    }
}
