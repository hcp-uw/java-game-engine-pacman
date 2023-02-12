package com.jgegroup.pacman.objects;
import java.util.HashMap;
import java.util.HashSet;

public class Map {
    private static HashMap<Position, Tile> objects;
    private static Map instance;
    private Map(/*Map Context*/){
        objects = new HashMap<>();
        /* Todo: Add a method that reads data from the map file and places the components from the data file
             and then loads the components into the hashmap.
        */
        createMap(/*Map Context*/);
    }

    public static Map getMapInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }
    public static HashMap<Position, Tile> getMap() { return instance.objects; };
    public static void createMap(/*Map Context*/) {

    };
}
