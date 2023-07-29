package com.jgegroup.pacman;

import com.jgegroup.pacman.objects.Map;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;


public class GameScene implements Runnable, javafx.event.EventHandler<javafx.scene.input.KeyEvent> {
  public static final int TILE_SIZE = 32;
  public static final int NUMBER_OF_TILE_WIDTH = 20;
  public static final int NUMBER_OF_TILE_LENGTH = 28;

  public static final int RESOLUTION_HORIZONTAL = TILE_SIZE * NUMBER_OF_TILE_WIDTH; // 768
  public static final int RESOLUTION_VERTICAL = TILE_SIZE * NUMBER_OF_TILE_LENGTH; // 1024
  Scene gameScene;
  StackPane stackPane;

  Canvas layer_1;
  Canvas layer_2;


  static GraphicsContext layer_1_paint;
  static GraphicsContext layer_2_paint;
  static Map map;


  static int a = 300;
  static int b = 300;
  static int c = 0;

  Thread gameThread;

  GameScene() {
    map = Map.getMapInstance();
    map.createMap();
    stackPane = new StackPane();
    gameScene = new Scene(stackPane, RESOLUTION_HORIZONTAL, RESOLUTION_VERTICAL, Color.BLACK);
    layer_1 = map.getCanvas() ;
    layer_2 = new Canvas(RESOLUTION_HORIZONTAL, RESOLUTION_VERTICAL);
    layer_1_paint = layer_1.getGraphicsContext2D();
    layer_2_paint = layer_2.getGraphicsContext2D();
    addCanvasLayer(layer_1);
    addCanvasLayer(layer_2);
    gameScene.setOnKeyPressed(this);
  }
  public void addCanvasLayer(Canvas canvas) {
    stackPane.getChildren().add(canvas);
  }


  public void startThread() {
    gameThread = new Thread(this);
    init();
    gameThread.start();
  }
  @Override
  public void run() {
    long FPS = 60;
    long targetTimePerFrame = 1000000000 / FPS;
    long lastTime = System.nanoTime();
    long currentTime;
    long elapsedTime;
    long sleepTime;

    while(true) { // or gameThread != null
      update();
      redraw();
      currentTime = System.nanoTime();
      elapsedTime = currentTime - lastTime;
      lastTime = currentTime;
      sleepTime = targetTimePerFrame - elapsedTime;
      if (sleepTime > 0) {
        try {
          Thread.sleep(sleepTime / 1000000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

  }
  public void init() {

  }
  public void update() {
    c += 1;
    System.out.println("a is now at:" + a);


  }
  public void redraw() {
   // layer_2_paint.clearRect(a - 1 , a - 1, 20, 20);
    layer_2_paint.setFill(Color.WHITE);
    layer_2_paint.fillRect(a, b, 20, 20);



  }

  @Override
  public void handle(javafx.scene.input.KeyEvent  keyEvent) {
    KeyCode keyCode = keyEvent.getCode();

    // Update the position of the rectangle based on the pressed key
    switch (keyCode) {
      case LEFT:
        a -= 5;
        break;
      case RIGHT:
        a += 5;
        break;
      case UP:
        b -= 5;
        break;
      case DOWN:
        b += 5;
        break;
      default:
        break;
    }
  }
}
