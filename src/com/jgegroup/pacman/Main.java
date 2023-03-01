package com.jgegroup.pacman;
import com.jgegroup.pacman.GameScene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.image.Image;




public class Main extends Application {
    //Code starts here
    // hello
    public static void main(String[] args)  {
      launch(args);

    }
  @Override
  public void start(Stage stage) throws Exception {
      stage = new Stage();
      GameScene gameScene = new GameScene();

      // icon and name
      stage.setTitle("2D Pacman Game");
      Image icon = new Image("icon/icon.png");
      stage.getIcons().add(icon);
      // add scene
      stage.setScene(gameScene.gameScene);
      stage.setResizable(false);
      stage.show();
  }
}
