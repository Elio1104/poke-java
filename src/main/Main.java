package main;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame(Constants.TITLE);
        GamePanel gamePanel = new GamePanel();

        frame.add(gamePanel);
        frame.setSize(Constants.WIDTH, Constants.HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        System.out.println("Loading assets...");
        System.out.println(Constants.IMG_PATH + "logo.png");
        try {
            frame.setIconImage(ImageIO.read(new File(Constants.IMG_PATH + "logo.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setVisible(true);

        //recentre la fenetre
        Insets insets = frame.getInsets();
        frame.setSize(Constants.WIDTH + insets.left + insets.right,
                Constants.HEIGHT + insets.top + insets.bottom);
    }
}