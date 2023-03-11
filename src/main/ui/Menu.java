package ui;

import model.Game;
import model.GameHistory;
import model.TicTacToe;

import java.util.Scanner;

// Represents the user interaction for the menu.
public class Menu {

    private GameHistory history = new GameHistory();
    private TicTacToeApp game;
    private Game gameStats;

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
        for (int i = 1; i <= history.messages().size(); i++) {
            System.out.println("Game" + i + ":");
            printBoard(history.boards().get(i - 1));
            System.out.println(history.messages().get(i - 1));
            System.out.println("\n\n");
        }
    }

    // REQUIRE: inputted message have to be the specified game messages
    //          either "The players tied, no winner"
    //          or "Player" + currPlayer + " have won by " + message
    //          where currPlayer is either 1 or 2 and message is "row", "column", or "diagonal"
    // EFFECTS: updates the win count and history
    private void updateWinLoss(String message) {
        gameStats = new Game();
        String index = message.substring(6,7);
        if (index.equals("1") || index.equals("2")) {
            if (index.equals("1")) {
                gameStats.setWinner(1);
            } else {
                gameStats.setWinner(2);
            }
        }
        gameStats.setMessage(message);
        gameStats.setBoard(game.getGame());
        history.addGame(gameStats);
    }

    // EFFECT: prints the 2d array with spaces in between each index
    //         prints number between 0-8 if slot is not taken
    private void printBoard(TicTacToe game) {
        int count = 0;
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

    // EFFECTS: prints the win loss
    private void displayWinLoss() {
        System.out.println("\n");
        System.out.println("Player1 have won " + countWins1() + " and lost " + countWins2() + " times.");
        System.out.println("Player2 have won " + countWins2() + " and lost " + countWins1() + " times.");
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

    // EFFECTS: counts the number of times 1 appear in the list
    public Integer countWins1() {
        int count = 0;
        for (int i : history.winners()) {
            if (i == 1) {
                count++;
            }
        }
        return count;
    }

    // EFFECTS: counts the number of times 2 appear in the list
    public Integer countWins2() {
        int count = 0;
        for (int i : history.winners()) {
            if (i == 2) {
                count++;
            }
        }
        return count;
    }
}
