package ui;

import java.util.Scanner;
import java.util.Random;

import model.TicTacToe;

public class TicTacToeApp {

    private Scanner input;
    private TicTacToe game;
    private static final String PLAYER1 = "X";
    private static final String  PLAYER2 = "O";

    // EFFECTS: runs the tic-tac-toe app
    public TicTacToeApp() {
        runTicTacToe();
    }

    public void runTicTacToe() {
        int firstPlayer = chooseFirstPlayer();
        String symbol = "";
        input = new Scanner(System.in);
        boolean gameRunning = true;

        init();
        game.printBoard();
        System.out.println("Player" + firstPlayer + " starts first");

        while (gameRunning) {
            printInstructions(firstPlayer);
            int currMove = inputNextMove();
            symbol = changeSymbol(firstPlayer);

            if (checkInput(currMove)) {
                game.changeBoard(currMove, symbol);
            }
        }
    }


    private void init() {
        game = new TicTacToe();
    }

    private void printInstructions(int currentPlayer) {
        System.out.println("Player " + currentPlayer + " pls input a number between 0 to 8");
    }

    private int chooseFirstPlayer() {
        Random r = new Random();
        int num1 = 0;
        int num2 = 0;

        while (num1 == num2) {
            num1 = r.nextInt(10) + 1;
            num2 = r.nextInt(10) + 1;
        }

        if (num2 > num1) {
            return 2;
        }
        return 1;

    }

    private boolean checkInput(int input) {
        if (input > 8 || input < 0 || game.slotTaken(input)) {
            System.out.println("Not a valid input try again");
            return false;
        }
        return true;
    }

    private int inputNextMove() {
        int playerInput = 0;
        try {
            playerInput = this.input.nextInt();
        } catch (Exception e) {
            System.out.println("Not a valid input try again");
        }
        return playerInput;
    }

    private String changeSymbol(int player) {
        if (player == 1) {
            return PLAYER1;
        }
        return PLAYER2;
    }

}
