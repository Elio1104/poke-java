package main.map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.Image;

import main.Constants;

public class Map {
    private char[][] grid;
    private MapAssets assets;
    private int width, height;

    public Map() {
        loadMap();
    }

    private void loadMap() {
        String path = Constants.MAP_PATH + Constants.MAP_NAME;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            width = 0;
            height = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() > width)
                    width = line.length();
                height++;
            }

            grid = new char[width][height];
            reader.close();

            reader = new BufferedReader(new FileReader(path));

            for (int y = 0; y < height; y++) {
                line = reader.readLine();
                for (int x = 0; x < line.length(); x++) {
                    grid[x][y] = line.charAt(x);
                }
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPlayerX() {
    	for (int i = 0; i < grid.length; i++) {
    		for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 'P') {
                    return i;
                }
            }
    	}
        return -1;
    }

    public int getPlayerY() {
        for (char[] chars : grid) {
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == 'P') {
                    return j;
                }
            }
        }
        return -1;
    }

    public Image getTile(int x, int y) {
        return assets.getTile(grid[x][y]);
    }
}
