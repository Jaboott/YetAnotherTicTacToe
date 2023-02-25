package ui;

import model.TicTacToe;

public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.printBoard();
        game.changeBoard(6, "X");
        game.printBoard();
        game.changeBoard(6, "O");
        game.printBoard();
        game.changeBoard(7, "O");
        game.printBoard();
    }
}
