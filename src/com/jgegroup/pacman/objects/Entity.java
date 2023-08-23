package com.jgegroup.pacman.objects;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Entity {
    public int x, y;
    public String direction = "not moving";
    public int speed;
    public Image up, down, left, right;

    public boolean collisionDetected;
    public Rectangle collision_range;
}
