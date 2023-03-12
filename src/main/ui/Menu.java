package ui;

import model.Game;
import model.GameHistory;
import model.TicTacToe;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Represents the user interaction for the menu.
public class Menu {

    private static final String JSON_STORE = "./data/workroom.json";
    private GameHistory history = new GameHistory();
    private TicTacToeApp game;
    private Game gameStats;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // initialize game object and runs the menu
    public Menu() {
        game = new TicTacToeApp();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
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
                displaySaveMessage();
                break;
            } else if (playerInput == 5) {
                loadGameHistory();
            } else {
                System.out.println("Invalid input please try again");
            }
        }
    }

    // EFFECTS: prints the instructions
    private void displayMenu() {
        System.out.println("\n");
        System.out.println("Welcome to Crosses & Circles: The Ultimate Battle");
        System.out.println("Type 1 to see the current win-loss record");
        System.out.println("Type 2 to play the tic-tac-toe game");
        System.out.println("Type 3 to see the game history");
        System.out.println("Type 4 to stop the program");
        System.out.println("Type 5 to load previous games and win-loss");
    }

    // EFFECTS: prints the win loss
    private void displayWinLoss() {
        System.out.println("\n");
        System.out.println("Player1 have won " + countWins1() + " and lost " + countWins2() + " times.");
        System.out.println("Player2 have won " + countWins2() + " and lost " + countWins1() + " times.");
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

    // EFFECTS: counts the number of times 1 appear in the list
    private Integer countWins1() {
        int count = 0;
        for (int i : history.winners()) {
            if (i == 1) {
                count++;
            }
        }
        return count;
    }

    // EFFECTS: counts the number of times 2 appear in the list
    private Integer countWins2() {
        int count = 0;
        for (int i : history.winners()) {
            if (i == 2) {
                count++;
            }
        }
        return count;
    }

    // EFFECTS: Give the user a chance to save the games, y to save, anything else to quit
    private void displaySaveMessage() {
        Scanner input = new Scanner(System.in);
        String playerInput;
        System.out.println("Type y to save the game history and win-loss record");
        playerInput = input.nextLine();
        if (playerInput.equals("y")) {
            saveGameHistory();
        }
    }

    // EFFECTS: saves the GameHistory to file
    private void saveGameHistory() {
        try {
            jsonWriter.open();
            jsonWriter.write(history);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadGameHistory() {
        try {
            history = jsonReader.read();
            System.out.println("Loaded " + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
