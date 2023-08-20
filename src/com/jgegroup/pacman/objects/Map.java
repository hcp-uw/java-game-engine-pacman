package com.jgegroup.pacman.objects;

import com.jgegroup.pacman.Scene;
import com.jgegroup.pacman.objects.immovable.*;
import com.jgegroup.pacman.objects.immovable.consumables.Consumable;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import com.jgegroup.pacman.objects.characters.Pacman;


public class Map {
    private  HashMap<Position, Tile> tiles;
    private  HashMap<Position, Consumable> objects;
    private static Map Map_Instance;
    private Position pacmanSpawn = new Position(40, 40);
    private Position ghostSpawn = new Position(40, 900);

    private  Canvas canvas = new Canvas(Scene.RESOLUTION_HORIZONTAL, Scene.RESOLUTION_VERTICAL); // tool
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D(); // tool within tool(canvas)
    public Tile[] tileType = new Tile[2]; // Array of Tile object. For instant Tile[0] is object  floor, Tile[1] is object wall
    public  int[][] mapArray = new int[Scene.NUMBER_OF_TILE_LENGTH][Scene.NUMBER_OF_TILE_WIDTH];


//    int tilePosition [] [];
//    Tile[] tile;
    public Map(/*Map Context*/){
        objects = new HashMap<>();
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
    public HashMap<Position, Consumable> getObjects() { return Map_Instance.objects; }


    public void createMap(/*Map Context*/) {
        loadTileImage(tileType);
        getMap(mapArray);
        drawMap(tileType, mapArray);
        drawDot();
        extractMapToBoard(mapArray, tileType);
    }
    public  void loadTileImage(Tile[] tile) {
      Image floor = new Image("tiles/floor.png");
      tile[0]  = new Path(floor);

      Image wall = new Image("tiles/wall.png");
      tile[1] = new Tile(wall);
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
       while (row < Scene.NUMBER_OF_TILE_LENGTH) {
         /** ==> read the whole row */
         String mapLine = br.readLine();
         /** ==> read every element in the row, put elements in array */
         while (column < Scene.NUMBER_OF_TILE_WIDTH) {
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

  /** @@Author: Tung, Noah
   * draw map method
   * Throws no exception
   * @return nothing
   * Takes in tile types and tile positions
   */
   public void drawMap(Tile[] tileType, int[][] tilePositions){
     /** ==> first create a canvas(image) and graphics context*/
      int row = 0;
      int column = 0;
      int x = 0;
      int y = 0;
     /** ==>loop top to bottom, left column to right column*/
      while(row < tilePositions.length){
        while(column < tilePositions[0].length){
          /** ==> use GraphicsContext to draw the canvas, the type of image to draw is called from tilePosition[][]*/
          graphicsContext.drawImage(tileType[tilePositions[row][column]].getImage(), x, y , Scene.TILE_SIZE , Scene.TILE_SIZE);
          column++;
          x+= Scene.TILE_SIZE;
        }
        /** ==> move pointer back to first column, next under row*/
        column = 0;
        x=0;
        row++;
        y += Scene.TILE_SIZE;
      }
   }
   public void drawDot() {
       int dotSize = 8;

       graphicsContext.setFill(Color.YELLOW);
       for (int x = 0; x < Scene.NUMBER_OF_TILE_LENGTH; x++) {
           for (int y = 0; y < Scene.NUMBER_OF_TILE_WIDTH; y++) {
               if (mapArray[x][y] == 0) {
                   int xCoordinate = y * Scene.TILE_SIZE + (Scene.TILE_SIZE/2) - (dotSize/2);
                   int yCoordinate = x * Scene.TILE_SIZE + (Scene.TILE_SIZE/2) - (dotSize/2);
                   graphicsContext.fillRect(xCoordinate, yCoordinate, dotSize, dotSize);
               }
           }
       }
   }
   public void drawPacman(Pacman pacman){
     graphicsContext.setFill(Color.RED);
     Position pos = pacman.getPosition();
     int x = pos.getX(), y = pos.getY();
     graphicsContext.fillRect(x, y, 20, 20);
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
       return pacmanSpawn;
   }

   public Position getGhostSpawn() {
       return ghostSpawn;
   }
}

