package com.jgegroup.pacman.objects.consumables;

import com.jgegroup.pacman.objects.GameObject;
import com.jgegroup.pacman.objects.Position;

public abstract class Consumable extends GameObject {
    public int score;
    public Consumable(int x, int y) {
        this(x,y,0);
    }
    public Consumable(Position position){
        this(position,0);
    }
    public Consumable(int x, int y, int score) {
        super(x,y);
        this.score=score;
    }
    public Consumable(Position position, int score){
        super(position);
        this.score=score;
    }




}
