package ui.gui;

import model.GameHistory;
import model.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represent a JFrame with the Game Record
public class GameRecordFrame extends JFrame {

    JTextArea record;
    JScrollPane recordHolder;
    ButtonGroup group;
    JRadioButton radioButton1;
    JRadioButton radioButton2;
    JPanel radioPanel;

    //private GameHistory history;
    private GameHistory tieHistory;

    //EFFECTS: construct a record frame
    GameRecordFrame(GameHistory history) {
        //this.history = history;
        initializeObjects();
        initializePanel();
        radioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayRecord(history);
            }
        });
        radioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tieHistory = history.filterHistory();
                displayRecord(tieHistory);
            }
        });
        JOptionPane.showMessageDialog(null, recordHolder, "Game History", JOptionPane.PLAIN_MESSAGE);
    }



    //EFFECTS: initializes panel
    private void initializePanel() {
        record.setEditable(false);
        record.setLineWrap(true);
        record.setWrapStyleWord(true);
        recordHolder.setPreferredSize(new Dimension(200, 200));

        group.add(radioButton1);
        group.add(radioButton2);
        radioPanel.add(radioButton1);
        radioPanel.add(radioButton2);
        recordHolder.setColumnHeaderView(radioPanel);
    }

    //EFFECTS: creates a new objects for the objects
    private void initializeObjects() {
        record = new JTextArea();
        recordHolder = new JScrollPane(record);
        radioPanel = new JPanel();
        radioButton1 = new JRadioButton("Show All History");
        radioButton2 = new JRadioButton("Show Tie");
        group = new ButtonGroup();
    }

    // EFFECTS: sets JTextArea to every element in the list history
    private void displayRecord(GameHistory history) {
        record.setText("The history of the games:");
        for (int i = 1; i <= history.messages().size(); i++) {
            record.setText(record.getText() + ("\nGame" + i + ":\n"));
            printBoard(history.boards().get(i - 1));
            record.setText(record.getText() + (history.messages().get(i - 1)));
            record.setText(record.getText() + ("\n\n"));
        }
    }

    // EFFECT: sets JTextArea to the 2d array with spaces in between each index
    //         sets JTextArea to number between 0-8 if slot is not taken
    private void printBoard(TicTacToe game) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.getSlot(i,j).equals("")) {
                    record.setText(record.getText() + (count + "     "));
                } else {
                    record.setText(record.getText() + (game.getSlot(i,j) + "     "));
                }
                count++;
            }
            record.setText(record.getText() + ("\n"));
        }
    }
}
