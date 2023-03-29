package ui.gui;

import model.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToeAppFrame extends JFrame implements ActionListener {

    JButton topLeft;
    JButton topMid;
    JButton topRight;
    JButton midLeft;
    JButton midMid;
    JButton midRight;
    JButton botLeft;
    JButton botMid;
    JButton botRight;

    JPanel buttonPanel;
    JPanel mainPanel;
    JLabel titleLabel;

    TicTacToe ticTacToe;

    private int currPlayer;
    private static final String PLAYER1 = "X";
    private static final String PLAYER2 = "O";

    public TicTacToeAppFrame() {
        ticTacToe = new TicTacToe();
        currPlayer = chooseFirstPlayer();
        mainPanel = new JPanel(new BorderLayout());
        titleLabel = new JLabel("Player" + currPlayer + " starts first");
        buttonPanel = new JPanel(new GridLayout(3,3));

        initializeButtons();
        addButtons();
        initializeActionListener();

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        this.add(mainPanel);
        this.setSize(300,300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initializeButtons() {
        topLeft = new JButton("");
        topMid = new JButton("");
        topRight = new JButton("");
        midLeft = new JButton("");
        midMid = new JButton("");
        midRight = new JButton("");
        botLeft = new JButton("");
        botMid = new JButton("");
        botRight = new JButton("");
    }

    private void addButtons() {
        buttonPanel.add(topLeft);
        buttonPanel.add(topMid);
        buttonPanel.add(topRight);
        buttonPanel.add(midLeft);
        buttonPanel.add(midMid);
        buttonPanel.add(midRight);
        buttonPanel.add(botLeft);
        buttonPanel.add(botMid);
        buttonPanel.add(botRight);
    }

    private void initializeActionListener() {
        topLeft.addActionListener(this);
        topMid.addActionListener(this);
        topRight.addActionListener(this);
        midLeft.addActionListener(this);
        midMid.addActionListener(this);
        midRight.addActionListener(this);
        botLeft.addActionListener(this);
        botMid.addActionListener(this);
        botRight.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() ==  topLeft) {
            updateButton(topLeft);
        } else if (e.getSource() ==  topMid) {
            updateButton(topMid);
        } else if (e.getSource() ==  topRight) {
            updateButton(topRight);
        } else if (e.getSource() ==  midLeft) {
            updateButton(midLeft);
        } else if (e.getSource() ==  midMid) {
            updateButton(midMid);
        } else if (e.getSource() == midRight) {
            updateButton(midRight);
        } else if (e.getSource() ==  botLeft) {
            updateButton(botLeft);
        } else if (e.getSource() ==  botMid) {
            updateButton(botMid);
        } else if (e.getSource() ==  botRight) {
            updateButton(botRight);
        }
        currPlayer = changePlayer(currPlayer);
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

    // EFFECTS: change the given player to the next player
    private int changePlayer(int player) {
        if (player == 1) {
            titleLabel.setText("Player2's turn");
            return 2;
        }
        titleLabel.setText("Player1's turn");
        return 1;
    }

    private void updateButton(JButton button) {
        if (currPlayer == 1) {
            button.setText(PLAYER1);
        } else {
            button.setText(PLAYER2);
        }
    }

}
