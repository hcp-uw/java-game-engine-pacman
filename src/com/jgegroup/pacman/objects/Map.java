package com.jgegroup.pacman.objects;
import com.jgegroup.pacman.objects.immovable.Tile;
import com.jgegroup.pacman.objects.immovable.consumables.Consumable;

import java.util.HashMap;

public class Map {
    private static HashMap<Position, Tile> tiles;
    private static HashMap<Position, Consumable> objects;
    private static Map instance;
    private Map(/*Map Context*/){
        objects = new HashMap<>();
        tiles = new HashMap<>();
        createMap(/*Map Context*/);
    }

    /** @@Author: Noah
     * Creates a map instance if its not already created
     * Throws no exceptions
     * @return Map singleton
     * Takes no parameters
     */
    public static Map getMapInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }

    /** @@Author: Noah
     * Gets the tiles of the map from the object
     * Throws no exceptions
     * @return map instance tiles
     * Takes in nothing
     */
    public static HashMap<Position, Tile> getTiles() { return instance.tiles; }

    /** @@Author: Noah
     * Gets the objects that are on the map
     * Throws no exceptions
     * @return map instance objects
     * Takes in nothing
     */
    public static HashMap<Position, Consumable> getObjects() { return instance.objects; }
    public static void createMap(/*Map Context*/) {

    };
}
