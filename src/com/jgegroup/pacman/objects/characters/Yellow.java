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
     * @param dirX
     * @param dirY
     * @param dx
     * @param dy
     * @param surr
     */
    public void normalThink(Direction dirX, Direction dirY, int dx, int dy, HashMap<Direction, Tile> surr) {
        dirX = (surr.get(dirX) instanceof Wall) ? Direction.STOP : dirX;
        dirY = (surr.get(dirY) instanceof Wall) ? Direction.STOP : dirY;
        if (dirY != Direction.STOP && dirX != Direction.STOP) {
            this.direction = Math.abs(dx) >= Math.abs(dy) && dx != 0 ? dirX : dirY;
        } else if (dirX == Direction.STOP) {
            this.direction = dirY;
        } else {
            this.direction = dirX;
        }
    }

    @Override
    public void spookedThink(Direction dirX, Direction dirY, int dx, int dy, HashMap<Direction, Tile> surr) {

    }
}
