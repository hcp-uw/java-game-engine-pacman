package com.jgegroup.pacman.objects;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import com.jgegroup.pacman.objects.Enums.*;

public class Entity {
    public int x, y;
    public Direction newDirection;
    public Direction direction = Direction.STOP;
    public int speed;
    public Image[] images;

    public boolean collisionDetected;
    public Rectangle collision_range;
}
