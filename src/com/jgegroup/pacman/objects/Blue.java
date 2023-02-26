package com.jgegroup.pacman.objects;

import com.jgegroup.pacman.objects.immovable.Wall;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class Blue extends Ghost{

    public Blue(int x, int y, int spookLength, Color color) {
        super(x, y, spookLength, color);
    }

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
