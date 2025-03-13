package main.map;

import main.Constants;

import java.awt.*;
import java.util.HashMap;
import javax.imageio.ImageIO;
import java.io.File;


public class MapAssets {
    private final HashMap<Character, Image> tiles;

    public MapAssets() {
        tiles = new HashMap<>();
        loadImages();
    }

    private void loadImages() {
        try {
            tiles.put('0', ImageIO.read(new File(Constants.SPRITE_MAP_PATH + "Grass.png")));
            tiles.put('1', ImageIO.read(new File(Constants.SPRITE_MAP_PATH + "Tree.png")));
            tiles.put('C', ImageIO.read(new File(Constants.SPRITE_MAP_PATH + "pokeball.png")));
            tiles.put('E', ImageIO.read(new File(Constants.SPRITE_MAP_PATH + "exit.png")));
            tiles.put('V', ImageIO.read(new File(Constants.SPRITE_MAP_PATH + "vilain.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Image getTile(char c) {
        return tiles.get(c);
    }
}
