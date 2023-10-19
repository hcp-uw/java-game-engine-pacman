package com.jgegroup.GameConfig;

import com.jgegroup.GameConfig.config.Settings;
import com.jgegroup.pacman.GameScene;
import javafx.application.Application;
import javafx.collections.FXCollections;
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
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
        Label label = new Label("Game Settings");
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
        Label pacManLives = new Label("Pacman Lives");
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
        Label ghostSpeed = new Label("Ghost Speed");
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
        Label pacManSpeed = new Label("Pacman Speed");
        pacManSpeed.relocate(25, 200);


        Button button = new Button("Game Launch");
        button.relocate(125, 50);
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
        ComboBox<String> floorBox = new ComboBox<>();
        floorBox.setPrefWidth(258);
        floorBox.relocate(525, 50);

        File floorDir = new File("res/tiles/floor tiles");
        File[] floors = floorDir.listFiles();

        List<Image> floorImagesList = new ArrayList<>();
        List<Image> wallImagesList = new ArrayList<>();
        List<String> floorPaths = new ArrayList<>();
        List<String> wallPaths = new ArrayList<>();

        floorBox.setCellFactory(param -> new TileListCell());
        floorBox.setButtonCell(new TileListCell());
        if (floors != null) {
            for (int i = 0; i < floors.length; i++) {
                String name = floors[i].getName();
                int pos = name.lastIndexOf('.');
                Image image = new Image("tiles/floor tiles/" + floors[i].getName());
                floorPaths.add("tiles/floor tiles/" + floors[i].getName());
                floorImagesList.add(image);
            }
        }
        floorBox.setItems(FXCollections.observableArrayList(floorPaths));

        floorBox.setOnAction(event -> {
            int index = floorBox.getSelectionModel().getSelectedIndex();
            if (index > -1)
                settings.setFloorImage(floorImagesList.get(index));
        });

        //wall
        ComboBox<String> wallBox = new ComboBox<>();
        wallBox.setPrefWidth(258);
        wallBox.relocate(525, 100);

        File wallDir = new File("res/tiles/wall tiles");
        File[] walls = wallDir.listFiles();
        wallBox.setCellFactory(param -> new TileListCell());
        wallBox.setButtonCell(new TileListCell());
        if (walls != null) {
            for (int i = 0; i < walls.length; i++) {
                String name = walls[i].getName();
                int pos = name.lastIndexOf('.');
                Image image = new Image("tiles/wall tiles/" + walls[i].getName());
                wallPaths.add("tiles/wall tiles/" + walls[i].getName());
                wallImagesList.add(image);
            }
        }
        wallBox.setItems(FXCollections.observableArrayList(wallPaths));



        wallBox.setOnAction(event -> {
            int index = wallBox.getSelectionModel().getSelectedIndex();
            if (index > -1)
                settings.setWallImage(wallImagesList.get(index));
        });

        root.getChildren().addAll(floorBox, wallBox);

        // Save map button
        Button saveMapButton = new Button("Save Map");
        saveMapButton.relocate(600, 10);
        saveMapButton.setOnAction(event -> {
            try {
                current_map.saveMap();
            } catch (NullPointerException e) {
                Text errText = new Text();
                errText.setText("Not working with a map!");
                errText.relocate(620, 20);
                root.getChildren().add(errText);
            }
        });


        // Show Map button
        Button showMapButton = new Button("Show map");
        showMapButton.relocate(525, 10);
        Text map_text = new Text();
        AtomicInteger scrollPane_index = new AtomicInteger(-1);
        AtomicInteger tilePickerButton_index = new AtomicInteger(-1);
        showMapButton.setOnAction(event -> {
            if (scrollPane_index.get() != -1)
                root.getChildren().remove(scrollPane_index.get());
            if (tilePickerButton_index.get() != -1)
                root.getChildren().remove(tilePickerButton_index);
            try {
                String content = current_map.showMapContent();
                root.getChildren().remove(map_text);
                map_text.setText(content);
                map_text.relocate(5, 200);
                root.getChildren().add(map_text);
            } catch (NullPointerException e) {
                Text errText = new Text();
                errText.setText("Not working with a map!");
                errText.relocate(300, 30);
                root.getChildren().add(errText);
            }
            ScrollPane scrollPane = new ScrollPane();
            TilePickerButton tilePickerButton = new TilePickerButton(0,
                    settings.selectedFloorImage() ?
                            settings.getFloorImage() : new Image("tiles/floor tiles/floor.png"),
                    settings.selectedWallImage() ?
                            settings.getWallImage() : new Image("tiles/wall tiles/wall.png"),
                    settings.selectedFloorImage() ?
                            settings.getFloorImage() : new Image("tiles/floor tiles/floor.png"),
                    settings.selectedFloorImage() ?
                            settings.getFloorImage() : new Image("tiles/floor tiles/floor.png"));
            tilePickerButton.relocate(215, 100);
            TilePane tilePane = new TilePane(current_map.getArrayMap(),
                    settings.selectedFloorImage() ?
                            settings.getFloorImage() : new Image("tiles/floor tiles/floor.png"),
                    settings.selectedWallImage() ?
                            settings.getWallImage() : new Image("tiles/wall tiles/wall.png"),
                    tilePickerButton);
            scrollPane.setContent(tilePane);
            tilePane.setPrefHeight(800);
            scrollPane.relocate(5, 150);
            scrollPane.setMaxHeight(400);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            root.getChildren().addAll(scrollPane, tilePickerButton);
            scrollPane_index.set(root.getChildren().indexOf(scrollPane));
            tilePickerButton_index.set(root.getChildren().indexOf(tilePickerButton));

        });

        // Create new map button
        Button createNewMapButton = new Button("Create New Map");
        createNewMapButton.relocate(10, 10);
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
        comboBox.relocate(670, 10);
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
            horizontaLengthDialog.setTitle("Creating New Map");
            horizontaLengthDialog.setHeaderText(null);
            horizontaLengthDialog.setContentText("Enter number of horizontal tiles");
            horizontaLengthDialog.showAndWait().ifPresent(horizontal_length -> {
                try {

                    int hor_len = Integer.parseInt(horizontal_length);

                    if (hor_len > 32) {
                        throw new NumberFormatException("Number was too big!!!");
                    }
                    this.horizontal_length = hor_len;
                    TextInputDialog verticalLengthDialog = new TextInputDialog();
                    verticalLengthDialog.setTitle("Create New Map");
                    verticalLengthDialog.setHeaderText(null);
                    verticalLengthDialog.setContentText("Enter number of vertical tiles");
                    verticalLengthDialog.showAndWait().ifPresent(vertical_length -> {
                        try {
                            int ver_len = Integer.parseInt(vertical_length);
                            if (ver_len > 32) {
                                throw new NumberFormatException("Number was too big!!!");
                            }
                            this.vertical_length = ver_len;
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
        current_map = new Map(newMapName, vertical_length, horizontal_length);
    }

    class TileListCell extends ListCell<String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null || empty) {
                setGraphic(null);
            } else {
                Image image = new Image(item);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                int pos1 = item.lastIndexOf('/');
                int pos2 = item.lastIndexOf('.');
                setGraphic(new Label(item.substring(pos1 + 1, pos2), imageView));
            }

            setText("");
        }
    }

    class TilePane extends Pane {
        int[][] mapArray;
        Image floor, wall;
        TilePickerButton picker;

        public TilePane(int[][] mapArray, Image floor, Image wall, TilePickerButton picker) {
            super();
            System.out.println("Creating TilePane");
            this.mapArray = mapArray;
            this.floor = floor;
            this.wall = wall;
            this.picker = picker;
            initialize(this);
        }

        public void initialize(TilePane tilePane) {
            int height = mapArray.length;
            int width = mapArray[0].length;
            int height_spacer = 5; // account for space from top bar
            int width_spacer = 5;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {

                    Button newButton = new Button();
                    if (mapArray[i][j] != 1) {
                        ImageView floorView = new ImageView(floor);
                        floorView.setFitHeight(12);
                        floorView.setFitWidth(12);
                        newButton.setGraphic(floorView);
                    } else {
                        ImageView wallView = new ImageView(wall);
                        wallView.setFitWidth(12);
                        wallView.setFitHeight(12);
                        newButton.setGraphic(wallView);
                    }
                    int finalI = i;
                    int finalJ = j;
                    newButton.setOnAction(event -> {
                        if (picker.getValue() != 1) {
                            ImageView wallView = new ImageView(wall);
                            wallView.setFitWidth(12);
                            wallView.setFitHeight(12);
                            mapArray[finalI][finalJ] = 0;
                            newButton.setGraphic(wallView);
                        } else {
                            ImageView floorView = new ImageView(floor);
                            floorView.setFitHeight(12);
                            floorView.setFitWidth(12);
                            mapArray[finalI][finalJ] = 1;
                            newButton.setGraphic(floorView);
                        }
                    });
                    newButton.relocate(width_spacer + (i * 32), height_spacer + (j * 32));
                    newButton.setMaxHeight(10);
                    newButton.setMaxWidth(10);
                    newButton.setPrefSize(10, 10);
                    tilePane.getChildren().add(newButton);
                }
            }
        }
    }

    class TilePickerButton extends Button {
        int curr;
        Image floor, wall;
        WritableImage dot, bigDot;

        public TilePickerButton(int curr, Image floor, Image wall, WritableImage dot, WritableImage bigDot) {
            super();
            this.curr = curr;
            this.floor = floor;
            this.wall = wall;
            this.dot = dot;
            this.bigDot = bigDot;
            initDot(dot);
            initBigDot(bigDot);
            setCurrGraphic();
            this.setOnAction(event -> {
                if (this.curr > 3)
                    this.curr = 0;
                else
                    this.curr++;
                setCurrGraphic();
            });
        }

        public int getValue() {
            return this.curr;
        }

        public void setCurrGraphic() {
            if (this.curr == 0) {
                ImageView floorView = new ImageView(floor);
                floorView.setFitHeight(15);
                floorView.setFitWidth(15);
                this.setGraphic(floorView);
            } else if (this.curr == 1) {
                ImageView wallView = new ImageView(wall);
                wallView.setFitWidth(15);
                wallView.setFitHeight(15);
                this.setGraphic(wallView);
            } else if (this.curr == 2) {
                ImageView dotView = new ImageView(wall);
                dotView.setFitWidth(15);
                dotView.setFitHeight(15);
                this.setGraphic(dotView);
            } else if (this.curr == 3) {
                ImageView bigDotView = new ImageView(bigDot);
                bigDotView.setFitWidth(15);
                bigDotView.setFitHeight(15);
                this.setGraphic(bigDotView);
            }
        }
    }
}
