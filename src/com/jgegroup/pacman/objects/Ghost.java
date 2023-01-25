package jgegroup.pacman.objects;


import javafx.scene.paint.Color;

public class Ghost extends GameObjects {

    private Color color;
    private boolean spooked;
    private boolean dead;
    public Ghost(int x, int y, Color color) {
        super(x, y);
        this.color = color;
        this.spooked = false;
        this.dead = false;
    }

    // Todo: Add method signatures/code
    public void setSpooked() {
        spooked = true;
        //TODO: Add a timer, after the timer stopped, set spooked to false.
    }


    public void death() {
        this.dead = true;
        this.position.setX(/* SpawnX */);
        this.position.setY(/* SpawnY */);
    }

    public void respawn() {
        this.dead = false;
    }

    @Override
    protected int collisionHandle(GameObjects object) {
        if (object instanceof Pacman || object instanceof Wall) {
            return 1;
        }
        return 0;
    }
}
