package com.jgegroup.mapwriter;

import com.jgegroup.mapwriter.config.Config;
import com.jgegroup.mapwriter.config.ConfigBuilder;
import com.jgegroup.pacman.GameLaunch;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Slider;

import java.io.*;

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


    public Pane GameSettingsContent() {
        Pane root = new Pane();
        Label label = new Label ("Game Settings");
        label.relocate(150, 10);

        // Lives for PacMan
        Slider pacManLivesSlider = new Slider(1, 10, 3);
        pacManLivesSlider.setPrefWidth(250);
        pacManLivesSlider.relocate(125, 100);
        pacManLivesSlider.setBlockIncrement(1);
        pacManLivesSlider.setMajorTickUnit(1);
        pacManLivesSlider.setMinorTickCount(0);
        pacManLivesSlider.setShowTickMarks(true);
        pacManLivesSlider.setShowTickLabels(true);
        Label pacManLives = new Label ("Pacman Lives");
        pacManLives.relocate(25, 100);

        // Speed for Ghost
        Slider ghostLivesSlider = new Slider(1, 5, 3);
        ghostLivesSlider.setPrefWidth(250);
        ghostLivesSlider.relocate(125, 150);
        ghostLivesSlider.setBlockIncrement(1);
        ghostLivesSlider.setMajorTickUnit(1);
        ghostLivesSlider.setMinorTickCount(0);
        ghostLivesSlider.setShowTickMarks(true);
        ghostLivesSlider.setShowTickLabels(true);
        Label ghostSpeed = new Label ("Ghost Speed");
        ghostSpeed.relocate(25, 150);

        // PacMan Speed default
        Slider pacManSpeedSlider = new Slider(1, 5, 3);
        pacManSpeedSlider.setPrefWidth(250);
        pacManSpeedSlider.relocate(125, 200);
        pacManSpeedSlider.setBlockIncrement(1);
        pacManSpeedSlider.setMajorTickUnit(1);
        pacManSpeedSlider.setMinorTickCount(0);
        pacManSpeedSlider.setShowTickMarks(true);
        pacManSpeedSlider.setShowTickLabels(true);
        Label pacManSpeed = new Label ("Pacman Speed");
        pacManSpeed.relocate(25, 200);

        Button button = new Button ("Game Launch");
        button.relocate(300,300);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.out.println("button pressed");
                int pacmanLives = (int) (pacManLivesSlider.getValue() + 0.5);
                int ghostSpeed = (int) (ghostLivesSlider.getValue() + 0.5) * 2;
                int pacmanSpeed = (int) (pacManSpeedSlider.getValue() + 0.5) * 2;
                System.out.println("Our lives for pacman is " + pacmanLives);
                System.out.println("Our speed for ghost  is " + ghostSpeed);
                System.out.println("Our speed for pacman is " + pacmanSpeed);
                // How do I launch the game from here?

                // We're experimenting with this :)
                try {
                    String folderName = "gameSettings";
                    String fileName = "GUIsettings.txt";
                    File folder = new File(folderName);
                    FileWriter file = new FileWriter(fileName);
                    file.write("this worked");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };

        // Create print stream


        // when button is pressed
        button.setOnAction(event);
        root.getChildren().addAll(label, button, pacManLivesSlider, ghostLivesSlider, pacManSpeedSlider,
                ghostSpeed, pacManSpeed, pacManLives);
        return root;
    }

    public GameLaunch gameLaunch;
    public Pane MapWriterContent() {
        Pane root = new Pane();
        Label label = new Label ("Map Writer");
        label.relocate(150, 10);

        root.getChildren().addAll(label);

        Map map1 = new Map("map2", 10, 10);
        String content = readMap(map1.getPath());
        Text map_content = new Text();
        map_content.setText(content);
        root.getChildren().add(map_content);
        return root;
    }
    public String readMap (String path) {
      try {
        InputStream inputStream = new FileInputStream(path);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder map_content = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
          map_content.append(line).append("\n");
        }
        bufferedReader.close();
        return map_content.toString();
      } catch (IOException e) {
        e.printStackTrace();
        return "";
      }
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
