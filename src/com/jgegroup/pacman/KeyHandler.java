package com.jgegroup.pacman;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements javafx.event.EventHandler<javafx.scene.input.KeyEvent>  {
    public String movement = "not moving";
    @Override
    public void handle(javafx.scene.input.KeyEvent  keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        switch (keyCode) {
            case UP:
                this.movement = "up";
                break;
            case DOWN:
                this.movement = "down";
                break;
            case LEFT:
                this.movement = "left";
                break;
            case RIGHT:
                this.movement = "right";
                break;
            default:
                break;
        }
    }
}
