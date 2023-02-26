package com.jgegroup.pacman.objects.characters;

import com.jgegroup.pacman.objects.Enums;
import com.jgegroup.pacman.objects.immovable.Tile;
import com.jgegroup.pacman.objects.immovable.Wall;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class Yellow extends Ghost {
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
    public void think(Enums.Direction dirX, Enums.Direction dirY, int dx, int dy, HashMap<Enums.Direction, Tile> surr) {
        dirX = (surr.get(dirX) instanceof Wall) ? Enums.Direction.STOP : dirX;
        dirY = (surr.get(dirY) instanceof Wall) ? Enums.Direction.STOP : dirY;
        if (dirY != Enums.Direction.STOP && dirX != Enums.Direction.STOP) {
            this.direction = Math.abs(dx) >= Math.abs(dy) && dx != 0 ? dirX : dirY;
        } else if (dirX == Enums.Direction.STOP) {
            this.direction = dirY;
        } else {
            this.direction = dirX;
        }
    }
}
