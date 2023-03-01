package com.jgegroup.pacman;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GameScene {
  final int tileSize = 32;
  final int NUMBER_OF_TILE_WIDTH = 24;
  final int NUMBER_OF_TILE_LENGTH = 32;

  final int RESOLUTION_HORIZONTAL = tileSize * NUMBER_OF_TILE_WIDTH; // 768
  final int RESOLUTION_VERTICAL = tileSize * NUMBER_OF_TILE_LENGTH; // 1024

  Scene gameScene;

  StackPane stackPane;

  GraphicsContext graphicsContext;

  GameScene(){
    stackPane = new StackPane();
    gameScene = new Scene(stackPane, RESOLUTION_HORIZONTAL, RESOLUTION_VERTICAL, Color.BLACK);
  }
}
