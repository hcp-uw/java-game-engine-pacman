package com.jgegroup.pacman.objects;

import com.jgegroup.pacman.GameScene;
import com.jgegroup.pacman.legacy.Position;
import com.jgegroup.pacman.objects.immovable.Tile;
//import com.jgegroup.pacman.legacy.consumables.Consumable;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Map {
    private  HashMap<Position, Tile> tiles;
//    private  HashMap<Position, Consumable> objects;
    private static Map Map_Instance;
    private  Canvas canvas = new Canvas(GameScene.RESOLUTION_HORIZONTAL, GameScene.RESOLUTION_VERTICAL); // tool
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D(); // tool within tool(canvas)
    public Tile[] tileType = new Tile[4]; // Array of Tile object. For instant Tile[0] is object  floor, Tile[1] is object wall
    public int[][] mapArray2D = new int[GameScene.NUMBER_OF_TILE_COLUMN][GameScene.NUMBER_OF_TILE_ROW];


    public Map(/*Map Context*/){
//        objects = new HashMap<>();
        tiles = new HashMap<>();
    }

    /** @@Author: Noah
     * Creates a map instance if it's not already created
     * Throws no exceptions
     * @return Map singleton
     * Takes no parameters
     */
    public static Map getMapInstance() {
        if (Map_Instance == null) {
          Map_Instance = new Map();
        }
        return Map_Instance;
    }

    /** @@Author: Noah
     * Gets the tiles of the map from the object
     * Throws no exceptions
     * @return map instance tiles
     * Takes in nothing
     */
    public HashMap<Position, Tile> getTiles() { return Map_Instance.tiles; }

    public Canvas getCanvas() { return Map_Instance.canvas; }

    /** @@Author: Noah, Tung
     * Load tile image for map, read map from txt file, and extract map to board
     * Throws no exceptions
     * @return map instance objects
     * Takes in nothing
     */
    public void createMap(/*Map Context*/) {
        loadTileImage();
        readMap();
        extractMapToBoard(mapArray2D, tileType);
    }

    public void loadTileImage() {
      Image floor = new Image("tiles/floor.png");
      tileType[0]  = new Tile(floor);
      tileType[0].setCollisionOn(false);

      Image wall = new Image("tiles/wall.png");
      tileType[1] = new Tile(wall);
      tileType[1].setCollisionOn(true);

      tileType[2] = new Tile(floor);
      tileType[2].setCollisionOn(false);

      tileType[3] = new Tile(floor);
      tileType[3].setCollisionOn(false);
   }




  /** @@Author: Tung
   * A map context reader, read from "res/maps/map1.txt"
   * Throws IOException when streams cannot be made or cannot be read from
   * @write tile position to 2D array.
   * Takes in tile positions
   */
   public void readMap() {
     try {
       InputStream is = getClass().getResourceAsStream("/maps/map1.txt");
       BufferedReader br = new BufferedReader(new InputStreamReader(is));

       int column = 0;
       int row = 0;

       while (column < GameScene.NUMBER_OF_TILE_COLUMN && row < GameScene.NUMBER_OF_TILE_ROW) {
           String mapLine = br.readLine();
           while (column < GameScene.NUMBER_OF_TILE_COLUMN) {
               String numbers[] = mapLine.split(" ");
               int num = Integer.parseInt(numbers[column]);
               mapArray2D[column][row] = num;
               column++;
           }
           column = 0;
           row++;
       }
       br.close();
     } catch (Exception e) {
         System.err.println("Error occurred while reading in the map file");
         e.printStackTrace();
     }
   }



  /** @@Author: Tung, Noah
   * A function draw map
   * Throws no exception
   * @return nothing
   * Takes in tile types and tile positions
   */
   public void drawMap(GraphicsContext gamePainter){
       int row = 0;
       int column = 0;
       int x = 0;
       int y = 0;

       while (column < GameScene.NUMBER_OF_TILE_COLUMN && row < GameScene.NUMBER_OF_TILE_ROW) {
           int tile_number = mapArray2D[column][row]; // Use tilePositions to get the tile number
         gamePainter.drawImage(tileType[tile_number].getImage(), x, y, GameScene.TILE_SIZE , GameScene.TILE_SIZE);
           column++;
           x += GameScene.TILE_SIZE;

           if (column == GameScene.NUMBER_OF_TILE_COLUMN) {
               column = 0;
               row++;
               x = 0;
               y += GameScene.TILE_SIZE;
           }
       }
   }
  /** @@Author: Yuzhen, Tung
   * Draw dots
   * Throws no exception
   * @return nothing
   * Takes in tile types and tile positions
   */
  public void drawDot(GraphicsContext gamePainter) {
    int dotSize = 8;
    gamePainter.setFill(Color.YELLOW);
    for (int x = 0; x < GameScene.NUMBER_OF_TILE_COLUMN; x++) {
      for (int y = 0; y < GameScene.NUMBER_OF_TILE_ROW; y++) {
        if (mapArray2D[x][y] == 0) {
          int xCoordinate = x * GameScene.TILE_SIZE + (GameScene.TILE_SIZE / 2) - (dotSize / 2);
          int yCoordinate = y * GameScene.TILE_SIZE + (GameScene.TILE_SIZE / 2) - (dotSize / 2);
          gamePainter.fillOval(xCoordinate, yCoordinate, dotSize, dotSize);
        } else if (mapArray2D[x][y] == 3) {
            int xCoordinate = x * GameScene.TILE_SIZE + (GameScene.TILE_SIZE / 2) - (dotSize);
            int yCoordinate = y * GameScene.TILE_SIZE + (GameScene.TILE_SIZE / 2) - (dotSize);
          gamePainter.fillOval(xCoordinate, yCoordinate, dotSize * 2, dotSize * 2);
        }
      }
    }
  }


   public void extractMapToBoard(int[][] tiles, Tile[] tileTypes) {
       for (int x = 0; x < tiles.length; x++) {
           for (int y = 0; y < tiles[x].length; y++) {
               Position pos = new Position(x,y);
               int tileType = tiles[x][y];
               Map_Instance.tiles.put(pos, tileTypes[tileType]);
           }
       }
   }

}

