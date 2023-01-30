package com.jgegroup.pacman;

import com.jgegroup.pacman.objects.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

public class GameCycle extends Application {
    HashSet<Pacman> pacmen;
    HashSet<Ghost>  ghosts;
    HashMap<Position, GameObjects> board;

    @Override
    public void start(Stage stage) throws Exception {
        init(stage);
        boolean runGame = true;
        Timer timer = new Timer();
        while (runGame) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runGame = loop(stage.getScene());
                }
            }, 100 /* <- delay for now */);

        }
    }

    private boolean loop(Scene scene) {
        // Todo: Add a method to get keypress for Pacman, and get the direction for Ghost
        //  Then it will check to see if the move is valid, then will check collisions between other objects.
        for(Pacman _pacman : pacmen) {
            _pacman.updateSuper();
            //TODO: a better collision check
            for(Ghost _ghost : ghosts) {
                int collision_type = _pacman.collisionCheck(_ghost);
            }
        }
        for(Ghost _ghost : ghosts) {
            _ghost.updateSpooked();
            //TODO: do what we want the ghost do her
            // We can't put the logic in the inner loop, as multiple pacman will cause
            // multiple moves.
        }
    }

    public void init(Stage stage) {
        Map map = Map.getMapInstance();
        board = map.getMap();
        Stage.setScene(/* scene builder class */);
    }
}
