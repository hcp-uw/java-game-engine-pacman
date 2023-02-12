package com.jgegroup.pacman;

import com.jgegroup.pacman.objects.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class GameCycle extends Application {
    HashSet<Pacman> pacmen;
    HashSet<Ghost>  ghosts;
    //Each pixel refer to an
    HashMap<Position, Tile> tileBoard;
    //Object board is used to store consumables
    //NOTICE:The position ratio of pixel:object:tile is 16:4:1
    HashMap<Position, Tile> objectBoard;


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

    private boolean loop(Scene scene /*, keylister*/) {
        // direction = keylister.get
        // perform check to see if move is valid
            // if move valid, perform move and check for collisions
            // else do not move

        for(Pacman _pacman : pacmen) {
            /* TODO: perform check to see if move is valid
             *       if move valid, perform move
             *       otherwise do not move
             */
            for(Ghost _ghost : ghosts) {
                //Although we have return values for collisionCheck,
                // we don't need to store them when we know the result for sure
                _pacman.collisionCheck(_ghost);

            }
            //TODO: a collision check for consumables
            _pacman.updateSuper();
        }
        //Let each ghost move
        for(Ghost _ghost : ghosts) {
            _ghost.think();
            _ghost.updateSpooked();
            //do what we want the ghost do here
        }
        return true;
    }

    public void init(Stage stage) {
        Map map = Map.getMapInstance();
        tileBoard = map.getMap();
        stage.setScene(null/* scene builder class */);
    }
}
