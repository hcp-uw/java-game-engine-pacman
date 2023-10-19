package com.jgegroup.pacman.objects.characters;

import com.jgegroup.pacman.GameScene;
import com.jgegroup.pacman.PathFinder;
import com.jgegroup.pacman.objects.Enums.*;
import com.jgegroup.pacman.objects.Map;
import javafx.scene.paint.Color;

import java.util.Random;
import java.util.Set;

public class GhostMovement {
    private Color color;
    private Pac pac;
    private Ghost ghost;
    private Random random;
    private PathFinder pf;
    public GhostMovement(Color color, Pac pac, Ghost ghost) {
        this.color = color;
        this.pac = pac;
        pf = new PathFinder(Map.getMapInstance(), pac);
        this.ghost = ghost;
    }

    public Direction chase(Set<Direction> restrictions) {
        if (color.equals(Color.RED)) {
            return pf.redChase(ghost.x, ghost.y, pac.x, pac.y, restrictions);
        } else if (color.equals(Color.BLUE)) {
            return pf.blueChase(ghost.x, ghost.y, pac.x, pac.y, restrictions);
        } else if (color.equals(Color.PINK)) {
            return pf.pinkChase(ghost.x, ghost.y, pac.x, pac.y, restrictions);
        } else if (color.equals(Color.YELLOW)) {
            return pf.yellowChase(ghost.x, ghost.y, pac.x, pac.y, restrictions);
        }
        return Direction.STOP;
    }

    public Direction scatter(Set<Direction> restrictions) {
        if (color.equals(Color.RED)) {
            return pf.scatter(ghost.x, ghost.y, 0, 0, restrictions);
        } else if (color.equals(Color.BLUE)) {
            return pf.scatter(ghost.x, ghost.y, GameScene.RESOLUTION_HORIZONTAL, 0, restrictions);
        } else if (color.equals(Color.YELLOW)) {
            return pf.scatter(ghost.x, ghost.y, 0, GameScene.RESOLUTION_VERTICAL, restrictions);
        } else if (color.equals(Color.PINK)) {
            return pf.scatter(ghost.x, ghost.y, GameScene.RESOLUTION_HORIZONTAL, GameScene.RESOLUTION_VERTICAL, restrictions);
        }
        return Direction.STOP;
    }

    public Direction spooked(Set<Direction> restrictions) {
        return pf.spook(ghost.x, ghost.y, pac.x, pac.y, restrictions);
    }

    public Direction spawn(Set<Direction> restrictions) {
        return pf.scatter(ghost.x, ghost.y, 384, 340, restrictions);
    }
}
