package com.jgegroup.pacman.objects.characters;

import com.jgegroup.pacman.KeyHandler;
import com.jgegroup.pacman.MainScene;
import com.jgegroup.pacman.objects.Entity;
import com.jgegroup.pacman.objects.Enums.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pac extends Entity {

    MainScene mainScene;
    KeyHandler keyHandler;

    public Pac(MainScene mainScene, KeyHandler keyHandler) {
        this.mainScene = mainScene;
        this.keyHandler = keyHandler;
        setDefaultValues();
        setPacImage();
    }

    public void setDefaultValues() {
        x = 300;
        y = 200;
        collision_range = new Rectangle(0, 0, 25, 25);

        speed = 1;
        direction = Direction.STOP;
    }

    public void setPacImage() {
//        up = new Image("characters/Pacman Up.png");
    }

    public void update() {
        switch (keyHandler.movement) {
            case "up":
                direction = Direction.UP;
                break;
            case "down":
                direction = Direction.DOWN;
                break;
            case "left":
                direction = Direction.LEFT;
                break;
            case "right":
                direction = Direction.RIGHT;
                break;
            default:
                break;
        }
        collisionDetected = false;
        mainScene.collisionChecker.checkTile(this);

        if (collisionDetected == false) {
            switch (keyHandler.movement) {
                case "up":
                    this.y -= speed;
                    break;
                case "down":
                    this.y += speed;
                    break;
                case "left":
                    this.x -= speed;
                    break;
                case "right":
                    this.x += speed;
                    break;
                default:
                    break;
            }
        }
        System.out.println("Entity collisionDetected is " + collisionDetected);
    }

    public void redraw(GraphicsContext painter) {
        painter.clearRect(x - 5 , y - 5, mainScene.RESOLUTION_HORIZONTAL, mainScene.RESOLUTION_VERTICAL );
        painter.setFill(Color.WHITE);
        painter.fillRect(x, y, mainScene.TILE_SIZE, mainScene.TILE_SIZE);
//        painter.clearRect(x - speed, y - speed, mainScene.RESOLUTION_HORIZONTAL, mainScene.RESOLUTION_VERTICAL);
//        painter.drawImage(up, x, y, 400, 100);



    }



}