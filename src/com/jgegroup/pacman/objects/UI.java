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

  public void redraw(GraphicsContext gamePainter) {
    gamePainter.setFont(font);
    displayLives(gamePainter);
    displayPoints(gamePainter);
  }

  public void displayLives (GraphicsContext gamePainter) {
    gamePainter.setFill(Color.RED);
    gamePainter.fillText("Lives: " + gameScene.getPacLives(), 0, 40);

  }
  public void displayPoints (GraphicsContext gamePainter) {
    gamePainter.setFill(Color.WHITE);
    gamePainter.fillText("Points: " + gameScene.getPoints(), 0, 80);
  }
  public void displayGameFinish(GraphicsContext gamePainter) {
    Font endFont = Font.font("Arial", FontWeight.EXTRA_BOLD, 60);
    gamePainter.setFont(endFont);
    gamePainter.setFill(Color.GREEN);
    gamePainter.fillText("Game OVER", gameScene.RESOLUTION_HORIZONTAL / 4, gameScene.RESOLUTION_VERTICAL / 2);

  }
}
