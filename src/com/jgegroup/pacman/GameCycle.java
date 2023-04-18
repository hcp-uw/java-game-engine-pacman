package com.jgegroup.pacman;

import com.jgegroup.pacman.objects.*;
import com.jgegroup.pacman.objects.characters.*;
import com.jgegroup.pacman.objects.immovable.Tile;
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
    HashMap<Position, Consumable> consumables;

    // holds position of dot that was eaten by
    // pacman, to then be used by frontend to remove the
    // pixel
    Consumable dotPosition = null;
    private boolean paused = false;

    // this is used to cache the direction
    // so it can be later passed to the pacman
    public static Direction dir_cache;
    public HashMap<GameObject, HashSet<Enums.Update>> updates;

    public static void main(String[] args) {
        launch(args);
    }
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
        // scan for player input
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
            // Move cached direction into nextMove
            _pacman.nextMove = dir_cache;

            // Set Pacman position
            pacPos = _pacman.getPosition();
            // Get surrounding tiles from POV of Pacman
            HashMap<Direction, Tile> surr = MapUtils.getSurrounding(tileBoard, pacPos);
            // Validate move
            Direction move = MapUtils.moveValid(_pacman, surr);
            // Process move
            if (move == Direction.LEFT) {
                pacPos.translate(-1, 0);
            } else if (move == Direction.UP) {
                pacPos.translate(0, 1);
            } else if (move == Direction.RIGHT) {
                pacPos.translate(1,0);
            } else if (move == Direction.DOWN) {
                pacPos.translate(0,-1);
            }
            // Add Pacman movement to update hashmap
            if (move != Direction.STOP) {
                if (!updates.containsKey(_pacman)) {
                    HashSet<Update> pUpdates = new HashSet<>();
                    pUpdates.add(Update.MOVED);
                    updates.put(_pacman, pUpdates);
                } else {
                    updates.get(_pacman).add(Update.MOVED);
                }
            }
            // Process Collision checks with Ghosts from the POV of Pacman
            for(Ghost _ghost : ghosts) {
                // Although we have return values for collisionCheck,
                // we don't need to store them when we know the result for sure
                // Get the collision type
                int coltype = _pacman.collisionCheck(_ghost);
                // if collided with Pacman
                if (coltype == 101) {
                    // If pacman is Super add death to updates
                    if (!updates.containsKey(_ghost)) {
                        HashSet<Update> gUpdates = new HashSet<>();
                        gUpdates.add(Update.DEATH);
                        updates.put(_ghost, gUpdates);
                    } else {
                        updates.get(_ghost).add(Update.DEATH);
                    }
                    // respawn the ghost
                    Color ghostColor = _ghost.getColor();
                    Position ghostPos = _ghost.getPosition();
                    int spookLength = _ghost.spookLength;
                    _ghost = ghostColor == Color.RED ? new Red(ghostPos.getX(), ghostPos.getY(), spookLength) :
                            ghostColor == Color.BLUE ? new Blue(ghostPos.getX(), ghostPos.getY(), spookLength) :
                            ghostColor == Color.YELLOW ? new Yellow(ghostPos.getX(), ghostPos.getY(), spookLength) :
                            new Pink(ghostPos.getX(), ghostPos.getY(), spookLength);
                    // end pacman is super
                } else {
                    // else Pacman is not super, add pacman death to updates

                    // call pacman death
                    boolean alive = _pacman.death();
                    if (!alive) {
                        return false;
                    } else {
                        if (!updates.containsKey(_pacman)) {
                            HashSet<Update> pUpdates = new HashSet<>();
                            pUpdates.add(Update.DEATH);
                            updates.put(_pacman, pUpdates);
                        } else {
                            updates.get(_pacman).add(Update.DEATH);
                        }
                    }

                }
            }
            // Process consumable collisions
            if (consumables.get(pacPos) instanceof Consumable) {
                // get the consumable at Pacpos
                Consumable consumable = consumables.get(pacPos);
                // check to see if it is a BigDot
                if (consumable instanceof BigDot) {
                    // set the super state for Pacman
                    _pacman.setSuper();
                    // Add the super state to updates
                    if (!updates.containsKey(_pacman)) {
                        HashSet<Update> pUpdate = new HashSet<>();
                        pUpdate.add(Update.SUPER);
                        updates.put(_pacman, pUpdate);
                    } else {
                        updates.get(_pacman).add(Update.SUPER);
                    }
                    // add the scared state to all the ghosts
                    for (Ghost _ghost : ghosts) {
                        // set the spooked state
                        _ghost.setSpooked();
                        // add the spooked state to the updates map
                        if (!updates.containsKey(_ghost)) {
                            HashSet<Update> gUpdate = new HashSet<>();
                            gUpdate.add(Update.SCARED);
                            updates.put(_ghost, gUpdate);
                        } else {
                            updates.get(_ghost).add(Update.SCARED);
                        }
                    }
                }
                // Add the consumable to the updates map with update eaten
                if (!updates.containsKey(consumable)) {
                    HashSet<Update> cUpdate = new HashSet<>();
                    cUpdate.add(Update.EATEN);
                    updates.put(consumable, cUpdate);
                } else {
                    updates.get(consumable).add(Update.EATEN);
                }
            }
            // end of pacman loop, update the super state, check for game over
            _pacman.updateSuper();
            Consumable consumable = _pacman.checkQueue();
            if (consumable != null) {
                if (!updates.containsKey(consumable)) {
                    HashSet<Update> cUpdate = new HashSet<>();
                    cUpdate.add(Update.RESPAWN);
                    updates.put(consumable, cUpdate);
                } else {
                    updates.get(consumable).add(Update.RESPAWN);
                }
            }
            // If Pacman is dead return false and conclude the game
            if (_pacman.getLives() < 0 ) return false;
        }
        // Process Ghosts
        for(Ghost _ghost : ghosts) {
            // Process movement begin
            // get position
            Position ghostPos = _ghost.getPosition();
            // get surrounding from POV of ghost
            HashMap<Direction, Tile> surr = MapUtils.getSurrounding(tileBoard, ghostPos);
            // Process move
            _ghost.thinkPrep(tileBoard, pacPos);
            // Validate move
            Direction move = MapUtils.moveValid(_ghost, surr);
            // Update position
            if (move == Direction.LEFT) {
                ghostPos.translate(-1, 0);
            } else if (move == Direction.UP) {
                ghostPos.translate(0, 1);
            } else if (move == Direction.RIGHT) {
                ghostPos.translate(1,0);
            } else if (move == Direction.DOWN) {
                ghostPos.translate(0,-1);
            }
            // Add moved to updates map for ghost
            if (!updates.containsKey(_ghost)) {
                HashSet<Update> gUpdates = new HashSet<>();
                gUpdates.add(Update.MOVED);
                updates.put(_ghost, gUpdates);
            } else {
                updates.get(_ghost).add(Update.MOVED);
            }
            // Process ghost movement end
            // end of ghost loop process spooked state
            _ghost.updateSpooked();
        }
        // Process updates and redraw
        redraw(updates);
        // Clear the updates map for next loop cycle
        updates.clear();
        return true;
    }

    public void init(Stage stage) {
        // Inits map singleton and retrieves
        Map map = Map.getMapInstance();
        tileBoard = map.getTiles();
        consumables = map.getObjects();
        GameScene gameScene = new GameScene();
        stage.setScene(gameScene.gameScene);

        // initializes all the required positions for
        // all hashmaps and hashsets
        pacmen = new HashSet<>();
        Position pacSpawn = map.getPacmanSpawn();
        pacmen.add(new Pacman(pacSpawn.getX(), pacSpawn.getY(), 10));
        for (Pacman pacman : pacmen) {
            map.drawPacman(pacman);
        }
        ghosts = new HashSet<>();
        Position ghostSpawn = map.getGhostSpawn();
        ghosts.add(new Blue(ghostSpawn.getX() - 2, ghostSpawn.getY(), 10));
        ghosts.add(new Red(ghostSpawn.getX() - 1, ghostSpawn.getY(), 10));
        ghosts.add(new Pink(ghostSpawn.getX() + 1, ghostSpawn.getY(), 10));
        ghosts.add(new Yellow(ghostSpawn.getX() + 2, ghostSpawn.getY(), 10));
        for (Ghost ghost : ghosts) {
            map.drawGhost(ghost);
        }
        updates = new HashMap<>();

    }

    public void redraw(HashMap<GameObject, HashSet<Update>> updates) {
        for (GameObject gameObject : updates.keySet()) {
            // if game object is consumable: (Dot, or BigDot)
            if (gameObject instanceof Consumable) {
                // if it was eaten remove from map
                if (updates.get(gameObject).contains(Update.EATEN)) {
                    Position pos = gameObject.getPosition();
                    consumables.remove(pos);
                }
                // else if respawned, add to map
                else if (updates.get(gameObject).contains(Update.RESPAWN)) {
                    consumables.put(gameObject.getPosition(), (Consumable) gameObject);
                }
            // else if game object is pacman
            } else if (gameObject instanceof Pacman) {

            }
            // else if game object is Ghost
            else if (gameObject instanceof Ghost) {

            }
            Map.getMapInstance().drawDot();
        }
    }
}
