package com.jgegroup.pacman.objects;

import javafx.scene.paint.Color;


// Death and respawning will be handled by the game cycle by the reassignment of the ghost to
// another ghost.
public class Ghost extends GameObjects {

    private Color color;
    private boolean spooked;
    public Ghost(int x, int y, Color color) {
        super(x, y);
        this.color = color;
        this.spooked = false;
    }

    // Todo: Add method signatures/code
    public void setSpooked() {
        spooked = true;
        //TODO: Add a timer, after the timer stopped, set spooked to false.
    }

    @Override
    protected int collisionHandle(GameObjects object) {
        if (object instanceof Pacman || object instanceof Wall) {
            return 1;
        }
        return 0;
    }
}
