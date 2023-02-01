package com.jgegroup.pacman.objects.consumables;

import com.jgegroup.pacman.objects.GameObject;
import com.jgegroup.pacman.objects.Position;

public class Dot extends Consumable {

    public Dot(Position position) {
        super(position, 100);

    }

    public Dot(int x, int y) {
        super(x,y, 100);
    }
    @Override
    protected int collisionHandle(GameObject object) {
        return 0;
    }
}
