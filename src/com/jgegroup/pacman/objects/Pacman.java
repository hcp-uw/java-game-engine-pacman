package com.jgegroup.pacman.objects;

import com.jgegroup.pacman.objects.immovable.consumables.*;
import com.jgegroup.pacman.objects.Enums.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


public class Pacman extends MovingObject {
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
    // Queue to aid in endless mode functionality
    private Queue<Position> consumed;
    // move container for next move
    public Direction nextMove;
    public Pacman(int spawnX, int spawnY, int superLength) {
        super(spawnX, spawnY);
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.lives = 3;
        this.superLength = superLength;
        this.score = 0;
        this.Super = -1;
        this.direction = Enums.Direction.STOP;
        this.nextMove = Enums.Direction.STOP;
        consumed = new LinkedList<>();
    }

    /** @Authors: Noah / Nicola
     * Decrements lives state and moves Pacman to spawn
     * @Throws no exceptions
     * @Returns true if Pacman has lives remaining or is on last life, false if there are no more lives
     * Takes in no parameters
     **/
    public boolean death() {
        lives--;
        if (lives >= 0) {
            this.position.moveTo(spawnX, spawnY);
            return true;
        }
        return false;
    }

    /** @Author: Noah
     * Updates score of Pacman after it eats something
     * @Throws no exceptions
     * @Returns the position of dot or ghost eaten for front-end
     * Takes in a consumable object as a parameter
    **/
    public Position eat(Consumable consumable) {
        this.score += consumable.score;
        consumed.add(consumable.position);
        return consumable.position;
    }

    public Consumable checkQueue() {
        if (consumed.size() > 16) {
            return Math.round(Math.random() * 100) >= 50 ?
                    new Dot(consumed.remove()) : new BigDot(consumed.remove());
        }
        return null;
    }

    /** @Author Noah
     * Sets the super state
     * @Throws no exceptions
     * @Returns nothing
     * Takes in no parameters
     **/
    public void setSuper() { this.Super += superLength; }

    /** @Author: Noah
     * Updates the super state
     * @Throws no exceptions
     * @Returns true if the pacman is super, else false
     * Takes in no parameters
    **/
    public boolean updateSuper() {
        if (this.Super >= 0) {
            this.Super--;
            return true;
        }
        return false;
    }

    /** @Author: Noah
     * Checks to see if Pacman is super
     * @Throws no exceptions
     * @Returns true if super state container is greater than or equal to 0, else false
     * Takes in no parameters
    */
    public boolean isSuper() { return this.Super >= 0; }

    /** @Author: Noah
     * Gets num of lives left
     * @Throws no exceptions
     * @Returns number of lives left
     * Takes in no parameters
     */
    public int getLives() { return this.lives; }

    /** @Authors: Noah / Jesse
     * Examines the collision between Pacman and Object and handles it
     * @Throws no exceptions
     * @Returns 1 if a collision happens, 0 else
     * Takes in a GameObject as a parameter
    */
    @Override
    protected int collisionHandle(MovingObject object) {
        if (object instanceof Ghost) {
            this.death();
            return 100;
        } else if (object instanceof Pacman) {
            return 101;
        }
//        else if (object instanceof Consumable) {
//            return 3;
//        }
        /*For Pacman, collisionHandle should be and only should be execute
        * when they hit a ghost or a pacman
        * Collision with immovable will be handled separately
        * */
        return 99;
    }

    @Override
    protected void think(Direction dirX, Direction dirY, int dx, int dy, HashMap<Direction, Tile> surr) {
        return;
    }
}
