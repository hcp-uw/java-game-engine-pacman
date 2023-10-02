package com.jgegroup.mapwriter;

import com.jgegroup.mapwriter.config.Config;
import com.jgegroup.mapwriter.config.ConfigBuilder;
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Launch extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        // Creating tab pane, tabs, then adding tabs to tab pane
        TabPane tabPane = new TabPane();
        tabPane.setSide(Side.TOP);
        Tab tab1 = new Tab("Game Settings");
        Tab tab2 = new Tab("Map Writer");
        tab1.setClosable(false);
        tab2.setClosable(false);
        tabPane.getTabs().addAll(tab1, tab2);

        // CONTENT ADDED TO TABS HAVE TO BE NODES: SINGLE CONTROL, GROUP OF CONTROLS WRAPPED IN PANE OBJECT, ETC.
        tab1.setContent(GameSettingsContent());
        tab2.setContent(MapWriterContent());

        // Set up
        Group root = new Group(); // No longer used - Gabriel Sison
        Scene scene = new Scene(tabPane, 400, 400);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("JGE Settings");
        stage.centerOnScreen();

        stage.show();
        app(stage);
    }

    public Pane MapWriterContent() {
        Pane root = new Pane();
        Label label = new Label ("Map Writer");
        label.relocate(150, 10);
        TextField textField = new TextField();
        textField.relocate(100, 30);
        Button button = new Button ("Button");
        button.relocate(275,30);
        root.getChildren().addAll(label, textField, button);
        return root;
    }

    public Pane GameSettingsContent() {
        Pane root = new Pane();
        Label label = new Label ("Game Settings");
        label.relocate(150, 10);
        TextField textField = new TextField();
        textField.relocate(100, 30);
        Button button = new Button ("Button");
        button.relocate(275,30);
        root.getChildren().addAll(label, textField, button);
        return root;
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
