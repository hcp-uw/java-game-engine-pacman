package com.jgegroup.pacman.objects.characters;

import com.jgegroup.pacman.KeyHandler;
import com.jgegroup.pacman.MainScene;
import com.jgegroup.pacman.objects.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Pac extends Entity {

    MainScene pacScene;
    KeyHandler keyHandler;

    public Pac(MainScene PacScene, KeyHandler keyHandler) {
        this.pacScene = PacScene;
        this.keyHandler = keyHandler;
        setDefaultValues();
        setPacImage();
    }

    public void setDefaultValues() {
        x = 200;
        y = 200;
        speed = 1;
    }

    public void setPacImage() {
        up = new Image("characters/Pacman Up.png");
    }

    public void update() {
        switch (keyHandler.movement) {
            case 0:
                this.y += speed;
                break;
            case 1:
                this.y -= speed;
                break;
            case 2:
                this.x -= speed;
                break;
            case 3:
                this.x += speed;
                break;
            default:
                break;
        }
        System.out.println("Movement:" + keyHandler.movement);
    }

    public void redraw(GraphicsContext painter) {
//        painter.clearRect(x - 5 , y - 5, pacScene.RESOLUTION_HORIZONTAL, pacScene.RESOLUTION_VERTICAL );
//        painter.setFill(Color.WHITE);
//        painter.fillRect(x, y, 20, 20);
        painter.clearRect(x - speed, y - speed, pacScene.RESOLUTION_HORIZONTAL, pacScene.RESOLUTION_VERTICAL);
        painter.drawImage(up, x, y, 400, 100);



    }



}
