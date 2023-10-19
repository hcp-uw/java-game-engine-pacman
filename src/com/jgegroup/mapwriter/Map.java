package com.jgegroup.mapwriter;

import java.io.FileWriter;
import java.io.IOException;


public class Map {

  private String map_name;

  private String path;
  private int horizontal_length;

  private int vertical_length;
  private int ArrayMap [][];



  public Map(String map_name, int horizontal_length, int vertical_length) {
    this.map_name = map_name;
    this.path = "res/maps/" + map_name;
    this.horizontal_length = horizontal_length;
    this.vertical_length = vertical_length;
    this.ArrayMap = new int[horizontal_length][vertical_length];
    loadBaseMap();
    writeMap();
  }

  public void loadBaseMap() {
    for (int i=0; i<horizontal_length; i++) {
      for (int j=0; j<vertical_length; j++){
        ArrayMap[i][j] = 0;
      }
    }
  }
  public void writeMap() {
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

  public void changeTile(int x, int y, int TileType) {
  }

  public void changeBunchOfHorizontalTile(int from, int to, int TileType, int row) {

  }
  public String getPath() {
    return path;
  }
}
