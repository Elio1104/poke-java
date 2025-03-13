package main.player;

import main.Constants.Status;
import java.awt.*;

public class Player {
    private boolean isMoving;
    private final PlayerAssets assets;
    private double x, y;
    private final int speed;
    private int item;
    private Status status;
    private int frame;

    public Player(int x, int y) {
        this.isMoving = false;
        this.x = x;
        this.y = y;
        this.speed = 8;
        this.item = 0;
        this.assets = new PlayerAssets();
        this.status = Status.DOWN;
        this.frame = 1;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    public void addItem() {
        this.item++;
    }

    public Image getSprite(){
        return (assets.getSprite(status, frame));
    }

    public boolean isMoving() {
        return isMoving;
    }
    public void swapMoving() {
        isMoving = !isMoving;
    }

    public int getSpeed() {
        return speed;
    }

    public int getFrame() {
        return frame;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void nextFrame() {
        frame++;
        if(frame > 3) {
            frame = 1;
        }
    }
}
