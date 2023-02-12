package com.jgegroup.pacman.objects.immovable.consumables;

import com.jgegroup.pacman.objects.MovingObject;
import com.jgegroup.pacman.objects.Position;

public class Dot extends Consumable {

    public static final int SCORE = 100;
    public Dot(Position position) {
        super(position);

    }

    public Dot(int x, int y) {
        super(x,y);
    }
}
