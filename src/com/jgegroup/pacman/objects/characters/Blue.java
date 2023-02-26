package com.jgegroup.pacman.objects.characters;

import com.jgegroup.pacman.objects.Enums.*;
import com.jgegroup.pacman.objects.immovable.Tile;
import com.jgegroup.pacman.objects.immovable.Wall;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class Blue extends Ghost implements GhostMovement{

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
    @Override
    public void normalThink(Direction dirX, Direction dirY, int dx, int dy, HashMap<Direction, Tile> surr) {
        dirX = (surr.get(dirX) instanceof Wall) ? Direction.STOP : dirX;
        dirY = (surr.get(dirY) instanceof Wall) ? Direction.STOP : dirY;
        if (dirY != Direction.STOP && dirX != Direction.STOP) {
            this.direction = Math.abs(dy) <= Math.abs(dx) && dx != 0 ? dirY : dirX;
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
