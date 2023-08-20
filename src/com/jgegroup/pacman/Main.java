package com.jgegroup.pacman;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.image.Image;

public class Main extends Application {

  public static void main(String[] args)  {
    launch(args);
  }
  @Override
  public void start(Stage stage) throws Exception {
    stage = new Stage();
    stage.setTitle("2D Pacman Game");
    Image icon = new Image("icon/icon.png");
    stage.getIcons().add(icon);


    Scene scene = new Scene();
    stage.setScene(scene.gameScene);
    stage.setResizable(true);
    stage.show();
    scene.startThread();
  }
}
