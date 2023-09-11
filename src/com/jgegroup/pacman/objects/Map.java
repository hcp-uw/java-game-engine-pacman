package com.jgegroup.pacman.objects;

import com.jgegroup.pacman.MainScene;
import com.jgegroup.pacman.legacy.Position;
import com.jgegroup.pacman.objects.immovable.Path;
import com.jgegroup.pacman.objects.immovable.Tile;
//import com.jgegroup.pacman.legacy.consumables.Consumable;
import java.awt.*;
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
    private Position pacman_spawn_position = new Position(40, 40);
    private Position ghost_spawn_position = new Position(40, 900);

    private  Canvas canvas = new Canvas(MainScene.RESOLUTION_HORIZONTAL, MainScene.RESOLUTION_VERTICAL); // tool
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D(); // tool within tool(canvas)
    public Tile[] tileType = new Tile[2]; // Array of Tile object. For instant Tile[0] is object  floor, Tile[1] is object wall
    public  int[][] mapArray2D = new int[MainScene.NUMBER_OF_TILE_COLUMN][MainScene.NUMBER_OF_TILE_ROW];


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

    /** @@Author: Noah
     * Gets the objects that are on the map
     * Throws no exceptions
     * @return map instance objects
     * Takes in nothing
     */
//    public HashMap<Position, Consumable> getObjects() { return Map_Instance.objects; }


    public void createMap(/*Map Context*/) {
        loadTileImage(tileType);
        getMap(mapArray2D);
        drawMap(tileType, mapArray2D);
        //drawDot();
        extractMapToBoard(mapArray2D, tileType);
    }

    public  void loadTileImage(Tile[] tile) {
      Image floor = new Image("tiles/floor.png");
      tile[0]  = new Tile(floor);
      tile[0].setCollisionOn(false);

      Image wall = new Image("tiles/wall.png");
      tile[1] = new Tile(wall);
      tile[1].setCollisionOn(true);
   }




  /** @@Author: Tung
   * A map context reader, read from "res/maps/map1.txt"
   * Throws IOException when streams cannot be made or cannot be read from
   * @write tile position to 2D array.
   * Takes in tile positions
   */
   public void getMap(int[][] tiles) {
     try {
       InputStream is = getClass().getResourceAsStream("/maps/map1.txt");
       BufferedReader br = new BufferedReader(new InputStreamReader(is));

       int column = 0;
       int row = 0;

       while (column < MainScene.NUMBER_OF_TILE_COLUMN && row < MainScene.NUMBER_OF_TILE_ROW) {
           String mapLine = br.readLine();
           while (column < MainScene.NUMBER_OF_TILE_COLUMN) {
               String numbers[] = mapLine.split(" ");
               int num = Integer.parseInt(numbers[column]);
               tiles[column][row] = num;
               column++;
           }
           column = 0;
           row++;
       }


//         String mapLine = br.readLine();
//         while (row < MainScene.NUMBER_OF_TILE_ROW) {
//           String numbers[] = mapLine.split(" "); // ["n"] from n n n
//           int num = Integer.parseInt(numbers[row]); // ["1"] --> to integer
//           tiles[column][row] = num;
//           row++;
//         }
//         row = 0;
//         column++;
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
   public void drawMap(Tile[] tileType, int[][] tilePositions){
       int row = 0;
       int column = 0;
       int x = 0;
       int y = 0;

       while (column < MainScene.NUMBER_OF_TILE_COLUMN && row < MainScene.NUMBER_OF_TILE_ROW) {
           int tile_number = tilePositions[column][row]; // Use tilePositions to get the tile number
           graphicsContext.drawImage(tileType[tile_number].getImage(), x, y, MainScene.TILE_SIZE , MainScene.TILE_SIZE);
           column++;
           x += MainScene.TILE_SIZE;

           if (column == MainScene.NUMBER_OF_TILE_COLUMN) {
               column = 0;
               row++;
               x = 0;
               y += MainScene.TILE_SIZE;
           }
       }
//      while(column < tilePositions[0].length){
//        while(row < tilePositions.length){
//          graphicsContext.drawImage(tileType[tilePositions[row][column]].getImage(), x, y , MainScene.TILE_SIZE , MainScene.TILE_SIZE);
//          row++;
//          x += MainScene.TILE_SIZE;
//        }
//        row = 0;
//        x = 0;
//        column++;
//        y += MainScene.TILE_SIZE;
//      }
   }


  public void drawDot(GraphicsContext graphicsContext) {
    int dotSize = 8;
    graphicsContext.setFill(Color.YELLOW);
    for (int x = 0; x < MainScene.NUMBER_OF_TILE_COLUMN; x++) {
      for (int y = 0; y < MainScene.NUMBER_OF_TILE_ROW; y++) {
        if (mapArray2D[x][y] == 0) {
          int xCoordinate = x * MainScene.TILE_SIZE + (MainScene.TILE_SIZE / 2) - (dotSize / 2);
          int yCoordinate = y * MainScene.TILE_SIZE + (MainScene.TILE_SIZE / 2) - (dotSize / 2);
          graphicsContext.fillOval(xCoordinate, yCoordinate, dotSize, dotSize);
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

   public Position getPacmanSpawn() {
       return pacman_spawn_position;
   }

   public Position getGhostSpawn() {
       return ghost_spawn_position;
   }
}

