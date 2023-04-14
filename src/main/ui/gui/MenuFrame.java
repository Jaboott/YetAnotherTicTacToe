package ui.gui;

import model.Event;
import model.EventLog;
import model.GameHistory;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import static ui.Menu.JSON_STORE;

//Represents a JFrame with a menu on it
public class MenuFrame extends JFrame implements ActionListener {

    JButton showRecord;
    JButton playGame;
    JButton gameHistory;
    JButton stopProgram;
    JButton loadHistory;

    JPanel panel;
    JLabel title;

    private GameHistory history;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: constructs a new MenuFrame with no parameter passed in
    public MenuFrame() {
        this.history = new GameHistory();
        initializeComponents();
    }

    //EFFECTS: constructs a new MenuFrame with history saved
    public MenuFrame(GameHistory history) {
        initializeComponents();
        this.history = history;
    }

    //EFFECTS: initialize the buttons, panel, and label for the JFrame
    private void initializeComponents() {
        this.setSize(170,210);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        title = new JLabel("Click on an option:");
        showRecord = new JButton("Show Record");
        playGame = new JButton("Play Tic-Tac-Toe");
        gameHistory = new JButton("Show Game History");
        stopProgram = new JButton("Quit the Game");
        loadHistory = new JButton("Load History");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initializeMenu();
        initializeActionListener();

        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //EFFECTS: adds components to the menu JFrame
    private void initializeMenu() {
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(showRecord);
        panel.add(playGame);
        panel.add(gameHistory);
        panel.add(stopProgram);
        panel.add(loadHistory);
        panel.add(Box.createVerticalGlue());
    }

    //EFFECTS: initialize ActionListener
    private void initializeActionListener() {
        showRecord.addActionListener(this);
        playGame.addActionListener(this);
        gameHistory.addActionListener(this);
        stopProgram.addActionListener(this);
        loadHistory.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() ==  showRecord) {
            JOptionPane.showMessageDialog(null, displayWinLoss());
            new MenuFrame(history);
        } else if (e.getSource() ==  playGame) {
            new TicTacToeAppFrame(history);
        } else if (e.getSource() ==  gameHistory) {
            new GameRecordFrame(history);
            new MenuFrame(history);
        } else if (e.getSource() ==  stopProgram) {
            saveHistoryCallBack();
        } else if (e.getSource() ==  loadHistory) {
            loadHistoryCallBack();
        }
        this.dispose();
    }

    //EFFECTS: gives the user an option to save history
    private void saveHistoryCallBack() {
        int result = JOptionPane.showConfirmDialog(null, "Save Game?", "", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            saveGameHistory();
        }
        for (Event e: EventLog.getInstance()) {
            System.out.println(e.toString());
        }
        System.exit(0);
    }

    //EFFECTS: gives the user an option to load history
    private void loadHistoryCallBack() {
        int result = JOptionPane.showConfirmDialog(null, "Load History?", "", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            loadGameHistory();
        }
        new MenuFrame(history);
    }

    // EFFECTS: returns the win loss in a String
    private String displayWinLoss() {
        return ("Player1 have won " + countWins1() + " and lost " + countWins2() + " times.\n"
                + "Player2 have won " + countWins2() + " and lost " + countWins1() + " times.");
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
    // EFFECTS: loads GameHistory from file
    private void loadGameHistory() {
        try {
            history = jsonReader.read();
            System.out.println("Loaded " + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
