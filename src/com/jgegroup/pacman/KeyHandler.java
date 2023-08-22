package com.jgegroup.pacman;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements javafx.event.EventHandler<javafx.scene.input.KeyEvent>  {
    public int movement = 4;
    @Override
    public void handle(javafx.scene.input.KeyEvent  keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        switch (keyCode) {
            case DOWN:
                this.movement = 0;
                break;
            case UP:
                this.movement = 1;
                break;
            case LEFT:
                this.movement = 2;
                break;
            case RIGHT:
                this.movement = 3;
                break;
            default:
                break;
        }
    }
}
