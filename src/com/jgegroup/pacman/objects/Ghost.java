package jgegroup.pacman.objects;

import java.awt.*;

public class Ghost extends Player {

    private Color color;
    private boolean spooked;
    public Ghost(int x, int y, Color color) {
        super(x, y);
        this.color=color;
        spooked = false;
    }

    public void setSpooked() {
        spooked = true;
        //Add a timer, after the timer stopped, set spooked to false.
    }


    public void moveLeft() {
        // test
        System.out.println("test");
        System.out.println("Iman's test HAHA");

    }

    public void moveRight() {

    }

    public void moveUp() {

    }

    public void moveDown() {

    }

    public void death() {

    }

    public void respawn() {

    }

    @Override
    protected int collisionHandle(GameObjects object) {
        return 0;
    }
}
