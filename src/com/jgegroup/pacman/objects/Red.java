package com.jgegroup.pacman.objects;

import com.jgegroup.pacman.objects.immovable.Wall;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class Red extends Ghost {
        public Red(int x, int y, int spookLength, Color color) {
            super(x, y, spookLength, color);
        }

        public void think(Enums.Direction dirX, Enums.Direction dirY, int dx, int dy, HashMap<Enums.Direction, Tile> surr) {
            dirX = (surr.get(dirX) instanceof Wall) ? Enums.Direction.STOP : dirX;
            dirY = (surr.get(dirY) instanceof Wall) ? Enums.Direction.STOP : dirY;
            if (dirY != Enums.Direction.STOP && dirX != Enums.Direction.STOP) {
                this.direction = Math.abs(dx) <= Math.abs(dy) && dx != 0 ? dirX : dirY;
            } else if (dirX == Enums.Direction.STOP) {
                this.direction = dirY;
            } else {
                this.direction = dirX;
            }
        }
}
