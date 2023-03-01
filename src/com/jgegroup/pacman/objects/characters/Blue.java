package com.jgegroup.pacman.objects.characters;

import com.jgegroup.pacman.objects.Enums.*;
import com.jgegroup.pacman.objects.immovable.Tile;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class Blue extends Ghost implements GhostMovement {

    /** @@Author: Noah
     *  Blue Ghost
     */
    public Blue(int x, int y, int spookLength, Color color) {
        super(x, y, spookLength, color);
    }

    /** @@Author Noah
     * Chooses the next move for the blue ghost
     * Throws no exceptions
     * Returns nothing
     * @param dx x distance to the pacman
     * @param dy y distance to the pacman
     * @param surr surrounding tiles
     */
    public void normalThink(int dx, int dy, HashMap<Direction, Tile> surr) {

    }

    public void spookedThink(int dx, int dy, HashMap<Direction, Tile> surr) {

    }

}
