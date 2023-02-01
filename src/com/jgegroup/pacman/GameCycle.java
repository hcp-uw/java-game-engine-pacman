package com.jgegroup.pacman;

import com.jgegroup.pacman.objects.*;
import com.jgegroup.pacman.objects.immovable.Wall;
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
    HashMap<HashSet<Position>, HashSet<GameObject>> pixelBoard;
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

    private boolean loop(Scene scene, /* keylister*/) {
        // direction = keylister.get
        // perform check to see if move is valid
            // if move valid, perform move and check for collisions
            // else do not move
        for(Pacman _pacman : pacmen) {
            _pacman.updateSuper();
            //TODO: a better collision check
            for(Ghost _ghost : ghosts) {
                int collision_type = _pacman.collisionCheck(_ghost);

            }
        }
        // direction = ghost.think()
        // perform check to see if move is valid
            // if move valid, perform move and check for collisions
            // else do not move
        for(Ghost _ghost : ghosts) {
            _ghost.updateSpooked();
            //TODO: do what we want the ghost do her
            // We can't put the logic in the inner loop, as multiple pacman will cause
            // multiple moves.
        }
        return true;
    }

    public void init(Stage stage) {
        Map map = Map.getMapInstance();
        board = map.getMap();
        Stage.setScene(/* scene builder class */);
    }

    public boolean moveValid(Position moveTo) {
        HashSet<GameObject> set = board.get(moveTo);
        for (GameObject go : set) {
            if (go instanceof Wall) {
                return false;
            }
        }
        return true;
    }
}
