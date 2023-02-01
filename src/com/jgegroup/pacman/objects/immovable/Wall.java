package com.jgegroup.pacman.objects.immovable;

import com.jgegroup.pacman.objects.GameObject;
import com.jgegroup.pacman.objects.Ghost;
import com.jgegroup.pacman.objects.Pacman;
import com.jgegroup.pacman.objects.Position;

public class Wall extends GameObject {

    public Wall(int x, int y) {
        super(x, y);
    }

    public Wall(Position position) {
        super(position);
    }
    @Override
    protected int collisionHandle(GameObject object) {
        if (object instanceof Pacman || object instanceof Ghost) {
            return 1;
        }
        return 0;
    }
}
