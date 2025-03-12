package main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame(Constants.TITLE);
        GamePanel gamePanel = new GamePanel();

        frame.add(gamePanel);
        frame.setSize(Constants.WIDTH, Constants.HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        //recentre la fenetre
        Insets insets = frame.getInsets();
        frame.setSize(Constants.WIDTH + insets.left + insets.right,
                Constants.HEIGHT + insets.top + insets.bottom);
    }
}
