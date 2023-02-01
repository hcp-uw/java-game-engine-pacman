package com.jgegroup.pacman.objects.consumables;

import com.jgegroup.pacman.objects.GameObject;
import com.jgegroup.pacman.objects.Position;

public class BigDot extends Consumable {

    public BigDot(Position position) {
        super(position, 200);
    }

    public BigDot(int x, int y) {
        super(x,y,200);
    }
    @Override
    protected int collisionHandle(GameObject object) {
        return 0;
    }

}
