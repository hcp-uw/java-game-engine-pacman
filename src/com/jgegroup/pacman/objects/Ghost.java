package com.jgegroup.pacman.objects;

import javafx.scene.paint.Color;


// Death and respawning will be handled by the game cycle by the reassignment of the ghost to
// another ghost.

public class Ghost extends GameObjects {
    private final int spookLength;
    private Color base_color;
    private Color current_color;
    private int spookState;
    public Ghost(int x, int y, int spookLength, Color color) {
        super(x, y);
        this.spookLength = spookLength;
        this.base_color = color;
        this.current_color = color;
        // this indicates that it is not spooked
        this.spookState = -1;
    }

    // Authors: Noah / Nicola
    // Sets spookState to maximum spook state 'spookLength'
    // spook length represents the number of cycles to be spooked
    // Throws no exceptions
    // Returns nothing
    // Takes no parameters
    public void setSpooked() { spookState = spookLength; }

    // Authors: Noah / Nicola
    // Checks the spook state,
    public void checkSpooked() {
        if (spookState >= 0) {
            if (spookState % 2 == 0) {
                this.current_color = Color.WHITE;
            } else {
                this.current_color = Color.BLUE;
            }
            spookState--;
        } else {
            if (!current_color.equals(base_color)) {
                current_color = base_color;
            }
        }
    }

    @Override
    protected int collisionHandle(GameObjects object) {
        if (object instanceof Pacman || object instanceof Wall) {
            return 1;
        }
        return 0;
    }
}
