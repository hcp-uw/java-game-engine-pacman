//package com.jgegroup.pacman;
//
//import com.jgegroup.pacman.objects.*;
//import com.jgegroup.pacman.objects.Enums.Direction;
//import com.jgegroup.pacman.objects.characters.*;
//import com.jgegroup.pacman.objects.immovable.Tile;
//import com.jgegroup.pacman.objects.consumables.Consumable;
//import javafx.application.Application;
//
//import javafx.scene.input.KeyCode;
//import javafx.stage.Stage;
//
//import java.util.HashMap;
//
//public class GameCyclePrototype extends Application {
//    /*
//        Psuedocode general loop:
//        while game is running
//        process inputs
//        update game world
//        generate outputs
//        loop
//
//        Psuedocode pacman loop:
//        while player.lives > 0
//        // Process Inputs
//        JoystickData j = grab raw data from joystick
//
//        // Update Game World
//        update player.position based on j
//        foreach Ghost g in world
//            if player collides with g
//                kill either player or g
//            else
//                update AI for g based on player.position
//            end
//        loop
//
//        // Pac-Man eats any pellets
//        ...
//
//        // Generate Outputs
//        draw graphics
//        update audio
//        loop
//     */
//    long lastTime;
//    long now;
//    Position ghostSpawn;
//    Pacman pacman;
//    Ghost[] ghosts;
//    HashMap<Position, Tile> gameMap;
//    HashMap<Position, Consumable> consumables;
//    Direction dirCache;
//    boolean pause = false;
//
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        // setup
//        setup(primaryStage);
//        // loop
//        while (pacman.getLives() > 0) {
//            lastTime = now = System.currentTimeMillis();
//            getInput();
//
//            Position pacPos = MapUtils.worldToScreen(pacman.getPosition());
//            Position[] ghostPos = new Position[4];
//            int index = 0;
//            for (Ghost g : ghosts) {
//                ghostPos[index] = MapUtils.worldToScreen(ghosts[index].getPosition());
//                index++;
//                g.thinkPrep(gameMap, pacman.getPosition());
//            }
//            move(pacman, pacPos);
//            for (int i = 0; i < 4; i++) {
//                move(ghosts[i], ghostPos[i]);
//                if (pacPos.equals(ghostPos[i])) {
//                    int status = pacman.collisionHandle(ghosts[i]);
//                    if (status == 100)
//                        pacman.death();
//                    else {
//                        Class<? extends Ghost> clazz = ghosts[i].getClass();
//                        ghosts[i] = clazz.getConstructor().newInstance();
//                    }
//                }
//            }
//            if (consumables.containsKey(pacPos)) {
//                pacman.eat(consumables.get(pacPos));
//                consumables.remove(pacPos);
//            }
//
//
//
//            wait(now, now + 33);
//        }
//    }
//
//    public void setup(Stage stage) {
//        Map map = Map.getMapInstance();
//        gameMap = map.getTiles();
//        consumables = map.getObjects();
//        Position pacSpawn = map.getPacmanSpawn();
//        pacman = new Pacman(pacSpawn.getX(), pacSpawn.getY(), 10);
//
//        ghostSpawn = map.getGhostSpawn();
//        ghosts[0] = new Pink(ghostSpawn.getX(), ghostSpawn.getY(), 10);
//        ghosts[1] = new Blue(ghostSpawn.getX(), ghostSpawn.getY(), 10);
//        ghosts[2] = new Yellow(ghostSpawn.getX(), ghostSpawn.getY(), 10);
//        ghosts[3] = new Red(ghostSpawn.getX(), ghostSpawn.getY(), 10);
//
//        stage.getScene().setOnKeyPressed((keyEvent) -> {
//            if (keyEvent.getCode() == KeyCode.A) {
//                dirCache = Direction.LEFT;
//            } else if (keyEvent.getCode() == KeyCode.W) {
//                dirCache = Direction.UP;
//            } else if (keyEvent.getCode() == KeyCode.S) {
//                dirCache = Direction.DOWN;
//            } else if (keyEvent.getCode() == KeyCode.D) {
//                dirCache = Direction.RIGHT;
//            } else if (keyEvent.getCode() == KeyCode.SPACE) {
//                pause = !pause;
//            }
//        });
//    }
//
//    public void move(MovingObject object, Position worldPos) {
//        Direction dir = object.getDirection();
//        switch (dir) {
//            case DOWN -> {
//                worldPos.translate(0, -1);
//            }
//            case UP -> {
//                worldPos.translate(0, 1);
//            }
//            case LEFT -> {
//                worldPos.translate(-1, 0);
//            }
//            case RIGHT -> {
//                worldPos.translate(1, 0);
//            }
//            case STOP -> {}
//        }
//    }
//    public void getInput() {
//        long endTime = now + 1;
//        dirCache = Direction.STOP;
//        while (dirCache == Direction.STOP && System.currentTimeMillis() < endTime) {
//            ;
//        }
//        pacman.nextMove = MapUtils.validateMove(dirCache, MapUtils.getSurrounding(gameMap, pacman.getPosition()));
//        wait(now, endTime);
//    }
//
//    public void wait(long start, long stop) {
//        while (start < stop) {
//            start = System.currentTimeMillis();
//        }
//    }
//
//
//}
