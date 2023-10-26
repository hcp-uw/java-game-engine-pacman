package com.jgegroup.GameConfig;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.Scanner;
import java.io.*;


public class Map implements MapWriter {

  public static final int DOT = 0;
  public static final int WALL = 1;
  public static final int FLOOR = 2;
  public static final int BIGDOT = 3;

  private String map_name;

  private String path;
  private int column;

  private int row;
  private int ArrayMap [][];


  //New MAP
  public Map(String map_name, int row, int column) {
    this.map_name = map_name;
    this.path = "res/maps/" + map_name;
    this.column = column;
    this.row = row;
    this.ArrayMap = new int[column][row];
    loadBasicMap();
    saveMap();
  }
  //Existing MAP
  public Map(String map_name) throws FileNotFoundException {
    this.map_name = map_name;
    this.path = "res/maps/" + map_name;
    // SCAN and get size of map.
    try (Scanner scanner = new Scanner(new File(path))) {
      int rowCount = 0;
      // Count column. Require pointer to be at first row, therefore rowCount++.
      String firstLine = scanner.nextLine();
      String[] columns = firstLine.split(" ");
      rowCount++;
      // Count row
      while (scanner.hasNextLine()) {
        scanner.nextLine();
        rowCount++;
      }
      this.column = columns.length;
      this.row = rowCount;
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    this.ArrayMap = new int[column][row];
    constructArrayMap();
    System.out.println("x: " + column + " y: " + row);
  }



  @Override
  public void loadBasicMap() {
    for (int i = 1; i < column - 1; i++) {
      for (int j = 1; j < row - 1; j++) {
        ArrayMap[i][j] = DOT;
      }
    }
    // Add walls on the border
    for (int i = 0; i < column; i++) {
      ArrayMap[i][0] = WALL;
      ArrayMap[i][row - 1] = WALL;
    }
    for (int j = 0; j < row; j++) {
      ArrayMap[0][j] = WALL;
      ArrayMap[column - 1][j] = WALL;
    }
  }

  @Override
  public void resetMap() {

  }

  @Override
  public void saveMap(){
    try {
      System.out.printf("Writing file %n");
      System.out.printf("Path is %s%n", path);
      FileWriter writer = new FileWriter(path);
      StringBuilder sb = new StringBuilder();
      for (int j = 0; j < row; j++) {
        for (int i = 0; i < column; i++) {
          sb.append((char) ('0' + ArrayMap[i][j]));
          if (i < column - 1)
            sb.append(' ');
        }
        sb.append('\n');
      }
      writer.write(sb.toString());
      writer.close();
      FileTime lastModifiedTime = FileTime.fromMillis(System.currentTimeMillis() + 3000);
        System.out.printf("Finished writing file %n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void constructArrayMap() {
    try (Scanner scanner = new Scanner(new File(path))) {

      for (int row = 0; row < this.row && scanner.hasNextLine(); row++) {
        String[] numbers = scanner.nextLine().split(" ");
        for (int column = 0; column < this.column; column++) {
          ArrayMap[column][row] = Integer.parseInt(numbers[column]);
        }
      }
    } catch (FileNotFoundException e) {
      System.err.println("Error occurred while reading the map file: " + path);
      e.printStackTrace();
    }
  }


  public String getPath() {
    return path;
  }

  public int[][] getArrayMap() {
    return ArrayMap;
  }

  public void setArrayMap(int[][] arrayMap) {
    this.ArrayMap = arrayMap;
  }
}
