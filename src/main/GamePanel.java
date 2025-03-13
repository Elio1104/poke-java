package main;

import main.map.Map;
import main.player.Player;

import main.Constants.Status;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    private final Map map;
    private final Player player;
    private final int paddingX, paddingY;
    private final int playerPosPixelX, playerPosPixelY;

    public GamePanel() {
        this.map = new Map();
        this.player = new Player(map.getPlayerX(), map.getPlayerY());

        paddingX = (int) Math.ceil((((double)(Constants.WIDTH - Constants.IMG_PIXEL_SIZE) / 2) / Constants.IMG_PIXEL_SIZE));
        paddingY = (int) Math.ceil((((double)(Constants.HEIGHT - Constants.IMG_PIXEL_SIZE) / 2) / Constants.IMG_PIXEL_SIZE));

        playerPosPixelX = Constants.WIDTH / 2 - Constants.IMG_PIXEL_SIZE / 2;
        playerPosPixelY = Constants.HEIGHT / 2 - Constants.IMG_PIXEL_SIZE / 2;

        setFocusable(true);
        requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int x = player.getX() - paddingX; x <= player.getX() + paddingX; x++) {
            for(int y = player.getY() - paddingY; y <= player.getY() + paddingY; y++) {
                int posX = playerPosPixelX - Constants.IMG_PIXEL_SIZE * (player.getX() - x);
                int posY = playerPosPixelY - Constants.IMG_PIXEL_SIZE * (player.getY() - y);
                g.drawImage(map.getGrass(), posX, posY, this);
                if (!(x < 0 || y < 0 || x >= map.getWidth() || y >= map.getHeight()))
                    g.drawImage(map.getTile(x,y), posX, posY, this);
            }
        }

        g.drawImage(player.getSprite(Status.DOWN, 1), playerPosPixelX, playerPosPixelY, this);
    }
}