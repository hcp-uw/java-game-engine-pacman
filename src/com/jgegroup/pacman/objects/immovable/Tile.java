package com.jgegroup.pacman.objects.immovable;

import javafx.scene.image.Image;

public class Tile {
    private Image image;

    private boolean collision = false; // false mean it object can be walk through.
    public Tile(Image image) {
        this.image = image;
    }

    public Image getImage(){
      return this.image;
    }

    public void setCollision(boolean canCollide) {
         this.collision = canCollide;
    }
    public boolean getCollision() {
        return collision;
    }
}
