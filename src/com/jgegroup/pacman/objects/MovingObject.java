package com.jgegroup.pacman.objects;

import com.jgegroup.pacman.SimpleMath;
import com.jgegroup.pacman.objects.Enums.*;

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



    public int collisionCheck(MovingObject object){
        if(SimpleMath.getDistance(this.getPosition(),object.getPosition())
                >(this.getRadius()+object.getRadius())){
            //There is no collision
            return -1;
        }
        //Handle the collision, integer returned defines collision
        return collisionHandle(object);
    }

    protected abstract int collisionHandle(MovingObject object);

    protected abstract void think(Direction dirX, Direction dirY, int dx, int dy);
}
