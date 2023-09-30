package com.jgegroup.pacman;

import com.jgegroup.pacman.objects.Map;

import com.jgegroup.pacman.objects.UI;
import com.jgegroup.pacman.objects.characters.Ghost;
import com.jgegroup.pacman.objects.characters.Pac;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;


public class GameScene implements Runnable{
  public static boolean gameFinished;
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
  public CollisionChecker collisionChecker;
  public javafx.scene.Scene gameScene;

  private StackPane stackPane;

  private Canvas gameCanvas;
  private GraphicsContext gamePainter;

  private Canvas Layer_Lower;
  private Canvas Layer_Upper;
  private Canvas Layer_UI;
  private Canvas[] Layer_Ghosts;

  private static GraphicsContext Layer_Lower_PaintComponent;
  private static GraphicsContext Layer_Upper_PaintComponent;
  private static GraphicsContext Layer_UI_PaintComponent;
  private static GraphicsContext[] Layer_Ghost_PaintComponents;
  public  Map map;
  private KeyHandler keyHandler;
  private Pac pac;
  private Ghost[] ghosts;
  private Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.PINK};

  public UI ui;



  private static int x = 300;
  private static int y = 300;
  private static int c = 0;


  public GameScene(int ghostNumber) {
    stackPane = new StackPane();
    gameScene = new javafx.scene.Scene(stackPane, RESOLUTION_HORIZONTAL, RESOLUTION_VERTICAL, Color.BLACK);
    gameCanvas = new Canvas(RESOLUTION_HORIZONTAL, RESOLUTION_VERTICAL);
    gamePainter = gameCanvas.getGraphicsContext2D();

    ui = new UI(this);
    map = Map.getMapInstance();
    map.createMap();
    collisionChecker = new CollisionChecker(map);

    Layer_Upper = new Canvas(RESOLUTION_HORIZONTAL, RESOLUTION_VERTICAL);
    Layer_UI = new Canvas(RESOLUTION_HORIZONTAL, RESOLUTION_VERTICAL);

    Layer_Upper_PaintComponent = Layer_Upper.getGraphicsContext2D();
    Layer_UI_PaintComponent = Layer_UI.getGraphicsContext2D();


    addCanvasLayer(gameCanvas);
    addCanvasLayer(Layer_Upper);
    addCanvasLayer(Layer_UI);

    Layer_Ghosts = new Canvas[ghostNumber];
    Layer_Ghost_PaintComponents = new GraphicsContext[ghostNumber];
    for (int i=0; i<ghostNumber; i++) {
      Layer_Ghosts[i] = new Canvas(RESOLUTION_HORIZONTAL, RESOLUTION_VERTICAL);
      Layer_Ghost_PaintComponents[i] = Layer_Ghosts[i].getGraphicsContext2D();
      addCanvasLayer(Layer_Ghosts[i]);
    }
    keyHandler = new KeyHandler();
    gameScene.setOnKeyPressed(keyHandler);
    pac = new Pac(this, keyHandler);
    ghosts = new Ghost[ghostNumber];

    for (int i = 0; i < ghostNumber; i++) {
      ghosts[i] = new Ghost(10, this, colors[i], pac);
      ghosts[i].setSpawnPosition(i);
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
    while (pac.getLives() >= 0) {
      update();
      redraw();
      controlFPS();
    }
    ui.displayGameFinish(Layer_UI_PaintComponent);
  }

  public void update() {
    pac.update();
    for (Ghost ghost : ghosts) {
      ghost.update();
    }

  }
  public void redraw() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        map.drawMap(getGamePainter());
        map.drawDot(getGamePainter());
        pac.redraw(getGamePainter());
        for (int i = 0; i < ghosts.length; i++) {
          ghosts[i].redraw(Layer_Ghost_PaintComponents[i]);
        }
        ui.redraw(Layer_UI_PaintComponent);
      }
    });
  }


  // Functions

  // Add canvas to the main StackPane aka the game panel.
  public void addCanvasLayer(Canvas canvas) {
    this.stackPane.getChildren().add(canvas);
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
  public int getPacLives() {
    return pac.getLives();
  }
  public int getPoints() {
    return pac.getPoint();
  }

  public Canvas getCanvas() {
    return this.gameCanvas;
  }
  public GraphicsContext getGamePainter() {
    return this.gamePainter;
  }
}




