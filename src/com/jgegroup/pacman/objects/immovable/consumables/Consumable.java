package com.jgegroup.pacman.objects.immovable.consumables;

import com.jgegroup.pacman.objects.GameObject;
import com.jgegroup.pacman.objects.Position;

public abstract class Consumable extends GameObject {
    int score;
    public Consumable(int x, int y) {
        super(x,y);
    }
    public Consumable(Position position) {
        super(position);
    }

    public int getScore() { return this.score; }
}
