package ui.gui;

import javax.swing.*;

//Represent a splash screen
public class SplashScreen extends JFrame {

    JPanel panel;
    JLabel titleLabel;
    ImageIcon icon;

    //EFFECTS: shows a splash screen on a JFrame
    public SplashScreen() {
        panel = new JPanel();
        titleLabel = new JLabel();
        icon = new ImageIcon("data//SplashScreen.png");
        titleLabel.setIcon(icon);
        panel.add(titleLabel);
        this.add(panel);
        this.setSize(500,500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
