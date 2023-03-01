package com.jgegroup.pacman.objects.characters;

import com.jgegroup.pacman.objects.Enums.*;
import com.jgegroup.pacman.objects.immovable.Tile;
import com.jgegroup.pacman.objects.immovable.Wall;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class Yellow extends Ghost implements GhostMovement {
    public Yellow(int x, int y, int spookLength, Color color) {
        super(x, y, spookLength, color);
    }

    /** @@Author: Noah
     * Enables the yellow ghost to process its next move
     * Throws no exceptions
     * Returns nothing
     * @param dx
     * @param dy
     * @param surr
     */
    public void normalThink(int dx, int dy, HashMap<Direction, Tile> surr) {

    }

    public void spookedThink(int dx, int dy, HashMap<Direction, Tile> surr) {

    }
}
