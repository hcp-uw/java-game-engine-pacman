package com.jgegroup.pacman;

import com.jgegroup.pacman.objects.Enums.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements javafx.event.EventHandler<javafx.scene.input.KeyEvent>  {
    public Direction movement = Direction.STOP;
    @Override
    public void handle(javafx.scene.input.KeyEvent  keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        switch (keyCode) {
            case UP:
                this.movement = Direction.UP;
                break;
            case DOWN:
                this.movement = Direction.DOWN;
                break;
            case LEFT:
                this.movement = Direction.LEFT;
                break;
            case RIGHT:
                this.movement = Direction.RIGHT;
                break;
            default:
                break;
        }
    }
}
