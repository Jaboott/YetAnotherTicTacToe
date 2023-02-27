package ui;

import java.util.Scanner;
import java.util.Random;

import model.TicTacToe;

public class TicTacToeApp {

    private TicTacToe game;
    private static final String PLAYER1 = "X";
    private static final String PLAYER2 = "O";

    // EFFECTS: runs the tic-tac-toe app
    public TicTacToeApp() {
        runTicTacToe();
    }

    // MODIFY: this
    // EFFECTS: runs the game
    public void runTicTacToe() {
        int currPlayer = chooseFirstPlayer();
        String symbol = "";
        boolean gameRunning = true;

        init();
        game.printBoard();
        System.out.println("Player" + currPlayer + " starts first");

        while (gameRunning) {
            printInstructions(currPlayer);
            int currMove = inputNextMove();
            symbol = changeSymbol(currPlayer);

            if (checkInput(currMove)) {
                game.changeBoard(currMove, symbol);
                game.printBoard();
                finalMessage(currPlayer);
                gameRunning = checkEnd();
                currPlayer = changePlayer(currPlayer);
            }

        }
    }

    // MODIFY: this
    // EFFECTS: makes a new tic-tac-toe game
    private void init() {
        game = new TicTacToe();
    }

    // EFFECTS: prints out instructions
    private void printInstructions(int currentPlayer) {
        System.out.println("Player " + currentPlayer + " pls input a number between 0 to 8");
    }

    // EFFECTS: choose a random player
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

    // EFFECTS: prints message and return false if input is not valid, return true otherwise
    private boolean checkInput(int input) {
        if (input > 8 || input < 0 || game.slotTaken(input)) {
            System.out.println("Input not in range or taken pls try again");
            return false;
        }
        return true;
    }

    // EFFECTS: takes user input until user inputs a valid input
    private int inputNextMove() {
        int playerInput = 0;
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                playerInput = input.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Not a valid input try again");
                input.nextLine();
            }
        }
        return playerInput;
    }

    // EFFECTS: returns "X" or "O" depending on the inputted player
    private String changeSymbol(int player) {
        if (player == 1) {
            return PLAYER1;
        }
        return PLAYER2;
    }

    // EFFECTS: change the given player to the next player
    private int changePlayer(int player) {
        if (player == 1) {
            return 2;
        }
        return 1;
    }

    // REQUIRE: currPlayer needs to be either 1 or 2
    // EFFECTS: outputs message for given game status
    private void finalMessage(int currPlayer) {
        String message = game.checkStatus();

        if (message.equals("It is a tie")) {
            System.out.println("The players tied, no winner");
        } else if (message.equals("")) {
            System.out.println("");
        } else {
            System.out.println("Player" + currPlayer + " have won by " + message);
        }
    }

    // EFFECTS: returns true if game have not ended, false if game have ended
    private boolean checkEnd() {
        String message = game.checkStatus();

        if (message.equals("")) {
            return true;
        } else {
            return false;
        }
    }

}
