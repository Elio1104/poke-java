package main.player;

import main.Constants.Status;
import main.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.EnumMap;

public class PlayerAssets {
    private final EnumMap<Status, Image[]> sprites;

    public PlayerAssets() {
        sprites = new EnumMap<>(Status.class);
        loadSprites();
    }

    private void loadSprites(){
        try {
            sprites.put(Status.UP, new Image[]{
                    ImageIO.read(new File(Constants.SPRITE_PLAYER_PATH + "Dawn_up1.png")),
                    ImageIO.read(new File(Constants.SPRITE_PLAYER_PATH + "Dawn_up2.png")),
                    ImageIO.read(new File(Constants.SPRITE_PLAYER_PATH + "Dawn_up3.png"))
            });
            sprites.put(Status.DOWN, new Image[]{
                    ImageIO.read(new File(Constants.SPRITE_PLAYER_PATH + "Dawn_down1.png")),
                    ImageIO.read(new File(Constants.SPRITE_PLAYER_PATH + "Dawn_down2.png")),
                    ImageIO.read(new File(Constants.SPRITE_PLAYER_PATH + "Dawn_down3.png"))
            });
            sprites.put(Status.LEFT, new Image[]{
                    ImageIO.read(new File(Constants.SPRITE_PLAYER_PATH + "Dawn_left1.png")),
                    ImageIO.read(new File(Constants.SPRITE_PLAYER_PATH + "Dawn_left2.png")),
                    ImageIO.read(new File(Constants.SPRITE_PLAYER_PATH + "Dawn_left3.png"))
            });
            sprites.put(Status.RIGHT, new Image[]{
                    ImageIO.read(new File(Constants.SPRITE_PLAYER_PATH + "Dawn_left1.png")),
                    ImageIO.read(new File(Constants.SPRITE_PLAYER_PATH + "Dawn_left2.png")),
                    ImageIO.read(new File(Constants.SPRITE_PLAYER_PATH + "Dawn_left3.png"))
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Image getSprite(Status status, int frame) {
        return sprites.get(status)[frame - 1];
    }
}
