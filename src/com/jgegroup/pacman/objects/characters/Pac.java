package com.jgegroup.pacman.objects.characters;

import com.jgegroup.pacman.KeyHandler;
import com.jgegroup.pacman.MainScene;
import com.jgegroup.pacman.objects.Entity;
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
        collision_range = new Rectangle(0, 0, 10, 10);

        speed = 1;
        direction = "not moving";
    }

    public void setPacImage() {
        up = new Image("characters/Pacman Up.png");
    }

    public void update() {
        switch (keyHandler.movement) {
            case "up":
                direction = "up";
                break;
            case "down":
                direction = "down";
                break;
            case "left":
                direction = "left";
                break;
            case "right":
                direction = "right";
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
        painter.fillRect(x, y, 20, 20);
//        painter.clearRect(x - speed, y - speed, mainScene.RESOLUTION_HORIZONTAL, mainScene.RESOLUTION_VERTICAL);
//        painter.drawImage(up, x, y, 400, 100);



    }



}
