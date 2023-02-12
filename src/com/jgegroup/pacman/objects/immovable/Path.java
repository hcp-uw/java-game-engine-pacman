package com.jgegroup.pacman.objects.immovable;

import com.jgegroup.pacman.objects.Tile;
import javafx.scene.image.Image;


public class Path extends Tile {
    public boolean isIntersection;
    public boolean isCorner;

    public Path(boolean intersection, boolean corner, Image image) {
        super(image);
        isIntersection = intersection;
        isCorner = corner;
    }

}
