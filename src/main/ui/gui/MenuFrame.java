package ui.gui;

import model.Game;
import model.GameHistory;
import model.TicTacToe;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import static ui.Menu.JSON_STORE;

public class MenuFrame extends JFrame implements ActionListener {

    JButton showRecord;
    JButton playGame;
    JButton gameHistory;
    JButton stopProgram;
    JButton loadHistory;

    JPanel panel;

    JLabel title;

    GameHistory history;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public MenuFrame() {
        this.history = new GameHistory();
        initializeComponents();
    }

    public MenuFrame(GameHistory history) {
        initializeComponents();
        this.history = history;
        //System.out.println(history.messages());
    }


    private void initializeComponents() {
        this.setSize(170,210);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        title = new JLabel("Click on an option:");
        showRecord = new JButton("Show Record");
        playGame = new JButton("Play Tic-Tac-Toe");
        gameHistory = new JButton("Show Game History");
        stopProgram = new JButton("Stop The Program");
        loadHistory = new JButton("Load History");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeMenu();
        initializeActionListener();

        this.add(panel);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

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
            JOptionPane.showMessageDialog(null,     displayWinLoss());
            new MenuFrame(history);
        } else if (e.getSource() ==  playGame) {
            new TicTacToeAppFrame(history);
        } else if (e.getSource() ==  gameHistory) {
            System.out.println("3");
        } else if (e.getSource() ==  stopProgram) {
            saveHistoryCallBack();
        } else if (e.getSource() ==  loadHistory) {
            loadHistoryCallBack();
        }
        this.dispose();
    }


    private void saveHistoryCallBack() {
        int result = JOptionPane.showConfirmDialog(null, "Save Game?", "", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            saveGameHistory();
        }
        System.exit(0);
    }

    private void loadHistoryCallBack() {
        int result = JOptionPane.showConfirmDialog(null, "Load History?", "", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            loadGameHistory();
            new MenuFrame(history);
        } else {
            System.exit(0);
        }
    }

    // EFFECTS: prints the win loss
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
