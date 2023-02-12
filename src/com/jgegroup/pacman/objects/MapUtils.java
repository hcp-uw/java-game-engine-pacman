package com.jgegroup.pacman.objects;

import java.util.HashMap;
import com.jgegroup.pacman.objects.Enums.*;


public class MapUtils {

    public final int NUMBER_OF_TILE_VERTICAL = 24;
    public final int NUMBER_OF_TILE_HORIZONTAL = 32;
    public final int tileSize = 32;

    public HashMap<Direction,Tile> getSurrounding(HashMap<Position, Tile> map, Position pos) {
        HashMap<Direction, Tile> surrounding = new HashMap<>(4);
        surrounding.put(Direction.UP, map.get(new Position(pos.getX(), pos.getY() + 1)));
        surrounding.put(Direction.LEFT, map.get(new Position(pos.getX() + 1, pos.getY())));
        surrounding.put(Direction.DOWN, map.get(new Position(pos.getX(), pos.getY() - 1)));
        surrounding.put(Direction.RIGHT, map.get(new Position(pos.getX() - 1, pos.getY())));
        return surrounding;
    }

    public boolean moveValid(MovingObject object, HashMap<Enums.Direction, Tile> tileHashMap) {

        return true;
    }

    public Position screenToWorld(Position position) {
        return null;
    }

    public Position worldToScreen(Position position) {
        return null;
    }
}
