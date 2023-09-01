package com.jgegroup.pacman.objects.immovable;

import javafx.scene.image.Image;

public class Tile {
    private Image image;

    private boolean collisionOn = false; // false mean it object can be walk through.
    public Tile(Image image) {
        this.image = image;
    }

    public Image getImage(){
      return this.image;
    }

    public void setCollisionOn(boolean canCollide) {
         this.collisionOn = canCollide;
    }
    public boolean getCollisionOn() {
        return collisionOn;
    }
}
