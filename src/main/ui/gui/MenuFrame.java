package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame implements ActionListener {

    JButton showRecord;
    JButton playGame;
    JButton gameHistory;
    JButton stopProgram;
    JButton loadHistory;

    JPanel panel;

    JLabel title;

    public MenuFrame() {
        this.setSize(170,210);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        title = new JLabel("Click on an option:");
        showRecord = new JButton("Show Record");
        playGame = new JButton("Play Tic-Tac-Toe");
        gameHistory = new JButton("Show Game History");
        stopProgram = new JButton("Stop The Program");
        loadHistory = new JButton("Load History");
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
            System.out.println("1");
        } else if (e.getSource() ==  playGame) {
            System.out.println("2");
        } else if (e.getSource() ==  gameHistory) {
            System.out.println("3");
        } else if (e.getSource() ==  stopProgram) {
            System.out.println("4");
        } else if (e.getSource() ==  loadHistory) {
            System.out.println("5");
        }
    }
}
