package src.com.jgegroup.pacman.objects;

import java.awt.*;

public abstract class Player {
    private Point position;

    public Player(int x, int y) {
        this.position = new Point(x,y);
    }
    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void moveUp();
    public abstract void moveDown();

    public abstract void death();
    public abstract void respawn();
}
