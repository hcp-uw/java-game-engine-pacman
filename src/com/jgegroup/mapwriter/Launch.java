package com.jgegroup.mapwriter;

import com.jgegroup.mapwriter.config.Config;
import com.jgegroup.mapwriter.config.ConfigBuilder;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launch extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("JGE Map Writer");
        stage.centerOnScreen();
        stage.show();

        app(stage);
    }


    public void app(Stage stage) {
        Scene scene = stage.getScene();

        Config config = initialize(scene);
    }

    private Config initialize(Scene scene) {
        ConfigBuilder cb = new ConfigBuilder();


        return cb.build();
    }
}
