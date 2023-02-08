package com.jgegroup.pacman.objects.immovable;

import com.jgegroup.pacman.objects.Tile;
import com.jgegroup.pacman.objects.immovable.consumables.*;
import javafx.scene.image.Image;

import java.util.HashSet;
import java.util.List;

public class Path extends Tile {
    public boolean isIntersection;
    public boolean isCorner;
    private HashSet<Consumable> objects;

    public Path(boolean intersection, boolean corner, List<Consumable> consumables, Image image) {
        super(image);
        isIntersection = intersection;
        isCorner = corner;
        objects = new HashSet<>(consumables);
    }

    public HashSet<Consumable> getObjects() {
        return this.objects;
    }
}
