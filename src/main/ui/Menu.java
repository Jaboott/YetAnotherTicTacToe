package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private int p1Wins = 0;
    private int p2Wins = 0;
    private ArrayList<String> history = new ArrayList<String>();
    TicTacToeApp game;

    // initialize game object and runs the menu
    public Menu() {
        game = new TicTacToeApp();
        runMenu();
    }

    // MODIFY: this
    // EFFECT: runs the menu
    public void runMenu() {
        Scanner input = new Scanner(System.in);
        int playerInput;

        while (true) {
            displayMenu();
            playerInput = input.nextInt();

            if (playerInput == 1) {
                displayWinLoss();
            } else if (playerInput == 2) {
                updateWinLoss(game.runTicTacToe());
            } else if (playerInput == 3) {
                displayRecord();
            } else if (playerInput == 4) {
                break;
            } else {
                System.out.println("Invalid input please try again");
            }
        }
    }

    // EFFECTS: prints every element in the list history
    private void displayRecord() {
        System.out.println("The history of the games:");
        for (String i : history) {
            System.out.println(i);
        }
    }

    // REQUIRE: inputted message have to be the specified game messages
    //          either "The players tied, no winner"
    //          or "Player" + currPlayer + " have won by " + message
    //          where currPlayer is either 1 or 2 and message is "row", "column", or "diagonal"
    // EFFECTS: updates the win count and history
    private void updateWinLoss(String message) {
        String index = message.substring(6,7);
        if (index.equals("1") || index.equals("2")) {
            if (index.equals("1")) {
                p1Wins++;
                history.add(message);
            } else {
                p2Wins++;
                history.add(message);
            }
        } else {
            history.add(message);
        }
    }

    // EFFECTS: prints the win loss
    private void displayWinLoss() {
        System.out.println("\n");
        System.out.println("Player1 have won " + p1Wins + " and lost " + p2Wins + " times.");
        System.out.println("Player2 have won " + p2Wins + " and lost " + p1Wins + " times.");
    }

    // EFFECTS: prints the instructions
    public void displayMenu() {
        System.out.println("\n");
        System.out.println("Welcome to Crosses & Circles: The Ultimate Battle");
        System.out.println("Type 1 to see the current win-loss record");
        System.out.println("Type 2 to play the tic-tac-toe game");
        System.out.println("Type 3 to see the game history");
        System.out.println("Type 4 to stop the program");
    }
}
