package com.jgegroup.pacman.objects.characters;

import com.jgegroup.pacman.objects.Enums.*;
import com.jgegroup.pacman.objects.immovable.Tile;

import java.util.HashMap;

public interface GhostMovement {
    public void normalThink(Direction dirX, Direction dirY, int dx, int dy, HashMap<Direction, Tile> surr);
    public void spookedThink(Direction dirX, Direction dirY, int dx, int dy, HashMap<Direction, Tile> surr);
}
