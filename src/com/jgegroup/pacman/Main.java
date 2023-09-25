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


    MainScene mainScene = new MainScene(4);
    stage.setScene(mainScene.mainScene);
    stage.setResizable(true);
    stage.show();
    mainScene.startThread();


    // Change git message
  }
}
