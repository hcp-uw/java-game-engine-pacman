package com.jgegroup.pacman.objects.immovable.consumables;

import com.jgegroup.pacman.objects.GameObject;
import com.jgegroup.pacman.objects.MovingObject;
import com.jgegroup.pacman.objects.Position;

public abstract class Consumable extends GameObject {
    public static final int score=Integer.MIN_VALUE;
    public Consumable(int x, int y) {
        super(x,y);
    }
    public Consumable(Position position){
        super(position);
    }

//    public Consumable(int x, int y, int score) {
//        super(x,y);
//        this.score=score;
//    }
//    public Consumable(Position position, int score){
//        super(position);
//        this.score=score;
//    }




}
