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
        map.deleteCell((int)player.getX(), (int)player.getY());

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

        for(double x = (int)player.getX() - paddingX; x <= player.getX() + 1 + paddingX; x++) {
            for(double y = (int)player.getY() - paddingY; y <= player.getY() + 1 + paddingY; y++) {
                int posX = (int)((double)playerPosPixelX - ((double)Constants.IMG_PIXEL_SIZE * (player.getX() - x)));
                int posY = (int)((double)playerPosPixelY - ((double)Constants.IMG_PIXEL_SIZE * (player.getY() - y)));
                g.drawImage(map.getGrass(), posX, posY, this);
                if (!(x < 0 || y < 0 || x >= map.getWidth() || y >= map.getHeight()))
                    g.drawImage(map.getTile((int)x, (int)y), posX, posY, this);
            }
        }
        System.out.println("end : " + player.getFrame());
        g.drawImage(player.getSprite(), playerPosPixelX, playerPosPixelY, this);
    }

    private void movePlayer(int dx, int dy) {
        if (player.isMoving()) return;

        double newY = (int)player.getY() + dy;
        double newX = (int)player.getX() + dx;

        // Vérifie si la nouvelle position est dans les limites et si ce n'est pas un mur
        if (newX >= 0 && newX < map.getWidth() && newY >= 0 && newY < map.getHeight() && map.getCell((int)newX, (int)newY) != '1') {
            if (map.getCell((int)newX, (int)newY) == 'V') {
                System.out.println("You lose!");
                System.exit(0);
            }
            else if (map.getCell((int)newX, (int)newY) == 'E') {
                if (map.checkCollectible()) {
                    System.out.println("You win!");
                    System.exit(0);
                }
            }
            else {
                if (map.getCell((int)newX, (int)newY) == 'C') {
                    map.deleteCell((int)newX, (int)newY);
                    player.addItem();
                }

                player.swapMoving();
                player.nextFrame();

                Timer timer = new Timer(16, e -> {
                    if (player.getX() != newX || player.getY() != newY) {
                        if (Math.abs(player.getX() - newX) <= 0.5 && Math.abs(player.getY() - newY) <= 0.5 && player.getFrame() != 3) {
                            System.out.println("diff x : " + Math.abs(player.getX() - newX) + " diff y : " + Math.abs(player.getY() - newY));
                            player.nextFrame();
                        }
                        if (dx == -1)
                            player.setX(Math.max(player.getX() - ((double) player.getSpeed() / Constants.IMG_PIXEL_SIZE), newX));
                        if (dx == 1)
                            player.setX(Math.min(player.getX() + ((double) player.getSpeed() / Constants.IMG_PIXEL_SIZE), newX));
                        if (dy == -1)
                            player.setY(Math.max(player.getY() - ((double) player.getSpeed() / Constants.IMG_PIXEL_SIZE), newY));
                        if (dy == 1)
                            player.setY(Math.min(player.getY() + ((double) player.getSpeed() / Constants.IMG_PIXEL_SIZE), newY));
                        if (player.getFrame() == 3 && player.getX() == newX && player.getY() == newY)
                            player.nextFrame();
                        repaint(); // Met à jour l'affichage
                    } else {
                        // Fin de l'animation
                        player.setX(newX);
                        player.setY(newY);
                        ((Timer) e.getSource()).stop();
                        player.swapMoving();
                    }
                });
                timer.start();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Q:
            case KeyEvent.VK_LEFT:
                player.setStatus(Status.LEFT);
                movePlayer(-1, 0); // Déplacement à gauche
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                player.setStatus(Status.RIGHT);
                movePlayer(1, 0); // Déplacement à droite
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_Z:
                player.setStatus(Status.UP);
                movePlayer(0, -1); // Déplacement vers le haut
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                player.setStatus(Status.DOWN);
                movePlayer(0, 1); // Déplacement vers le bas
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}