package com.jgegroup.pacman.objects.characters;

import com.jgegroup.pacman.objects.Enums.*;
import com.jgegroup.pacman.objects.immovable.Path;
import com.jgegroup.pacman.objects.immovable.Tile;
import com.jgegroup.pacman.objects.immovable.Wall;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlueTest {
    Direction[] directions = {Direction.DOWN, Direction.LEFT,
                            Direction.UP, Direction.RIGHT};
    @Test
    void normalThink() {
        Blue blue = new Blue(0, 0, 10);
        blue.setDirection(Direction.STOP);
        List<HashMap<Direction, Tile>> surrs = new ArrayList<>(5);
        for (int i = 0 ; i < 5; i++) {
            HashMap<Direction, Tile> surr = new HashMap<>();
            for (Direction dir : directions) {
                double r = Math.random();
                if (r >= 0.20) {
                    surr.put(dir, new Path(new Image("tiles/floor.png")));
                } else {
                    surr.put(dir, new Wall(new Image("tiles/wall.png")));
                }
            }
        }

    }

    @Test
    void spookedThink() {
        Blue blue = new Blue(0, 0, 10);
    }
}