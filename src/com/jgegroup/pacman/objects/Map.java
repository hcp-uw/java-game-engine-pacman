package com.jgegroup.pacman.objects;

import com.jgegroup.pacman.GameScene;
import com.jgegroup.pacman.objects.immovable.*;
import com.jgegroup.pacman.objects.immovable.consumables.Consumable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Map {
    private static HashMap<Position, Tile> tiles;
    private static HashMap<Position, Consumable> objects;
    private static Map instance;

    private static Canvas canvas;

//    int tilePosition [] [];
//    Tile[] tile;
    public Map(/*Map Context*/){
        objects = new HashMap<>();
        tiles = new HashMap<>();
//        tile = new Tile[3];
//        tilePosition = new int[GameScene.NUMBER_OF_TILE_LENGTH] [GameScene.NUMBER_OF_TILE_WIDTH];
        createMap(/*Map Context*/);
    }

    /** @@Author: Noah
     * Creates a map instance if it's not already created
     * Throws no exceptions
     * @return Map singleton
     * Takes no parameters
     */
    public static Map getMapInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }

    /** @@Author: Noah
     * Gets the tiles of the map from the object
     * Throws no exceptions
     * @return map instance tiles
     * Takes in nothing
     */
    public static HashMap<Position, Tile> getTiles() { return instance.tiles; }

    public static Canvas getCanvas() { return instance.canvas; }

    /** @@Author: Noah
     * Gets the objects that are on the map
     * Throws no exceptions
     * @return map instance objects
     * Takes in nothing
     */
    public static HashMap<Position, Consumable> getObjects() { return instance.objects; }


    public void createMap(/*Map Context*/) {
        Tile[] tileTypes = new Tile[2];
        int[][] tiles = new int[GameScene.NUMBER_OF_TILE_LENGTH][GameScene.NUMBER_OF_TILE_WIDTH];
        loadTileImage(tileTypes);
        getMap(tiles);
        drawMap(tileTypes, tiles);
        extractMapToBoard(tiles, tileTypes);
    }
    public  void loadTileImage(Tile[] tile) {
      Image floor = new Image("tiles/floor.png");
      tile[0]  = new Path(floor);

      Image wall = new Image("tiles/wall.png");
      tile[1] = new Tile(wall);
   }

  /** @@Author: Tung, Noah
   * draw map method
   * Throws no exception
   * @return nothing
   * Takes in tile types and tile positions
   */
   public void drawMap(Tile[] tileTypes, int[][] tilePositions){
     /** ==> first create a canvas(image) and graphics context*/
      canvas = new Canvas(GameScene.RESOLUTION_HORIZONTAL, GameScene.RESOLUTION_VERTICAL);
      GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
      int row = 0;
      int column = 0;
      int x = 0;
      int y = 0;
     /** ==>loop top to bottom, left column to right column*/
      while(row < tilePositions.length){
        while(column < tilePositions[0].length){
          /** ==> use GraphicsContext to draw the canvas, the type of image to draw is called from tilePosition[][]*/
          graphicsContext.drawImage(tileTypes[tilePositions[row][column]].getImage(), x, y , GameScene.tileSize ,GameScene.tileSize);
          column++;
          x+= GameScene.tileSize;
        }
        /** ==> move pointer back to first column, next under row*/
        column = 0;
        x=0;
        row++;
        y += GameScene.tileSize;
      }
   }

  /** @@Author: Tung
   * A map context reader
   * Throws IOException when streams cannot be made or cannot be read from
   * @write tile position to 2D array.
   * Takes in tile positions
   */
   public void getMap(int[][] tiles) {
     try {
       /** ==> first create input stream and reader*/
       InputStream is = getClass().getResourceAsStream("/maps/map1.txt");
       BufferedReader br = new BufferedReader(new InputStreamReader(is));

       int column = 0;
       int row = 0;

       /** ==> create loop read from top to bottom */
       while (row < GameScene.NUMBER_OF_TILE_LENGTH) {
         /** ==> read the whole row */
         String mapLine = br.readLine();
         /** ==> read every element in the row, put elements in array */
         while (column < GameScene.NUMBER_OF_TILE_WIDTH) {
           String numbers[] = mapLine.split(" "); // ["n"] from n n n
           int num = Integer.parseInt(numbers[column]); // ["1"] --> to integer
           tiles[row][column] = num;
           column++;
         }
         /** ==> move pointer to next row */
         column = 0;
         row++;
       }
       br.close();
     } catch (Exception e) {
         System.err.println("Error occurred while reading in the map file");
         e.printStackTrace();
     }
   }

   public void extractMapToBoard(int[][] tiles, Tile[] tileTypes) {
       for (int x = 0; x < tiles.length; x++) {
           for (int y = 0; y < tiles[x].length; y++) {
               Position pos = new Position(x,y);
               int tileType = tiles[x][y];
               instance.tiles.put(pos, tileTypes[tileType]);
           }
       }
   }
}

