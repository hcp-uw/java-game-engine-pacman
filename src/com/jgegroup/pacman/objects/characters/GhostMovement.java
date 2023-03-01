package com.jgegroup.pacman.objects.characters;

import com.jgegroup.pacman.objects.Enums.*;
import com.jgegroup.pacman.objects.immovable.Tile;

import java.util.HashMap;

public interface GhostMovement {
    void normalThink(int dx, int dy, HashMap<Direction, Tile> surr);
    void spookedThink(int dx, int dy, HashMap<Direction, Tile> surr);
}
