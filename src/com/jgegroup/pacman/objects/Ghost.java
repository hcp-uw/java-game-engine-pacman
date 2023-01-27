package com.jgegroup.pacman.objects;

import javafx.scene.paint.Color;


// Death and respawning will be handled by the game cycle by the reassignment of the ghost to
// another ghost.

public class Ghost extends GameObjects {
    // Spook length is the cycle length interval for how long a ghost will
    // be spooked. Can exceed this length if more big dots are eaten
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
    public void setSpooked() { spookState += spookLength; }

    // Authors: Noah / Nicola
    // Checks the spook state, if the state is even it will change the color of the ghost
    // to white, and if its odd then it will change to blue, if not spooked it will return to original color
    // Throws no exceptions
    // Returns nothing
    // Takes no parameters
    public void checkSpooked() {
        if (spookState >= 0) {
            if (spookState % 2 == 0) {
                this.current_color = Color.WHITE;
            } else {
                this.current_color = Color.BLUE;
            }
            spookState--;
            if (spookState < 0) {
                this.current_color = base_color;
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
