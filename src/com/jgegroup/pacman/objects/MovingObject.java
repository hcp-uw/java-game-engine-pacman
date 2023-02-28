package com.jgegroup.pacman.objects;

import com.jgegroup.pacman.SimpleMath;
import com.jgegroup.pacman.objects.Enums.*;
import com.jgegroup.pacman.objects.immovable.Tile;

import java.util.HashMap;

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

    /** @@Author: Jesse
     * Handles the collision for the inheritor class
     * Throws no exceptions
     * @param object
     * @return integer that dictates the collision type
     */
    protected abstract int collisionHandle(MovingObject object);

}
