package com.jgegroup.pacman;

import com.jgegroup.pacman.objects.*;
import com.jgegroup.pacman.objects.immovable.consumables.Consumable;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Timer;
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

    @Override
    public void start(Stage stage) throws Exception {
        init(stage);
        boolean runGame = true;
        Timer timer = new Timer();
        // Todo: Add a keylistener for Pacman
        while (runGame) {
            runGame = loop(stage.getScene());
            TimeUnit.MILLISECONDS.sleep(1);
        }
    }

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
                _pacman.nextMove=dir_cache;
            //}


            /* TODO: perform check to see if move is valid
             *       if move valid, perform move
             *       otherwise do not move
             */
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
            for(Ghost _ghost : ghosts) {
                // Although we have return values for collisionCheck,
                // we don't need to store them when we know the result for sure
                int coltype = _pacman.collisionCheck(_ghost);

            }
            //TODO: a collision check for consumables
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

    }
}
