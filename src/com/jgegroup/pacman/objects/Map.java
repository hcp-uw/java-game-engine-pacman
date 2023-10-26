package com.jgegroup.pacman.objects;

import com.jgegroup.GameConfig.config.Settings;
import com.jgegroup.pacman.GameScene;
import com.jgegroup.pacman.legacy.Position;
import com.jgegroup.pacman.objects.immovable.Tile;
//import com.jgegroup.pacman.legacy.consumables.Consumable;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javax.management.ImmutableDescriptor;


public class Map {
    private HashMap<Position, Tile> tiles;
    //    private  HashMap<Position, Consumable> objects;
    private static Map Map_Instance;
    private Canvas canvas = new Canvas(GameScene.RESOLUTION_HORIZONTAL, GameScene.RESOLUTION_VERTICAL); // tool
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D(); // tool within tool(canvas)
    public Tile[] tileType = new Tile[4]; // Array of Tile object. For instant Tile[0] is object  floor, Tile[1] is object wall
    public int[][] mapArray2D;

    public Map(/*Map Context*/) {
        mapArray2D = new int[GameScene.NUMBER_OF_TILE_COLUMN][GameScene.NUMBER_OF_TILE_ROW];
    }

    /**
     * @return Map singleton
     * Takes no parameters
     * @@Author: Noah
     * Creates a map instance if it's not already created
     * Throws no exceptions
     */
    public static Map getMapInstance() {
        if (Map_Instance == null) {
            Map_Instance = new Map();
        }
        return Map_Instance;
    }


    /**
     * @return map instance objects
     * Takes in nothing
     * @@Author: Noah, Tung
     * Load tile image for map, read map from txt file, and extract map to board
     * Throws no exceptions
     */
    public void createMap(Settings settings) {
        Image floor = settings.selectedFloorImage() ?
                settings.getFloorImage() :
                new Image("tiles/floor tiles/floor.png");
        Image wall = settings.selectedWallImage() ?
                settings.getWallImage() :
                new Image("tiles/wall tiles/wall.png");
        loadTileImage(floor, wall);
        System.out.println(settings.selectedMap() ? settings.getMapPath() : "");
        readMap(settings.selectedMap() ? settings.getMapPath() : "res/maps/map1.txt");
    }


    /**
     * @return nothing
     * Takes in nothing
     * @@Author: Tung
     * Load image for Map
     * Throws no exception
     */
    public void loadTileImage(Image floor, Image wall) {
        tileType[0] = new Tile(floor);
        tileType[0].setCollisionOn(false);

        tileType[1] = new Tile(wall);
        tileType[1].setCollisionOn(true);

        tileType[2] = new Tile(floor); // Dots
        tileType[2].setCollisionOn(false);

        tileType[3] = new Tile(floor); // Big Dots
        tileType[3].setCollisionOn(false);
    }


    /**
     * @@Author: Tung
     * A map context reader, read from "res/maps/map1.txt".
     * Throws IOException when streams cannot be made or cannot be read from
     * @write a 2D array mapArray2D.
     * Takes in tile positions
     */
    public void readMap(String path) {
        try {
            BufferedReader br = null;
            Path pathtofile = Paths.get(path);
            br = Files.newBufferedReader(pathtofile);

            for (int y = 0; y < GameScene.NUMBER_OF_TILE_ROW; y++) {
                String[] numbers = br.readLine().split(" ");
                for (int x = 0; x < GameScene.NUMBER_OF_TILE_COLUMN; x++) {
                    mapArray2D[x][y] = Integer.parseInt(numbers[x]);
                }
            }
            br.close();
        } catch (Exception e) {
            System.err.println("Error occurred while reading in the map file");
            e.printStackTrace();
        }
    }


    /**
     * @@Author: Tung, Noah
     * A function draw map
     * Throws no exception
     */
    public void drawMap(GraphicsContext gamePainter) {
        for (int x = 0; x < GameScene.NUMBER_OF_TILE_COLUMN; x++) {
            for (int y = 0; y < GameScene.NUMBER_OF_TILE_ROW; y++) {
                int tileNum = mapArray2D[x][y];
                gamePainter.drawImage(
                        tileType[tileNum].getImage(),
                        x * GameScene.TILE_SIZE,
                        y * GameScene.TILE_SIZE,
                        GameScene.TILE_SIZE,
                        GameScene.TILE_SIZE
                );
            }
        }
    }

    /**
     * @@Author: Yuzhen, Tung
     * Draw dots
     * Throws no exception
     */
    public void drawDot(GraphicsContext gamePainter) {
        int dotSize = 8;
        gamePainter.setFill(Color.YELLOW);
        for (int i = 0; i < GameScene.NUMBER_OF_TILE_ROW; i++) {
            for (int j = 0; j < GameScene.NUMBER_OF_TILE_COLUMN; j++) {
                if (mapArray2D[j][i] == 0) {
                    int xCoord = j * GameScene.TILE_SIZE + (GameScene.TILE_SIZE / 2) - (dotSize / 2);
                    int yCoord = i * GameScene.TILE_SIZE + (GameScene.TILE_SIZE / 2) - (dotSize / 2);
                    gamePainter.fillOval(xCoord, yCoord, dotSize, dotSize);
                } else if (mapArray2D[j][i] == 3) {
                    int xCoord = j * GameScene.TILE_SIZE + (GameScene.TILE_SIZE / 2) - (dotSize);
                    int yCoord = i * GameScene.TILE_SIZE + (GameScene.TILE_SIZE / 2) - (dotSize);
                    gamePainter.fillOval(xCoord, yCoord, dotSize * 2, dotSize * 2);
                }
            }
        }
    }
}

