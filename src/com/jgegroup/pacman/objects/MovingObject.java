package com.jgegroup.pacman.objects;

import com.jgegroup.pacman.SimpleMath;
import com.jgegroup.pacman.objects.Enums.*;
import com.jgegroup.pacman.objects.characters.Ghost;

public abstract class MovingObject extends GameObject {
    protected int radius;
    protected Direction direction;

    public MovingObject(int x, int y) {
        super(x, y);
    }
    public MovingObject(Position position) {
        super(position);
    }

    public int getRadius(){return this.radius;}
    public void setRadius(int radius) {this.radius = radius;}

    public Direction getDirection() { return this.direction; }

    public void setDirection(Direction dir) { this.direction = dir; }

    /** @@Author: Jesse
     * Performs a collision check on an object that this object could
     * have collided with
     * Throws no exceptions
     * @param object
     * @return integer with corresponding collision type
     */
    public int collisionCheck(MovingObject object){
        if(SimpleMath.getDistance(this.getPosition(),object.getPosition())
                >(this.getRadius()+object.getRadius())){
            //There is no collision
            return -1;
        }
        //Handle the collision, integer returned defines collision
        return collisionHandle(object);
    }


    /** @@Author: Lucas
     * Draws a given character input facing 4 directions and
     * returns the characters drawn
     * Throws no exceptions
     * @param obj: moving object like Pacman or ghost
     * @return image array with size of 4.
     */
   /*
    public Image[] getImages(MovingObject obj) {
        Image[] objImage = new Image[4];
        if (obj instanceof Pacman) {
            objImage[0] = new Image("res/characters/Pacman.png");
        } else if (obj instanceof Ghost) { // need to specify which ghost to implement
            objImage[0] = new Image("res/characters/Ghost.png");
        }
        for (int i = 1; i < objImage.length; i++) {
            objImage[i] = rotateCharacter(obj[i-1], 90); // wrote the degree so that we can maybe reuse rotate function in angry bird
        }
        return objImage;
    }

    */

    /** @@Author: Jesse
     * Handles the collision for the inheritor class
     * Throws no exceptions
     * @param object
     * @return integer that dictates the collision type
     */
    protected abstract int collisionHandle(MovingObject object);

}
