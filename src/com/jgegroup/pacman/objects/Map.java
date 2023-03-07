package com.jgegroup.pacman.objects;

import com.jgegroup.pacman.GameScene;
import com.jgegroup.pacman.objects.immovable.Tile;
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

    public static Canvas canvas;

    int tilePosition [] [];
    Tile[] tile;
    public Map(/*Map Context*/){
        objects = new HashMap<>();
        tiles = new HashMap<>();
        tile = new Tile[3];
        tilePosition = new int[GameScene.NUMBER_OF_TILE_LENGTH] [GameScene.NUMBER_OF_TILE_WIDTH];
        createMap(/*Map Context*/);
    }

    /** @@Author: Noah
     * Creates a map instance if its not already created
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

    /** @@Author: Noah
     * Gets the objects that are on the map
     * Throws no exceptions
     * @return map instance objects
     * Takes in nothing
     */
    public static HashMap<Position, Consumable> getObjects() { return instance.objects; }


    public void createMap(/*Map Context*/) {
      loadTileImage();
      getMap();
      drawMap();
    }
    public  void loadTileImage() {
      Image floor = new Image("tiles/floor.png");
      tile[0]  = new Tile(floor);

      Image wall = new Image("tiles/wall.png");
      tile[1] = new Tile(wall);
   }

  /** @@Author: Tung
   * draw map method
   * Throws no exception
   * @return nothing
   * Takes in nothing
   */
   public void drawMap(){
     /** ==> first create input stream and reader*/
      canvas = new Canvas(GameScene.RESOLUTION_HORIZONTAL, GameScene.RESOLUTION_VERTICAL);
      GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
      int row = 0;
      int column = 0;
      int x = 0;
      int y = 0;
      while(row < GameScene.NUMBER_OF_TILE_LENGTH){
        while(column < GameScene.NUMBER_OF_TILE_WIDTH){
          System.out.println(tilePosition[row][column]);
          graphicsContext.drawImage(tile[tilePosition[row][column]].getImage(), x, y , GameScene.tileSize ,GameScene.tileSize);
          column++;
          x+= GameScene.tileSize;
        }
        column = 0;
        x=0;
        row++;
        y += GameScene.tileSize;
      }
   }

  /** @@Author: Tung
   * A map context reader
   * Throws exceptions when reading
   * @write tile position to 2D array.
   * Takes in nothing
   */
   public void getMap() {
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
         /** ==> read every elements in the row, put elements in array */
         while (column < GameScene.NUMBER_OF_TILE_WIDTH) {
           String numbers[] = mapLine.split(" "); // ["n"] from n n n
           int num = Integer.parseInt(numbers[column]); // ["1"] --> to integer
           tilePosition[row][column] = num;
           column++;
         }
         /** ==> move pointer to next row */
         column = 0;
         row++;
       }
       br.close();
     } catch (Exception e) {
     }
   }
}

