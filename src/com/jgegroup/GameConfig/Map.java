package com.jgegroup.GameConfig;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Scanner;
import java.io.*;


public class Map implements MapWriter {

  private String map_name;

  private String path;
  private int column;

  private int row;
  private int ArrayMap [][];


  //New MAP
  public Map(String map_name, int column, int row) {
    this.map_name = map_name;
    this.path = "res/maps/" + map_name;
    this.column = column;
    this.row = row;
    this.ArrayMap = new int[column][row];
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
    System.out.println("x: " + column + "y: " + row);
  }



  @Override
  public void loadBasicMap() {
    for (int i = 0; i < column; i++) {
      for (int j = 0; j < row; j++) {
        ArrayMap[i][j] = 0;
      }
    }
  }

  @Override
  public void resetMap() {

  }

  @Override
  public void saveMap() {
    try {
      FileWriter writer = new FileWriter(path);
      for (int i = 0; i< column; i++) {
        for (int j = 0; j< row; j++) {
          writer.write(ArrayMap[i][j] + " ");
        }
        writer.write("\n");
      }
      writer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void showMap(GraphicsContext painter) {
    int row = 0;
    int column = 0;

    while (column < this.column && row < this.row) {
      System.out.println("col:" + column + "row:" + row );
      Color color = ArrayMap[column][row] == 0 ? Color.GREEN : Color.BLUE;
      painter.setFill(color);
      painter.fillRect(column * 2, row * 2, 2, 2);

      column++;
      if (column == this.column) {

        column = 0;
        row++;
      }
    }
  }

  public String showMapContent() {
    StringBuilder mapContent = new StringBuilder();
    for (int i = 0; i < column; i++) {
      for (int j = 0; j < row; j++) {
        mapContent.append(ArrayMap[i][j]);
        mapContent.append(" ");
      }
      mapContent.append("\n");
    }
    return mapContent.toString();
  }

  @Override
  public void changeTile() {

  }

  @Override
  public void changeTileHorizontally() {

  }

  public void constructArrayMap() {
    try (Scanner scanner = new Scanner(new File(path))) {
      int row = 0;
      while (scanner.hasNextLine() && row < this.row) {
        String mapLine = scanner.nextLine();
        String[] numbers = mapLine.split(" ");
        for (int column = 0; column < this.column && column < numbers.length; column++) {
          int num = Integer.parseInt(numbers[column]);
          ArrayMap[column][row] = num;
        }
        row++;
      }
    } catch (FileNotFoundException e) {
      System.err.println("Error occurred while reading the map file: " + path);
      e.printStackTrace();
    }
  }


  public String getPath() {
    return path;
  }
}
