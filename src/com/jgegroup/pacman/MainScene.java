package com.jgegroup.pacman;

import com.jgegroup.pacman.objects.Map;

import com.jgegroup.pacman.objects.characters.Ghost;
import com.jgegroup.pacman.objects.characters.Pac;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;

import java.util.Random;


public class MainScene implements Runnable{
  private Thread gameThread;
  private static long FPS = 60;
  private static long targetTimePerFrame = 1000000000 / FPS;
  private static long lastTime = System.nanoTime();
  private static long currentTime;
  private static long elapsedTime;
  private static long sleepTime;
  public static final int TILE_SIZE = 32;
  public static final int NUMBER_OF_TILE_COLUMN = 20;
  public static final int NUMBER_OF_TILE_ROW = 28;

  public static final int RESOLUTION_HORIZONTAL = TILE_SIZE * NUMBER_OF_TILE_COLUMN; // 768
  public static final int RESOLUTION_VERTICAL = TILE_SIZE * NUMBER_OF_TILE_ROW; // 1024
  public CollisionChecker collisionChecker = new CollisionChecker(this);
  public javafx.scene.Scene mainScene;

  private StackPane stackPane;

  private Canvas Layer_Lower;
  private Canvas Layer_Upper;
  private Canvas[] Layer_Ghosts;

  private static GraphicsContext Layer_Lower_PaintComponent;
  private static GraphicsContext Layer_Upper_PaintComponent;
  private static GraphicsContext[] Layer_Ghost_PaintComponents;
  public  Map map;
  private KeyHandler keyHandler;
  private Pac pac;
  private Ghost[] ghosts;
  private Color[] colors = {Color.RED, Color.BLUE, Color.BROWN, Color.GAINSBORO, Color.GHOSTWHITE};



  private static int x = 300;
  private static int y = 300;
  private static int c = 0;


  public MainScene(int ghostNumber) {
    map = Map.getMapInstance();
    map.createMap();
    stackPane = new StackPane();
    mainScene = new javafx.scene.Scene(stackPane, RESOLUTION_HORIZONTAL, RESOLUTION_VERTICAL, Color.BLACK);
    Layer_Lower = map.getCanvas() ;
    Layer_Upper = new Canvas(RESOLUTION_HORIZONTAL, RESOLUTION_VERTICAL);
    Layer_Lower_PaintComponent = Layer_Lower.getGraphicsContext2D();
    Layer_Upper_PaintComponent = Layer_Upper.getGraphicsContext2D();
    addCanvasLayer(Layer_Lower);
    Layer_Ghosts = new Canvas[ghostNumber];
    Layer_Ghost_PaintComponents = new GraphicsContext[ghostNumber];
    for (int i=0;i<ghostNumber;i++) {
      Layer_Ghosts[i] = new Canvas(RESOLUTION_HORIZONTAL, RESOLUTION_VERTICAL);
      Layer_Ghost_PaintComponents[i] = Layer_Ghosts[i].getGraphicsContext2D();
      addCanvasLayer(Layer_Ghosts[i]);
    }
    addCanvasLayer(Layer_Upper);
    keyHandler = new KeyHandler();
    mainScene.setOnKeyPressed(keyHandler);
    pac = new Pac(this, keyHandler);
    ghosts = new Ghost[ghostNumber];
    Random r = new Random();
    for (int i = 0; i < ghostNumber; i++) {
      int color_num = Math.abs(r.nextInt()) % colors.length;
      ghosts[i] = new Ghost(10, this, colors[color_num], pac);
    }
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
      update();
      redraw();
      controlFPS();
    }

  }

  public void update() {
    pac.update();
    for (Ghost g : ghosts) {
      g.update();
    }
  }
  public void redraw() {
    map.drawDot(Layer_Lower_PaintComponent);
    pac.redraw(Layer_Upper_PaintComponent);
    for (int i = 0; i < ghosts.length; i++) {
      ghosts[i].redraw(Layer_Ghost_PaintComponents[i]);
    }
  }


  // Functions
  public void addCanvasLayer(Canvas canvas) {
    stackPane.getChildren().add(canvas);
  }
  public void controlFPS() {
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




