package com.jgegroup.pacman.objects.immovable.consumables;

import com.jgegroup.pacman.objects.GameObject;
import com.jgegroup.pacman.objects.MovingObject;
import com.jgegroup.pacman.objects.Position;

public class BigDot extends Consumable {

    public static final int SCORE = 200;
    public BigDot(Position position) {
        super(position);
    }
    public BigDot(int x, int y) {
        super(x, y);
    }

}
