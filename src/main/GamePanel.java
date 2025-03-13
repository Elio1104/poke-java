package main;

import main.map.Map;
import main.player.Player;

import main.Constants.Status;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener{
    private final Map map;
    private final Player player;
    private final int paddingX, paddingY;
    private final int playerPosPixelX, playerPosPixelY;

    public GamePanel() {
        this.map = new Map();
        this.player = new Player(map.getPlayerX(), map.getPlayerY());
        map.deleteCell(player.getX(), player.getY());

        paddingX = (int) Math.ceil((((double)(Constants.WIDTH - Constants.IMG_PIXEL_SIZE) / 2) / Constants.IMG_PIXEL_SIZE));
        paddingY = (int) Math.ceil((((double)(Constants.HEIGHT - Constants.IMG_PIXEL_SIZE) / 2) / Constants.IMG_PIXEL_SIZE));

        playerPosPixelX = Constants.WIDTH / 2 - Constants.IMG_PIXEL_SIZE / 2;
        playerPosPixelY = Constants.HEIGHT / 2 - Constants.IMG_PIXEL_SIZE / 2;

        addKeyListener(this);
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

    private void movePlayer(int dx, int dy) {
        int newY = player.getY() + dy;
        int newX = player.getX() + dx;

        System.out.println("New position: " + newX + ", " + newY);
        // Vérifie si la nouvelle position est dans les limites et si ce n'est pas un mur
        if (newX >= 0 && newX < map.getWidth() && newY >= 0 && newY < map.getHeight() && map.getCell(newX, newY) != '1') {
            if (map.getCell(newX, newY) == 'V') {
                System.out.println("You lose!");
                System.exit(0);
            }
            else if (map.getCell(newX, newY) == 'E') {
                if (map.checkCollectible()) {
                    System.out.println("You win!");
                    System.exit(0);
                }
            }
            else {
                if (map.getCell(newX, newY) == 'C') {
                    map.deleteCell(newX, newY);
                    player.addItem();
                }
                player.setX(newX);
                player.setY(newY);
            }
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                movePlayer(-1, 0); // Déplacement à gauche
                break;
            case KeyEvent.VK_RIGHT:
                movePlayer(1, 0); // Déplacement à droite
                break;
            case KeyEvent.VK_UP:
                movePlayer(0, -1); // Déplacement vers le haut
                break;
            case KeyEvent.VK_DOWN:
                movePlayer(0, 1); // Déplacement vers le bas
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}