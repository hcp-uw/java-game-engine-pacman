package com.jgegroup.pacman.objects;

import com.jgegroup.pacman.GameScene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UI {
  GameScene gameScene;
  Font font;


  public UI (GameScene gameScene) {
    this.gameScene = gameScene;
    font =  Font.font("Arial", FontWeight.BOLD, 40);
  }

  public void redraw(GraphicsContext graphicsContext) {
    graphicsContext.clearRect(0, 0, gameScene.RESOLUTION_HORIZONTAL, gameScene.RESOLUTION_VERTICAL);
    graphicsContext.setFont(font);
    displayLives(graphicsContext);
    displayPoints(graphicsContext);
  }

  public void displayLives (GraphicsContext graphicsContext) {
    graphicsContext.setFill(Color.RED);
    graphicsContext.fillText("Lives: " + gameScene.getPacLives(), 0, 40);

  }
  public void displayPoints (GraphicsContext graphicsContext) {
    graphicsContext.setFill(Color.WHITE);
    graphicsContext.fillText("Points: " + gameScene.getPoints(), 0, 80);
  }
  public void displayGameFinish(GraphicsContext graphicsContext) {
    graphicsContext.clearRect(0, 0, gameScene.RESOLUTION_HORIZONTAL, gameScene.RESOLUTION_VERTICAL);
    Font endFont = Font.font("Arial", FontWeight.EXTRA_BOLD, 60);
    graphicsContext.setFont(endFont);
    graphicsContext.setFill(Color.GREEN);
    graphicsContext.fillText("Game OVER", gameScene.RESOLUTION_HORIZONTAL / 4, gameScene.RESOLUTION_VERTICAL / 2);

  }
}
