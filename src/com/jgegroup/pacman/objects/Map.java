package com.jgegroup.pacman.objects;
import java.util.HashMap;

public class Map {
    private static HashMap<Position, GameObjects> objects;
    private static Map instance;
    private Map(/*Map Context*/){
        objects = new HashMap<>();
        /* Todo: Add a method that reads data from the map file and places the componenets from the data file
             and then loads the components into the hashmap.
        */
    }

    public static Map getMapInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }
    public static HashMap<Position, GameObjects> getMap() { return instance.objects; };
    public static void createMap(/*Map Context*/) {

    };
}
