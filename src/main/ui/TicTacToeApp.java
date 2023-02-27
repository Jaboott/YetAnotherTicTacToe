package ui;

import java.util.Scanner;
import java.util.Random;

import model.TicTacToe;

// Represents the user interaction functions of the tic-tac-toe game
public class TicTacToeApp {

    private TicTacToe game;
    private static final String PLAYER1 = "X";
    private static final String PLAYER2 = "O";
    private int p1Wins = 0;
    private int p2Wins = 0;

    // MODIFY: this
    // EFFECTS: runs the game
    public String runTicTacToe() {
        int currPlayer = chooseFirstPlayer();
        String symbol = "";

        init();
        printBoard(game);
        System.out.println("Player" + currPlayer + " starts first");

        while (true) {
            printInstructions(currPlayer);
            int currMove = inputNextMove();
            symbol = changeSymbol(currPlayer);

            if (checkInput(currMove)) {
                game.changeBoard(currMove, symbol);
                printBoard(game);

                if (!checkEnd()) {
                    return finalMessage(currPlayer);
                }
                currPlayer = changePlayer(currPlayer);
            }

        }
    }

    // EFFECT: prints the 2d array with spaces in between each index
    //         prints number between 0-8 if slot is not taken
    private void printBoard(TicTacToe game) {
        int count = 0;
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.getSlot(i,j).equals("")) {
                    System.out.print(count + "     ");
                } else {
                    System.out.print(game.getSlot(i,j) + "     ");
                }
                count++;
            }
            System.out.println("\n");
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
    private String finalMessage(int currPlayer) {
        String message = game.checkStatus();

        if (message.equals("It is a tie")) {
            System.out.println("The players tied, no winner");
            return "The players tied, no winner";
        } else if (message.equals("")) {
            System.out.println("");
            return "";
        } else {
            System.out.println("Player" + currPlayer + " have won by " + message);
            return "Player" + currPlayer + " have won by " + message;
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
