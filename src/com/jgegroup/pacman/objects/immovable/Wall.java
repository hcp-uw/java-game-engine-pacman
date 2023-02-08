package com.jgegroup.pacman.objects.immovable;

import com.jgegroup.pacman.GamePanel;
import com.jgegroup.pacman.objects.*;
import javafx.scene.image.Image;

public class Wall extends Tile {

    byte adjacentPaths;
    public Wall(Image image) {
        super(image);
        this.adjacentPaths = 0;
    }

}
