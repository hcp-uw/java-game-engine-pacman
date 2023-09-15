package com.jgegroup.pacman;

import com.jgegroup.pacman.objects.Enums.Direction;
import com.jgegroup.pacman.objects.Map;

import java.util.*;

public class PathFinder {

    private Map map;
    private List<Direction> priorities = List.of(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT);
    public PathFinder(Map map) {
        this.map = map;
    }

    public Direction chase(double ghost_x, double ghost_y, double pac_x, double pac_y) {
        int ghost_map_x = (int) Math.ceil(ghost_x / MainScene.TILE_SIZE);
        int ghost_map_y = (int) Math.ceil(ghost_y / MainScene.TILE_SIZE);

        int pac_map_x = (int) Math.ceil(pac_x / MainScene.TILE_SIZE);
        int pac_map_y = (int) Math.ceil(pac_y / MainScene.TILE_SIZE);

        Random r = new Random();
        // select if we modify x, y, or neither
        int choice = r.nextInt(81) % 3;
        if (choice == 1) {
            pac_map_x += r.nextInt(-2, 3);
        } else if (choice == 2) {
            pac_map_y += r.nextInt(-2, 3);
        }

        List<Direction> viable_moves = new ArrayList<>();
        int tile_up = map.mapArray2D[ghost_map_x][ghost_map_y + 1];
        int tile_right = map.mapArray2D[ghost_map_x + 1][ghost_map_y];
        int tile_down = map.mapArray2D[ghost_map_x][ghost_map_y - 1];
        int tile_left = map.mapArray2D[ghost_map_x - 1][ghost_map_y];
        if (!map.tileType[tile_up].getCollisionOn()) {
            viable_moves.add(Direction.UP);
        }
        if (!map.tileType[tile_right].getCollisionOn()) {
            viable_moves.add(Direction.RIGHT);
        }
        if (!map.tileType[tile_down].getCollisionOn()) {
            viable_moves.add(Direction.DOWN);
        }
        if (!map.tileType[tile_left].getCollisionOn()) {
            viable_moves.add(Direction.LEFT);
        }
        Direction most_viable_so_far = Direction.STOP;
        int max_so_far = Integer.MIN_VALUE;
        for (Direction dir : viable_moves) {
            if (dir.equals(Direction.UP)) {
                int dist = (int) (Math.pow(pac_map_x - ghost_map_x, 2)
                        + Math.pow(pac_map_y - ghost_map_y - 1, 2));
                if (dist > max_so_far) {
                    max_so_far = dist;
                    most_viable_so_far = dir;
                }
            } else if (dir.equals(Direction.RIGHT)) {
                int dist = (int) (Math.pow(pac_map_x - ghost_map_x + 1, 2)
                        + Math.pow(pac_map_y - ghost_map_y, 2));
                if (dist > max_so_far) {
                    max_so_far = dist;
                    most_viable_so_far = dir;
                }
            } else if (dir.equals(Direction.DOWN)) {
                int dist = (int) (Math.pow(pac_map_x - ghost_map_x, 2)
                        + Math.pow(pac_map_y - ghost_map_y + 1, 2));
                if (dist > max_so_far) {
                    max_so_far = dist;
                    most_viable_so_far = dir;
                }
            } else if (dir.equals(Direction.LEFT)) {
                int dist = (int) (Math.pow(pac_map_x - ghost_map_x - 1, 2)
                        + Math.pow(pac_map_y - ghost_map_y, 2));
                if (dist > max_so_far) {
                    max_so_far = dist;
                    most_viable_so_far = dir;
                }
            }
        }
        return most_viable_so_far;
    }
}
