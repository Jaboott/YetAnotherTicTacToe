package ui.gui;

import model.GameHistory;
import model.TicTacToe;

import javax.swing.*;
import java.awt.*;

public class GameRecordFrame extends JFrame {

    JTextArea record;
    JScrollPane recordHolder;

    private GameHistory history;

    GameRecordFrame(GameHistory history) {
        this.history = history;
        record = new JTextArea();
        recordHolder = new JScrollPane(record);
        displayRecord();
        record.setEditable(false);
        record.setLineWrap(true);
        record.setWrapStyleWord(true);
        recordHolder.setPreferredSize(new Dimension(200, 200));
        JOptionPane.showMessageDialog(null, recordHolder, "Game History", JOptionPane.PLAIN_MESSAGE);
    }


    // EFFECTS: prints every element in the list history
    private void displayRecord() {
        record.setText("The history of the games:");
        for (int i = 1; i <= history.messages().size(); i++) {
            record.setText(record.getText() + ("\nGame" + i + ":\n"));
            printBoard(history.boards().get(i - 1));
            record.setText(record.getText() + (history.messages().get(i - 1)));
            record.setText(record.getText() + ("\n\n"));
        }
    }

    // EFFECT: prints the 2d array with spaces in between each index
    //         prints number between 0-8 if slot is not taken
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
