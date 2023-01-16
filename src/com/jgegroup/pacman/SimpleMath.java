package jgegroup.pacman;

import jgegroup.pacman.objects.Position;

public class SimpleMath {
    public static double getDistance(Position p1, Position p2){
        double _dx=p1.getX()- p2.getX();
        double _dy=p1.getY()-p2.getY();
        return Math.sqrt(Math.pow(_dx,2)+Math.pow(_dy,2));
    }
}
