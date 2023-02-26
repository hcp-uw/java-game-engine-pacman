package com.jgegroup.pacman.objects.immovable.consumables;

import com.jgegroup.pacman.objects.Position;

public class BigDot extends Consumable {

    public BigDot(Position position) {
        super(position);
        this.score = 200;
    }
    public BigDot(int x, int y) {
        super(x,y);
        this.score = 200;
    }

}
