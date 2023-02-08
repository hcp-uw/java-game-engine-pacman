package com.jgegroup.pacman.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapUtils {
    public List<Tile> getSurrounding(HashMap<Position, Tile> map, Position pos) {
        ArrayList<Tile> surrounding = new ArrayList<>(4);
        surrounding.add(map.get(new Position(pos.getX(), pos.getY() + 1)));
        surrounding.add(map.get(new Position(pos.getX() + 1, pos.getY())));
        surrounding.add(map.get(new Position(pos.getX(), pos.getY() - 1)));
        surrounding.add(map.get(new Position(pos.getX() - 1, pos.getY())));
        return surrounding;
    }


}
