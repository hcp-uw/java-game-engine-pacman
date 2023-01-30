package com.jgegroup.pacman.objects;

import com.jgegroup.pacman.SimpleMath;

public abstract class GameObjects {

    protected Position position;
    protected int radius;
    public GameObjects(int x, int y) {
        this.position = new Position(x, y);
    }
    public GameObjects(Position position) {
        this.position = position;
    }

    public Position getPosition(){return this.position;}
    public int getRadius(){return this.radius;}

    public void setRadius(int radius) {this.radius = radius;}
    public byte direction;
    /*
        1 - Up 0001
        2 - Down 0010
        3 - Left 0101
        4 - Right 1000
     */


    public int collisionCheck(GameObjects object){
        if(SimpleMath.getDistance(this.getPosition(),object.getPosition())
                >(this.getRadius()+object.getRadius())){
            //There is no collision
            return -1;
        }
        //Handle the collision, integer returned defines collision
        return collisionHandle(object);
    }

    protected abstract int collisionHandle(GameObjects object);
}
