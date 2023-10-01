package com.jgegroup.pacman.objects.characters;

import com.jgegroup.pacman.objects.Enums.*;
import javafx.scene.paint.Color;

import java.util.Random;

public class GhostMovement {
    private Color color;
    private Pac pac;
    private Random random;
    public GhostMovement(Color color, Pac pac) {
        this.color = color;
        this.pac = pac;
        random = new Random();
    }

    /**
     * Gets the next move of a ghost
     * @param x current x position
     * @param y current y position
     * @return direction of the ghost's next move
     */
    public Direction nextMove(int x, int y) {
        if (color.equals(Color.RED)) {
            return redMove(x, y);
        } else if (color.equals(Color.BLUE)) {
            return blueMove(x, y);
        } else if (color.equals(Color.YELLOW)) {
            return yellowMove(x, y);
        } else if (color.equals(Color.PINK)) {
            return gainMove(x, y);
        } else if (color.equals(Color.GHOSTWHITE)) {
            return ghostMove(x, y);
        }
        return Direction.STOP;
    }


    private Direction redMove(int x, int y) {
        int dx = pac.x - x;
        int dy = pac.y - y;

        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx < 0) {
                return Direction.LEFT;
            } else if (dx > 0) {
                return Direction.RIGHT;
            }
        } else {
            if (dy < 0) {
                return Direction.UP;
            } else if (dy > 0) {
                return Direction.DOWN;
            }
        }
        return Direction.STOP;
    }

    private Direction blueMove(int x, int y) {
        int dx = pac.x - x;
        int dy = pac.y - y;
        if (Math.abs(dx) > Math.abs(dy)) {
            return dx < 0 ? Direction.LEFT : Direction.RIGHT;
        } else if (dy != 0){
            int dec = random.nextInt(128);
            dec %= 2;
            if (dec == 1) {
                dy = -dy;
            }
            return dy > 0 ? Direction.DOWN : Direction.UP;
        }
        return Direction.STOP;
    }

    private Direction yellowMove(int x, int y) {
        int dx = pac.x - x;
        int dy = pac.y - y;
        if (Math.abs(dx) > Math.abs(dy)) {
            int dec = random.nextInt(128);
            dec %= 2;
            if (dec == 1) {
                dx = -dx;
            }
            return dx > 0 ? Direction.RIGHT : Direction.LEFT;
        } else if (dy != 0) {
            return dy < 0 ? Direction.DOWN : Direction.UP;
        }
        return Direction.STOP;
    }

    private Direction gainMove(int x, int y) {
        int dx = pac.x - x;
        int dy = pac.y - y;
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx < 0) {
                return Direction.RIGHT;
            } else if (dx > 0) {
                return Direction.LEFT;
            }
        } else {
            if (dy < 0) {
                return Direction.UP;
            } else if (dy > 0) {
                return Direction.DOWN;
            }
        }
        return Direction.STOP;
    }

    private Direction ghostMove(int x, int y) {
        int dec = random.nextInt(128) % 4;
        return Direction.values()[dec];
    }
}
