package jgegroup.pacman.objects;

public abstract class Player {
    private Position position;

    public Player(double x, double y) {
        this.position = new Position(x,y);
    }
    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void moveUp();
    public abstract void moveDown();

    public abstract void death();
    public abstract void respawn();

    public Position getPosition(){return position;}
}
