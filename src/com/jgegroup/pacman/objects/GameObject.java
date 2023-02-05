package com.jgegroup.pacman.objects;

import com.jgegroup.pacman.SimpleMath;

public abstract class GameObject {

    protected Position position;
    public GameObject(int x, int y) {
        this.position = new Position(x, y);
    }
    public GameObject(Position position) {
        this.position = position;
    }

    public Position getPosition(){return this.position;}
}
