package com.jgegroup.pacman.objects;

public class Enums {
    /** @Author: Jesse
     *  Enum for direction input
     *  Contains directions
     */
    public enum Direction {
        UP, DOWN, LEFT, RIGHT, STOP
    }

    /** @Author: Noah
     *  Enum for updates
     *  Contains update types
     */
    public enum Update {
        DEATH, MOVED, SUPER, SCARED, EATEN, RESPAWN
    }
}
