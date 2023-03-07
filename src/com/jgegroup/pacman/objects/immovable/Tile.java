package com.jgegroup.pacman.objects.immovable;

import javafx.scene.image.Image;

public class Tile {
    private Image image;
    public Tile(Image image) {
        this.image = image;
    }

    public Image getImage(){
      return this.image;
    }
}
