package jgegroup.pacman.objects;

import jgegroup.pacman.SimpleMath;

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


    public int collisionCheck(GameObjects object){
        if(SimpleMath.getDistance(this.getPosition(),object.getPosition())
                >(this.getRadius()+object.getRadius())){
            //There is no collision
            return 1;
        }
        //Handle the collision, return 0 indicates success handled
        return collisionHandle(object);
    }

    protected abstract int collisionHandle(GameObjects object);
}
