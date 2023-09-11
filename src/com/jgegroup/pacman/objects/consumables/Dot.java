package com.jgegroup.pacman.objects.consumables;

import com.jgegroup.pacman.objects.Position;

public class Dot extends Consumable {

    public Dot(Position position) {
        super(position);
        this.score = 100;
    }

    public Dot(int x, int y) {
        super(x,y);
        this.score = 100;
    }
}
