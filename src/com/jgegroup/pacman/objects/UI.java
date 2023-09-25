package com.jgegroup.pacman.objects;

import com.jgegroup.pacman.MainScene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UI {
  MainScene mainScene;
  Font font;


  public UI (MainScene mainScene) {
    this.mainScene = mainScene;
    font =  Font.font("Arial", FontWeight.BOLD, 40);
  }

  public void redraw(GraphicsContext graphicsContext) {
    graphicsContext.clearRect(0, 0, mainScene.RESOLUTION_HORIZONTAL, mainScene.RESOLUTION_VERTICAL);
    graphicsContext.setFont(font);
    displayLives(graphicsContext);
    displayPoints(graphicsContext);
  }

  public void displayLives (GraphicsContext graphicsContext) {
    graphicsContext.setFill(Color.RED);
    graphicsContext.fillText("Lives: " + mainScene.getPacLives(), 0, 40);

  }
  public void displayPoints (GraphicsContext graphicsContext) {
    graphicsContext.setFill(Color.WHITE);
    graphicsContext.fillText("Points: " + mainScene.getPoints(), 0, 80);
  }
  public void displayGameFinish(GraphicsContext graphicsContext) {
    graphicsContext.clearRect(0, 0, mainScene.RESOLUTION_HORIZONTAL, mainScene.RESOLUTION_VERTICAL);
    Font endFont = Font.font("Arial", FontWeight.EXTRA_BOLD, 60);
    graphicsContext.setFont(endFont);
    graphicsContext.setFill(Color.GREEN);
    graphicsContext.fillText("Game OVER", mainScene.RESOLUTION_HORIZONTAL / 4, mainScene.RESOLUTION_VERTICAL / 2);

  }
}
