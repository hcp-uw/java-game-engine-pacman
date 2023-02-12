package com.jgegroup.pacman.objects;

import javafx.scene.image.Image;

public abstract class Tile {
    private Image image;
    public Tile(Image image) {
        this.image = image;
    }
}
