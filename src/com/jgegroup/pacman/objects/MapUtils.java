package com.jgegroup.pacman.objects;

import java.util.HashMap;
import com.jgegroup.pacman.objects.Enums.*;
import com.jgegroup.pacman.objects.immovable.Path;


public class MapUtils {
    public static final int tileSize = 32;

    /*
     * Authors: Noah, Jesse
     * Gets the surrounding tiles from the map for a given position
     * Throws no Exceptions
     * Returns the surround tiles
     * Takes in board and the position in question
     */
    public static HashMap<Direction,Tile> getSurrounding(HashMap<Position, Tile> map, Position pos) {
        HashMap<Direction, Tile> surrounding = new HashMap<>(8);
        surrounding.put(Direction.UP, map.get(new Position(pos.getX(), pos.getY() + 1)));
        surrounding.put(Direction.LEFT, map.get(new Position(pos.getX() + 1, pos.getY())));
        surrounding.put(Direction.DOWN, map.get(new Position(pos.getX(), pos.getY() - 1)));
        surrounding.put(Direction.RIGHT, map.get(new Position(pos.getX() - 1, pos.getY())));
        return surrounding;
    }

    /*
     * Authors: Noah, Jesse
     * Determines if a move is valid, if it is it sends back the next move
     * Throws no exceptions
     * Returns a move
     * Takes in the object, and the surrounding tiles
     */
    public static Direction moveValid(MovingObject object, HashMap<Direction, Tile> surr) {
        if (object instanceof Pacman) {
            if (((Pacman)object).nextMove != Direction.STOP) {
                Direction nextDir = ((Pacman) object).nextMove;
                if (surr.get(nextDir) instanceof Path) {
                    return nextDir;
                }
            }
        }
        Direction dir = object.direction;
        if (surr.get(dir) instanceof Path) {
            return dir;
        }
        return Direction.STOP;
    }

    /*
     * Authors: Noah, Jesse
     * Translates the screen tile position to the world position size
     * Throws no exceptions
     * Returns a new position with the world position
     * Takes in screen position
     */
    public static Position screenToWorld(Position pos) {
        return new Position(pos.getX()*tileSize, pos.getY()*tileSize);
    }

    /*
     * Authors: Noah, Jesse
     * Translates the world position to the screen tile position
     * Throws no exceptions
     * Returns a new position with the screen tile position
     * Takes in a world position
     */
    public static Position worldToScreen(Position pos) {
        return new Position(pos.getX() / tileSize, pos.getY() / tileSize);
    }
}
