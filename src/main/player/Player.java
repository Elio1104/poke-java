package main.player;

import main.Constants.Status;
import java.awt.*;

public class Player {
    PlayerAssets assets;
    int x, y;
    int speed;
    int item;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 8;
        this.item = 0;
        this.assets = new PlayerAssets();
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void addItem() {
        this.item++;
    }

    public Image getSprite(Status status, int frame){
        return (assets.getSprite(status, frame));
    }
}
