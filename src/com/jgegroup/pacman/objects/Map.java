package com.jgegroup.pacman.objects;
import com.jgegroup.pacman.objects.immovable.consumables.Consumable;

import java.util.HashMap;
import java.util.HashSet;

public class Map {
    private static HashMap<Position, Tile> tiles;
    private static HashMap<Position, Consumable> objects;
    private static Map instance;
    private Map(/*Map Context*/){
        objects = new HashMap<>();
        tiles = new HashMap<>();
        createMap(/*Map Context*/);
    }

    public static Map getMapInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }
    public static HashMap<Position, Tile> getTiles() { return instance.tiles; };
    public static HashMap<Position, Consumable> getObjects() { return instance.objects; }
    public static void createMap(/*Map Context*/) {

    };
}
