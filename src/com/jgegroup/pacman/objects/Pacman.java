package com.jgegroup.pacman.objects;

import java.util.LinkedList;
import java.util.Queue;


public class Pacman extends GameObjects {
    // Interval length for how long pacman can be super after eating a big dot
    private final int superLength;
    // Super state container for pacman, >= 0 <- super, < 0 <- not super
    private int Super;
    // Number of lives for Pacman, initially set to 3
    private int lives;
    // Score container
    private int score;
    // spawnX and spawnY are the original position of pacman
    // at start of game
    private final int spawnX;
    private final int spawnY;
    private Queue<Position> consumed;

    public Pacman(int spawnX, int spawnY, int superLength) {
        super(spawnX, spawnY);
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.lives = 3;
        this.superLength = superLength;
        this.score = 0;
        this.Super = -1;
        this.direction = 0;
        consumed = new LinkedList<>();
    }

    // Authors: Noah / Nicola
    // Decrements lives state and moves Pacman to spawn
    // Throws no exceptions
    // Returns true if Pacman has lives remaining or is on last life, false if there are no more lives
    // Takes in no parameters
    public boolean death() {
        lives--;
        if (lives >= 0) {
            this.position.moveTo(spawnX, spawnY);
            return true;
        }
        return false;
    }

    // Author: Noah
    // Updates score of Pacman after it eats something
    // Throws no exceptions
    // Returns nothing
    // Takes in a consumable object as a parameter
    public void eat(Consumables consumable) {
        this.score += consumable.score;
        consumed.add(consumable.position);
    }

    public Consumables checkQueue() {
        if (consumed.size() > 16) {
            return Math.round(Math.random() * 100) >= 50 ?
                    new Dot(consumed.remove()) : new BigDot(consumed.remove());
        }
        return null;
    }

    // Author: Noah
    // Sets the super state
    // Throws no exceptions
    // Returns nothing
    // Takes in no parameters
    public void setSuper() { this.Super += superLength; }

    // Author: Noah
    // Updates the super state
    // Throws no exceptions
    // Returns true if the pacman is super, else false
    // Takes in no parameters
    public boolean updateSuper() {
        if (this.Super >= 0) {
            this.Super--;
            return true;
        }
        return false;
    }

    // Author: Noah
    // Checks to see if Pacman is super
    // Throws no exceptions
    // Returns true if super state container is greater than or equal to 0, else false
    // Takes in no parameters
    public boolean isSuper() { return this.Super >= 0; }

    // Authors: Noah / Jesse
    // Examines the collision between Pacman and Object and handles it
    // Throws no exceptions
    // Returns 1 if a collision happens, 0 else
    // Takes in a GameObject as a parameter
    @Override
    protected int collisionHandle(GameObjects object) {
        if (object instanceof Ghost) {
            this.death();
            return 1;
        } else if (object instanceof Wall) {
            return 2;
        } else if (object instanceof Consumables) {
            this.eat((Consumables)object);
            return 3;
        }
        return 0;
    }
}
