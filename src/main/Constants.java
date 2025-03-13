package main;

public final class Constants {
    private Constants() {}

    public enum Status {
        UP, DOWN, LEFT, RIGHT
    }

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    public static final int IMG_PIXEL_SIZE = 64;

    public static final String TITLE = "Poke Java";

    public static final String MAP_PATH = "src/ressources/map/";
    public static final String MAP_NAME = "map2.ber";

    public static final String IMG_PATH = "src/ressources/img/";

    public static final String SPRITE_MAP_PATH = IMG_PATH + "map/";
    public static final String SPRITE_PLAYER_PATH = IMG_PATH + "player/";
}
