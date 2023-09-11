package com.jgegroup.pacman.objects.characters;

import com.jgegroup.pacman.objects.consumables.Consumable;
import com.jgegroup.pacman.objects.Enums;
import com.jgegroup.pacman.objects.MovingObject;
import com.jgegroup.pacman.objects.Enums.*;
import javafx.scene.image.Image;

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
    private Queue<Consumable> consumed;
    // move container for next move
    public Direction nextMove;

    public Image[] images;


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

    /** @@Authors: Noah / Nikola
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

    /** @@Author: Noah
     * Updates score of Pacman after it eats something
     * @Throws no exceptions
     * @Returns the position of dot or ghost eaten for front-end
     * Takes in a consumable object as a parameter
    **/
    public Consumable eat(Consumable consumable) {
        this.score += consumable.getScore();
        consumed.add(consumable);
        return consumable;
    }

    /** @@Author: Noah
     *  Returns the score variable
     *  Throws no exceptions
     *  @return Pacman's score
     *  Takes in no parameters
     */
    public int getScore() { return this.score; }

    /** @@Author: Noah
     *  Returns the consumable that was first eaten in the queue
     *  Throws no exceptions
     *  @return A new consumable with the position of the first eaten consumable thats in the queue
     *  Takes in no params
     */
    public Consumable checkQueue() {
        if (consumed.size() > 16) {
            return consumed.remove();
        }
        return null;
    }

    /** @@Author Noah
     * Sets the super state
     * @Throws no exceptions
     * @Returns nothing
     * Takes in no parameters
     **/
    public void setSuper() {
        if (this.Super <= 0)
            this.Super = superLength;
        else
            this.Super += superLength;
    }

    /** @@Author: Noah
     * Updates the super state
     * @Throws no exceptions
     * @Returns true if the pacman is super, else false
     * Takes in no parameters
    **/
    public boolean updateSuper() {
        if (this.Super > 0) {
            this.Super--;
            return true;
        }
        return false;
    }

    /** @@Author: Noah
     * Checks to see if Pacman is super
     * @Throws no exceptions
     * @Returns true if super state container is greater than or equal to 0, else false
     * Takes in no parameters
    */
    public boolean isSuper() { return this.Super >= 0; }

    /** @@Author: Noah
     * Gets the remaining super cycles
     * Throws no exceptions
     * @return Integer denoting how many super cycles are left
     * Takes in no parameters
     */
    public int getSuper() { return this.Super; }

    /** @@Author: Noah
     * Gets num of lives left
     * @Throws no exceptions
     * @Returns number of lives left
     * Takes in no parameters
     */
    public int getLives() { return this.lives; }

    @Override
    protected int collisionHandle(MovingObject object) {
        return 0;
    }

    /** @@Authors: Noah / Jesse
     * Examines the collision between Pacman and Object and handles it
     * @Throws no exceptions
     * @Returns 100 if a collision with a ghost happened and this was not super, 101 if it was super, 102 if the
     * other object was a pacman, 99 for all other cases
     * Takes in a GameObject as a parameter
    */
//    @Override
//    public int collisionHandle(MovingObject object) {
//        if (object instanceof Ghost) {
//            if (!this.isSuper()) {
//                return 100;
//            }
//            return 101;
//        } else if (object instanceof Pacman) {
//            return 102;
//        }
//        /*For Pacman, collisionHandle should be and only should be execute
//        * when they hit a ghost or a pacman
//        * Collision with immovable will be handled separately
//        * */
//        return 99;
//    }

//    public void loadImages() {
//        images = new Image[4];
//        images[0] = new Image("characters/Pacman.png");
//        images[1] =
//    }

}
