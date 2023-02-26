package com.jgegroup.pacman.objects.characters;

import com.jgegroup.pacman.objects.Enums;
import com.jgegroup.pacman.objects.immovable.Tile;
import com.jgegroup.pacman.objects.immovable.Wall;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class Blue extends Ghost {

    /** @@Author: Noah
     *  Blue Ghost
     */
    public Blue(int x, int y, int spookLength, Color color) {
        super(x, y, spookLength, color);
    }

    /** @@Author Noah
     * Chooses the next move for the blue ghost
     * Throws no exceptions
     * Returns nothin
     * @param dirX direction X
     * @param dirY direction Y
     * @param dx x distance to the pacman
     * @param dy y distance to the pacman
     * @param surr surrounding tiles
     */
    public void think(Enums.Direction dirX, Enums.Direction dirY, int dx, int dy, HashMap<Enums.Direction, Tile> surr) {
        dirX = (surr.get(dirX) instanceof Wall) ? Enums.Direction.STOP : dirX;
        dirY = (surr.get(dirY) instanceof Wall) ? Enums.Direction.STOP : dirY;
        if (dirY != Enums.Direction.STOP && dirX != Enums.Direction.STOP) {
            this.direction = Math.abs(dy) <= Math.abs(dx) && dx != 0 ? dirY : dirX;
        } else if (dirX == Enums.Direction.STOP) {
            this.direction = dirY;
        } else {
            this.direction = dirX;
        }
    }
}
