package com.jgegroup.pacman;

import com.jgegroup.pacman.objects.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;


public class Scene implements Runnable, javafx.event.EventHandler<javafx.scene.input.KeyEvent> {
  static long FPS = 60;
  static long targetTimePerFrame = 1000000000 / FPS;
  static long lastTime = System.nanoTime();
  static long currentTime;
  static long elapsedTime;
  static long sleepTime;
  public static final int TILE_SIZE = 32;
  public static final int NUMBER_OF_TILE_WIDTH = 20;
  public static final int NUMBER_OF_TILE_LENGTH = 28;

  public static final int RESOLUTION_HORIZONTAL = TILE_SIZE * NUMBER_OF_TILE_WIDTH; // 768
  public static final int RESOLUTION_VERTICAL = TILE_SIZE * NUMBER_OF_TILE_LENGTH; // 1024


  javafx.scene.Scene gameScene;
  StackPane stackPane;

  Canvas Layer_Lower;
  Canvas Layer_Upper;



  static GraphicsContext Layer_Lower_PaintComponent;
  static GraphicsContext Layer_Upper_PaintComponent;
  static Map map;


  static int x = 300;
  static int y = 300;
  static int c = 0;

  Thread gameThread;

  Scene() {
    map = Map.getMapInstance();
    map.createMap();
    stackPane = new StackPane();
    gameScene = new javafx.scene.Scene(stackPane, RESOLUTION_HORIZONTAL, RESOLUTION_VERTICAL, Color.BLACK);
    Layer_Lower = map.getCanvas() ;
    Layer_Upper = new Canvas(RESOLUTION_HORIZONTAL, RESOLUTION_VERTICAL);
    Layer_Lower_PaintComponent = Layer_Lower.getGraphicsContext2D();
    Layer_Upper_PaintComponent = Layer_Upper.getGraphicsContext2D();
    addCanvasLayer(Layer_Lower);
    addCanvasLayer(Layer_Upper);
    gameScene.setOnKeyPressed(this);
  }

  // startThread() -> run -> update() & redraw()
  // CORE
  public void startThread() {
    gameThread = new Thread(this);
    init();
    gameThread.start();
  }

  public void init() {

  }

  @Override
  public void run() {
    while (true) {
      redraw();
      FPSControl();
    }
  }

  public void update() {
    c += 1;
    System.out.println("a is now at:" + c);
  }
  public void redraw() {
    Layer_Upper_PaintComponent.clearRect(x - 5 , y - 5, RESOLUTION_HORIZONTAL, RESOLUTION_VERTICAL);
    Layer_Upper_PaintComponent.setFill(Color.WHITE);
    Layer_Upper_PaintComponent.fillRect(x, y, 20, 20);
  }


  // Functions

  @Override
  public void handle(javafx.scene.input.KeyEvent  keyEvent) {
    KeyCode keyCode = keyEvent.getCode();
    switch (keyCode) {
      case LEFT:
        x -= 5;
        break;
      case RIGHT:
        x += 5;
        break;
      case UP:
        y -= 5;
        break;
      case DOWN:
        y += 5;
        break;
      default:
        break;
    }
  }
  public void checkEat() {
  }
  public void addCanvasLayer(Canvas canvas) {
    stackPane.getChildren().add(canvas);
  }
  public void FPSControl() {
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




