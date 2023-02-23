package com.jgegroup.pacman;

import com.jgegroup.pacman.objects.*;
import com.jgegroup.pacman.objects.immovable.consumables.BigDot;
import com.jgegroup.pacman.objects.immovable.consumables.Consumable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import com.jgegroup.pacman.objects.MapUtils;
import com.jgegroup.pacman.objects.Enums.*;

public class GameCycle extends Application {
    HashSet<Pacman> pacmen;
    HashSet<Ghost>  ghosts;
    //Each pixel refer to an
    HashMap<Position, Tile> tileBoard;
    HashMap<Position, Consumable> objects;

    // holds onto position of objects(pacman/ghosts) through each
    // loop for front end to use in javaFX
    HashMap<MovingObject,Position> objectPosition;

    // holds position of dot that was eaten by
    // pacman, to then be used by frontend to remove the
    // pixel
    Consumable dotPosition = null;
    private boolean paused = false;

    // this is used to cache the direction
    // so it can be later passed to the pacman
    public static Direction dir_cache;
    public HashMap<GameObject, HashSet<Enums.Update>> updates;

    @Override
    public void start(Stage stage) throws Exception {
        init(stage);
        boolean runGame = true;
        while (runGame) {
            runGame = loop(stage.getScene());
            TimeUnit.MILLISECONDS.sleep(1);
        }
    }

    // Authors: Anthony / Jesse / Noah / Nikola
    // Stores direction on press of arrow keys and pauses game when space is pressed
    // Throws no exceptions
    // Returns true when game paused
    // Takes scene as parameter
    private boolean loop(Scene scene) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:    dir_cache = Direction.UP; break;
                case DOWN:  dir_cache = Direction.DOWN; break;
                case LEFT:  dir_cache = Direction.LEFT; break;
                case RIGHT: dir_cache = Direction.RIGHT; break;
                case SPACE: paused =! paused; break;
            }
        });

        //If the game is paused then skip all event handle
        if(paused) {
            return true;
        }
        // direction = keylister.get
        // perform check to see if move is valid
            // if move valid, perform move and check for collisions
            // else do not move
        Position pacPos = null;
        for(Pacman _pacman : pacmen) {
            //if(_pacman.isPlayer){
                _pacman.nextMove = dir_cache;
            //}


            pacPos = _pacman.getPosition();
            HashMap<Direction, Tile> surr = MapUtils.getSurrounding(tileBoard, pacPos);
            Direction move = MapUtils.moveValid(_pacman, surr);
            if (move == Direction.LEFT) {
                pacPos.translate(-1, 0);
            } else if (move == Direction.UP) {
                pacPos.translate(0, 1);
            } else if (move == Direction.RIGHT) {
                pacPos.translate(1,0);
            } else if (move == Direction.DOWN) {
                pacPos.translate(0,-1);
            }
            if (move != Direction.STOP) {
                if (!updates.containsKey(_pacman)) {
                    HashSet<Update> pUpdates = new HashSet<>();
                    pUpdates.add(Update.MOVED);
                    updates.put(_pacman, pUpdates);
                } else {
                    updates.get(_pacman).add(Update.MOVED);
                }
            }
            for(Ghost _ghost : ghosts) {
                // Although we have return values for collisionCheck,
                // we don't need to store them when we know the result for sure
                int coltype = _pacman.collisionCheck(_ghost);
                if (coltype == 101) {
                    if (!updates.containsKey(_ghost)) {
                        HashSet<Update> gUpdates = new HashSet<>();
                        gUpdates.add(Update.DEATH);
                        updates.put(_ghost, gUpdates);
                    } else {
                        updates.get(_ghost).add(Update.DEATH);
                    }
                    Color ghostColor = _ghost.getColor();
                    Position ghostPos = _ghost.getPosition();
                    int spookLength = _ghost.spookLength;
                    _ghost = ghostColor == Color.RED ? new Ghost.Red(ghostPos.getX(), ghostPos.getY(), spookLength, ghostColor) :
                            ghostColor == Color.BLUE ? new Ghost.Blue(ghostPos.getX(), ghostPos.getY(), spookLength, ghostColor) :
                            ghostColor == Color.YELLOW ? new Ghost.Yellow(ghostPos.getX(), ghostPos.getY(), spookLength, ghostColor) :
                            new Ghost.Pink(ghostPos.getX(), ghostPos.getY(), spookLength, ghostColor);
                } else {
                    if (!updates.containsKey(_pacman)) {
                        HashSet<Update> pUpdates = new HashSet<>();
                        pUpdates.add(Update.DEATH);
                        updates.put(_pacman, pUpdates);
                    }
                    _pacman.death();
                }
            }
            if (objects.get(pacPos) instanceof Consumable) {
                Consumable consumable = objects.get(pacPos);
                if (consumable instanceof BigDot) {
                    _pacman.setSuper();
                    if (!updates.containsKey(_pacman)) {
                        HashSet<Update> pUpdate = new HashSet<>();
                        pUpdate.add(Update.SUPER);
                        updates.put(_pacman, pUpdate);
                    } else {
                        updates.get(_pacman).add(Update.SUPER);
                    }
                    for (Ghost _ghost : ghosts) {
                        if (!updates.containsKey(_ghost)) {
                            HashSet<Update> gUpdate = new HashSet<>();
                            gUpdate.add(Update.SCARED);
                            updates.put(_ghost, gUpdate);
                        } else {
                            updates.get(_ghost).add(Update.SCARED);
                        }
                    }
                }
                if (!updates.containsKey(consumable)) {
                    HashSet<Update> cUpdate = new HashSet<>();
                    cUpdate.add(Update.EATEN);
                    updates.put(consumable, cUpdate);
                } else {
                    updates.get(consumable).add(Update.EATEN);
                }
            }
            _pacman.updateSuper();
            // If Pacman is dead return false and conclude the game
            if (_pacman.getLives() < 0 ) return false;
        }
        //Let each ghost move
        for(Ghost _ghost : ghosts) {
            Position ghostPos = _ghost.getPosition();
            HashMap<Direction, Tile> surr = MapUtils.getSurrounding(tileBoard, ghostPos);
            _ghost.thinkPrep(tileBoard, pacPos);
            Direction move = MapUtils.moveValid(_ghost, surr);
            if (move == Direction.LEFT) {
                ghostPos.translate(-1, 0);
            } else if (move == Direction.UP) {
                ghostPos.translate(0, 1);
            } else if (move == Direction.RIGHT) {
                ghostPos.translate(1,0);
            } else if (move == Direction.DOWN) {
                ghostPos.translate(0,-1);
            }
            _ghost.updateSpooked();
            //do what we want the ghost do here
        }
        redraw(updates);
        updateMaps(updates);
        updates.clear();
        return true;
    }

    public void init(Stage stage) {
        // Inits map singleton and retrieves
        Map map = Map.getMapInstance();
        tileBoard = map.getTiles();
        objects = map.getObjects();
        stage.setScene(null/* scene builder class */);

        // initializes all the required positions for
        // all hashmaps and hashsets
        pacmen = new HashSet<>();
        ghosts = new HashSet<>();
        objectPosition = new HashMap<>();
        updates = new HashMap<>();

    }

    public void redraw(HashMap<GameObject, HashSet<Update>> updates) {

    }

    public void updateMaps(HashMap<GameObject, HashSet<Update>> updates) {
        for (GameObject go : updates.keySet()) {
            if (go instanceof Consumable && updates.get(go).contains(Update.EATEN)) {
                objects.remove(go);
            }
        }
    }
}
