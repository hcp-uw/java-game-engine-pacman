package com.jgegroup.pacman.objects.characters;

import com.jgegroup.pacman.objects.Enums.*;
import com.jgegroup.pacman.objects.immovable.Tile;
import com.jgegroup.pacman.objects.immovable.Wall;
import javafx.scene.paint.Color;

import java.util.HashMap;

import static com.jgegroup.pacman.objects.MapUtils.validateMove;

public class Pink extends Ghost implements GhostMovement{
    public Pink(int x, int y, int spookLength) {
        super(x, y, spookLength, Color.PINK);
    }

    /** @@Author: Noah
     * Enables the pink ghost to process its next move in normal state
     * Throws no exceptions
     * Returns nothing
     * @param dx
     * @param dy
     * @param surr
     */
    public void normalThink(int dx, int dy, HashMap<Direction, Tile> surr) {
        Direction dX = Direction.STOP, dY = Direction.STOP;
        if (dx != 0) {
            dX = dx < 0 ? Direction.LEFT : Direction.RIGHT;
            dX = validateMove(dX, surr);
        }
        if (dy != 0) {
            dY = dy < 0 ? Direction.DOWN : Direction.UP;
            dY = validateMove(dY, surr);
        }
        if (dX != Direction.STOP && dY != Direction.STOP) {
            if (dx < dy) {
                this.setDirection(dX);
            } else {
                this.setDirection(dY);
            }
        }
        if (dX != Direction.STOP)
            this.setDirection(dX);
        if (dY != Direction.STOP)
            this.setDirection(dY);
    }

    /** @@Author: Noah
     * Enables the pink ghost to process its next move in spooked state
     * @param dx
     * @param dy
     * @param surr
     */
    public void spookedThink(int dx, int dy, HashMap<Direction, Tile> surr) {
        Direction dX = Direction.STOP, dY = Direction.STOP;
        dX = dx >= 0 ? Direction.LEFT : Direction.RIGHT;
        dX = validateMove(dX, surr);
        dY = dy >= 0 ? Direction.DOWN : Direction.UP;
        dY = validateMove(dY, surr);
        if (dX != Direction.STOP && dY != Direction.STOP) {
            if (dx < dy) {
                this.setDirection(dX);
            } else {
                this.setDirection(dY);
            }
        }
        if (dX != Direction.STOP)
            this.setDirection(dX);
        if (dY != Direction.STOP)
            this.setDirection(dY);
    }
}
