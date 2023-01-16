package jgegroup.pacman.objects;

public abstract class Player extends GameObjects{
    public Player(double x, double y) {
        super(x,y);
    }
    public Player(Position position){
        super(position);
    }
    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void moveUp();
    public abstract void moveDown();

    public abstract void death();
    public abstract void respawn();


}
