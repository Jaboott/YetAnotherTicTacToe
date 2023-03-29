package ui.gui;

import javax.swing.*;

public class SplashScreen extends JFrame {

    JPanel panel;
    JLabel titleLabel;
    ImageIcon icon;

    public SplashScreen() {
        panel = new JPanel();
        titleLabel = new JLabel();
        icon = new ImageIcon("data//SplashScreen.png");
        titleLabel.setIcon(icon);
        panel.add(titleLabel);
        this.add(panel);
        this.setSize(500,500);
        this.setResizable(false);
        this.setVisible(true);
    }
}
