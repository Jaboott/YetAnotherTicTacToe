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

        topLeft.setFont(new Font("Arial", Font.BOLD, 64));
        topMid.setFont(new Font("Arial", Font.BOLD, 64));
        topRight.setFont(new Font("Arial", Font.BOLD, 64));
        midLeft.setFont(new Font("Arial", Font.BOLD, 64));
        midMid.setFont(new Font("Arial", Font.BOLD, 64));
        midRight.setFont(new Font("Arial", Font.BOLD, 64));
        botLeft.setFont(new Font("Arial", Font.BOLD, 64));
        botMid.setFont(new Font("Arial", Font.BOLD, 64));
        botRight.setFont(new Font("Arial", Font.BOLD, 64));
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
        JButton button = (JButton) e.getSource();
        if (isFill(button)) {
            return;
        }
        changeButtonAfterAction(e);
        if (!checkEnd()) {
            JOptionPane.showMessageDialog(null, finalMessage());
            this.dispose();
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

    private Boolean isFill(JButton button) {
        if (button.getText().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    private String getSymbol(int player) {
        if (player == 1) {
            return PLAYER1;
        } else {
            return PLAYER2;
        }
    }

    private void changeButtonAfterAction(ActionEvent e) {
        updateButton((JButton) e.getSource());
        if (e.getSource() ==  topLeft) {
            ticTacToe.changeBoard(0, getSymbol(currPlayer));
        } else if (e.getSource() ==  topMid) {
            ticTacToe.changeBoard(1, getSymbol(currPlayer));
        } else if (e.getSource() ==  topRight) {
            ticTacToe.changeBoard(2, getSymbol(currPlayer));
        } else if (e.getSource() ==  midLeft) {
            ticTacToe.changeBoard(3, getSymbol(currPlayer));
        } else if (e.getSource() ==  midMid) {
            ticTacToe.changeBoard(4, getSymbol(currPlayer));
        } else if (e.getSource() == midRight) {
            ticTacToe.changeBoard(5, getSymbol(currPlayer));
        } else if (e.getSource() ==  botLeft) {
            ticTacToe.changeBoard(6, getSymbol(currPlayer));
        } else if (e.getSource() ==  botMid) {
            ticTacToe.changeBoard(7, getSymbol(currPlayer));
        } else if (e.getSource() ==  botRight) {
            ticTacToe.changeBoard(8, getSymbol(currPlayer));
        }
    }

    // REQUIRE: currPlayer needs to be either 1 or 2
    // EFFECTS: outputs message for given game status
    private String finalMessage() {
        String message = ticTacToe.checkStatus();
        if (message.equals("It is a tie")) {
            titleLabel.setText("The players tied, no winner");
            return "The players tied, no winner";
        } else if (message.equals("")) {
            return "";
        } else {
            titleLabel.setText("Player" + currPlayer + " have won by " + message);
            return "Player" + currPlayer + " have won by " + message;
        }
    }

    // EFFECTS: returns true if game have not ended, false if game have ended
    private boolean checkEnd() {
        String message = ticTacToe.checkStatus();

        return message.equals("");
    }

}
