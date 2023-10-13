package com.jgegroup.GameConfig;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;


public class Map implements MapWriter {

  private String current_map;

  private String path;
  private int horizontal_length;

  private int vertical_length;
  private int ArrayMap [][];



  public Map(String map_name, int horizontal_length, int vertical_length) {
    this.current_map= map_name;
    this.path = "res/maps/" + map_name;
    this.horizontal_length = horizontal_length;
    this.vertical_length = vertical_length;
    this.ArrayMap = new int[horizontal_length][vertical_length];
  }
  public String getPath() {
    return path;
  }


  @Override
  public void loadBasicMap() {
    for (int i=0; i<horizontal_length; i++) {
      for (int j=0; j<vertical_length; j++){
        ArrayMap[i][j] = 0;
      }
    }
  }

  @Override
  public void readMap() {

  }

  @Override
  public void resetMap() {

  }

  @Override
  public void saveMap() {
    try {
      FileWriter writer = new FileWriter(path);

      for (int i=0; i<horizontal_length; i++) {
        for (int j=0; j<vertical_length; j++) {
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

    while (column < horizontal_length && row < vertical_length) {
      System.out.println("col:" + column + "row:" + row );
      Color color = ArrayMap[column][row] == 0 ? Color.GREEN : Color.BLUE;
      painter.setFill(color);
      painter.fillRect(column * 2, row * 2, 2, 2);

      column++;
      if (column == horizontal_length) {

        column = 0;
        row++;
      }
    }
  }

  @Override
  public void changeTile() {

  }

  @Override
  public void changeTileHorizontally() {

  }
}
