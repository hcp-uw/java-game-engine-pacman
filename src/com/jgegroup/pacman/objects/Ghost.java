package jgegroup.pacman.objects;

public class Ghost extends Player {

    private String color;
    private boolean spooked;
    public Ghost(int x, int y, String color) {
        super(x, y);
        spooked = false;
    }

    public void setSpooked() { spooked = true; }


    public void moveLeft() {

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
}
