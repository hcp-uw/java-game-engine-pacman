package com.jgegroup.GameConfig;

import com.jgegroup.GameConfig.config.Settings;
//import com.jgegroup.pacman.GameLaunch;
import com.jgegroup.pacman.GameScene;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GameConfig extends Application {

//    public GameLaunch gameLaunch;
    public String newMapName; // For it to work with lambda ex
    public int horizontal_length; // For it to work with lambda ex
    public int vertical_length; // For it to work with lambda ex
    public static Map current_map;
    public static void main(String[] args) {
        launch(args);
    }
    private Stage stage;
    private Settings settings;
    @Override
    public void start(Stage stage) throws Exception {
        // Creating tab pane, tabs, then adding tabs to tab pane
        this.stage = stage;
        settings = new Settings();


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
        Scene scene = new Scene(tabPane, 800, 800);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("JGE Settings");
        stage.centerOnScreen();

        stage.show();
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
        ghostLivesSlider.setBlockIncrement(2);
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
        pacManSpeedSlider.setBlockIncrement(2);
        pacManSpeedSlider.setMajorTickUnit(1);
        pacManSpeedSlider.setMinorTickCount(0);
        pacManSpeedSlider.setShowTickMarks(true);
        pacManSpeedSlider.setShowTickLabels(true);
        Label pacManSpeed = new Label ("Pacman Speed");
        pacManSpeed.relocate(25, 200);


        Button button = new Button ("Game Launch");
        button.relocate(125,50);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.out.println("button pressed");
                int pacmanLives = (int) (pacManLivesSlider.getValue() + 0.5);
                int ghostSpeed = (int) (ghostLivesSlider.getValue() + 0.5);
                int pacmanSpeed = (int) (pacManSpeedSlider.getValue() + 0.5);
                ghostSpeed -= ghostSpeed % 2;
                pacmanSpeed -= pacmanSpeed % 2;
                ghostSpeed = Math.max(1, ghostSpeed);
                pacmanSpeed = Math.max(1, pacmanSpeed);
                System.out.println("Our lives for pacman is " + pacmanLives);
                System.out.println("Our speed for ghost  is " + ghostSpeed);
                System.out.println("Our speed for pacman is " + pacmanSpeed);
                // How do I launch the game from here?

                settings.setPacmanLives(pacmanLives);
                settings.setGhostSpeed(ghostSpeed);
                settings.setPacmanSpeed(pacmanSpeed);

                GameScene scene = new GameScene(6, settings);
                stage.setResizable(true);
                stage.show();
                scene.startThread();
                scene.readContext(settings);

                stage.setScene(scene.gameScene);
//
//                // Exports to settings.txt in setting directory
//                try {
//                    BufferedWriter writer = new BufferedWriter(new FileWriter("res/settings/settings.txt"));
//                    writer.write("pacman lives: " + pacmanLives);
//                    writer.write("\nghost speed : " + ghostSpeed);
//                    writer.write("\npacman speed: " + pacmanSpeed);
//                    writer.close();
//                } catch (IOException c) {
//                    throw new RuntimeException(c);
//                }


            }
        };



        // when button is pressed
        button.setOnAction(event);
        root.getChildren().addAll(label, button, pacManLivesSlider, ghostLivesSlider, pacManSpeedSlider,
                ghostSpeed, pacManSpeed, pacManLives);
        return root;
    }

    public Pane MapWriterContent() {
      Pane root = new Pane();
      Canvas mapWriterCanvas = new Canvas(400, 400);
      GraphicsContext mapPainter = mapWriterCanvas.getGraphicsContext2D();
      root.getChildren().add(mapWriterCanvas);

        // Dropdown for images
        // floor
        ComboBox<Label> floorBox = new ComboBox<>();
        floorBox.setPrefWidth(250);
        floorBox.relocate(450, 50);

        File floorDir = new File("res/tiles/floor tiles");
        File[] floors = floorDir.listFiles();

        List<Image> floorImagesList = new ArrayList<>();
        List<Image> wallImagesList = new ArrayList<>();

        if (floors != null) {
            for (int i = 0; i < floors.length; i++) {
                String name = floors[i].getName();
                int pos = name.lastIndexOf('.');
                Label floorlabel = new Label(pos > -1 ? name.substring(0, pos) : name);
                Image image = new Image("tiles/floor tiles/" + floors[i].getName());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                floorlabel.setGraphic(imageView);
                floorImagesList.add(image);
                floorBox.getItems().add(floorlabel);
            }
        }

        floorBox.setOnAction(event -> {
            Label label = floorBox.getValue();
            int index = floorBox.getItems().indexOf(label);
            if (index > -1) {
                settings.setFloorImage(floorImagesList.get(index));
            }
            floorBox.getSelectionModel().clearAndSelect(index);
        });

        //wall
        ComboBox<Label> wallBox = new ComboBox<>();
        wallBox.setPrefWidth(250);
        wallBox.relocate(450, 100);

        File wallDir = new File("res/tiles/wall tiles");
        File[] walls = wallDir.listFiles();

        if (walls != null) {
            for (int i = 0; i < walls.length; i++) {
                String name = walls[i].getName();
                int pos = name.lastIndexOf('.');
                Label walllabel = new Label(pos > -1 ? name.substring(0, pos) : name);
                Image image = new Image("tiles/wall tiles/" + walls[i].getName());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                walllabel.setGraphic(imageView);
                wallImagesList.add(image);
                wallBox.getItems().add(walllabel);
            }
        }

        wallBox.setOnAction(event -> {
            Label label = wallBox.getValue();
            int index = wallBox.getItems().indexOf(label);
            if (index > -1) {
                settings.setFloorImage(wallImagesList.get(index));
            }
            wallBox.getSelectionModel().clearAndSelect(index);
        });

        root.getChildren().addAll(floorBox, wallBox);

      // Save map button
      Button saveMapButton = new Button("Save Map");
      saveMapButton.relocate(640, 0);
      saveMapButton.setOnAction(event -> {
        try {
          current_map.saveMap();
        } catch (NullPointerException e) {
          Text errText = new Text();
          errText.setText("Not working with a map!");
          errText.relocate(720, 20);
          root.getChildren().add(errText);
        }
      });


      // Show Map button
      Button showMapButton = new Button("Show map");
      showMapButton.relocate(565, 0);
      Text map_text = new Text();
      showMapButton.setOnAction(event -> {
        try {
          String content = current_map.showMapContent();
          root.getChildren().remove(map_text);
          map_text.setText(content);
          map_text.relocate(5, 50);
          root.getChildren().add(map_text);
        } catch (NullPointerException e) {
          Text errText = new Text();
          errText.setText("Not working with a map!");
          errText.relocate(300, 30);
          root.getChildren().add(errText);
        }
      });

      // Create new map button
      Button createNewMapButton = new Button("Create New Map");
      createNewMapButton.relocate(0, 0);
        ComboBox<String> addMapDropBox = addMapDropBox();
        createNewMapButton.setOnAction(event -> {
        getMapInfo();
        updateMapDropBox(addMapDropBox);
      });


      root.getChildren().addAll(saveMapButton, showMapButton, createNewMapButton, addMapDropBox);


      return root;
    }

  public ComboBox<String> addMapDropBox() {
    ComboBox<String> comboBox = new ComboBox<>();
    comboBox.relocate(710, 0);
    File directory = new File("res/maps");
    File[] maps = directory.listFiles();
    if (maps != null) {
      for (File map : maps) {
          comboBox.getItems().add(map.getName());
      }
    }
    comboBox.setOnAction(event -> {
      String selectedMap = comboBox.getValue();
      if (selectedMap != null) {
        System.out.println("selecting map: " + selectedMap);
        try {
          // Change the currently working map.
          current_map = new Map(selectedMap);

        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        }
      }
    });
    return comboBox;
  }
  public void updateMapDropBox(ComboBox<String> dropDown) {
        dropDown.getItems().clear();
      File directory = new File("res/maps");
      File[] maps = directory.listFiles();
      if (maps != null) {
          for (File map : maps) {
              dropDown.getItems().add(map.getName());
          }
      }
  }

    public void getMapInfo() {
      String map_name;
      TextInputDialog nameDialog = new TextInputDialog();
      nameDialog.setTitle("Create New Map");
      nameDialog.setHeaderText(null);
      nameDialog.setContentText("Enter Map Name:");

      nameDialog.showAndWait().ifPresent(name -> {

        this.newMapName = name;

        TextInputDialog horizontaLengthDialog = new TextInputDialog();
        horizontaLengthDialog.setTitle("Create New Map");
        horizontaLengthDialog.setHeaderText(null);
        horizontaLengthDialog.setContentText("Enter number of tile horizontal");
        horizontaLengthDialog.showAndWait().ifPresent(horizontal_length -> {
          try {

          this.horizontal_length = Integer.parseInt(horizontal_length);

          TextInputDialog verticalLengthDialog = new TextInputDialog();
          verticalLengthDialog.setTitle("Create New Map");
          verticalLengthDialog.setHeaderText(null);
          verticalLengthDialog.setContentText("Enter number of tile vertical");
          verticalLengthDialog.showAndWait().ifPresent(vertical_length -> {
            try {
              this.vertical_length = Integer.parseInt(vertical_length);

            } catch (NumberFormatException e) {
              System.out.println("Invalid number");
            }
          });
          } catch (NumberFormatException e) {
            System.out.println("Invalid number");
          }
        });
      });
      // change the currently working map.
      current_map = new Map(newMapName,vertical_length, horizontal_length);
    }
}
