package main;

import main.map.Map;
import main.player.Player;

import main.Constants.Status;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    private final Map map;
    private final Player player;

    public GamePanel() {
        this.map = new Map();
        this.player = new Player(map.getPlayerX(), map.getPlayerY());

        setFocusable(true);
        requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(player.getSprite(Status.DOWN, 1), Constants.WIDTH / 2 - Constants.IMG_PIXEL_SIZE / 2, Constants.HEIGHT / 2 - Constants.IMG_PIXEL_SIZE / 2, this);
    }
}