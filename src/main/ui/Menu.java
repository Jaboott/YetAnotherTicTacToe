package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private int p1Wins = 0;
    private int p2Wins = 0;
    private ArrayList<String> history = new ArrayList<String>();
    TicTacToeApp game;

    public Menu() {
        game = new TicTacToeApp();
        runMenu();
    }

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

    private void displayRecord() {

    }

    private void updateWinLoss(String message) {
        String index = Character.toString(message.indexOf(6));
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

    private void displayWinLoss() {
        System.out.println("Player1 have won " + p1Wins + " and lost " + p2Wins + " times.");
        System.out.println("Player2 have won " + p2Wins + " and lost " + p1Wins + " times.");
    }

    public void displayMenu() {
        System.out.println("Welcome to Crosses & Circles: The Ultimate Battle");
        System.out.println("Type 1 to see the current win-loss record");
        System.out.println("Type 2 to play the tic-tac-toe game");
        System.out.println("Type 3 to see the game history");
        System.out.println("Type 4 to stop the program");
    }
}
