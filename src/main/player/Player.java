package main.player;

import main.Constants.Status;
import java.awt.*;

public class Player {
    PlayerAssets assets;
    int x, y;
    int speed;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 8;
        this.assets = new PlayerAssets();
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public Image getSprite(Status status, int frame){
        return (assets.getSprite(status, frame));
    }
}
