package com.jgegroup.pacman;

import com.jgegroup.pacman.objects.Position;

public class SimpleMath {
    /** @@Author: Noah
     * Gets the distance from one position to another position
     * Throws no exceptions
     * @return distance between the two points
     * @param p1 First Position
     * @param p2 Second Position
     */
    public static double getDistance(Position p1, Position p2){
        double _dx = p1.getX() - p2.getX();
        double _dy = p1.getY() - p2.getY();
        return Math.sqrt(Math.pow(_dx,2) + Math.pow(_dy,2));
    }
}
