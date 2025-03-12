package main;

public final class Constants {
    private Constants() {}

    public enum Status {
        UP, DOWN, LEFT, RIGHT;
    }

    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;
    public static final int IMG_PIXEL_SIZE = 64;

    public static final String TITLE = "Poke Java";

    public static final String MAP_PATH = "src/ressources/map/";
    public static final String MAP_NAME = "map1.ber";

    public static final String SPRITE_MAP_PATH = "src/ressources/img/map/";
    public static final String SPRITE_PLAYER_PATH = "src/ressources/img/player/";
}
