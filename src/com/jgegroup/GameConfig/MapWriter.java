package com.jgegroup.GameConfig;

import javafx.scene.canvas.GraphicsContext;

public interface  MapWriter {
  // Load the basic map with all zero tiles. Can be use when creating new map, or reset map back to default.
  void loadBasicMap();
  //Read the existing txt map file. Update the 2D map array.


  // Reset map to default (All zero).
  void resetMap();

  // Save the current map to a txt file.
  void saveMap();
}
