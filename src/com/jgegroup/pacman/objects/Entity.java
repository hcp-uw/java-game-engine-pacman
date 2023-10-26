package com.jgegroup.pacman.objects;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import com.jgegroup.pacman.objects.Enums.*;
// Entity
public class Entity {
    public int x, y;
    public Direction newDirection;
    public Direction direction = Direction.STOP;
    public int speed;
    public Image pacZero,  up1, up2, down1, down2, left1, left2, right1, right2, blue1, blue2, white1, white2;

    public int spriteNumber;
    public int spriteCounter;

    public Image spriteImage;


    public boolean collisionDetected;
    public Rectangle collision_range;

}
