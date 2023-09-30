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

    public Direction chase(
            double ghost_x,
            double ghost_y,
            double pac_x,
            double pac_y,
            Set<Direction> restriction
        ) {
        int ghost_map_x = (int) Math.round(ghost_x / GameScene.TILE_SIZE);
        int ghost_map_y = (int) Math.round(ghost_y / GameScene.TILE_SIZE);

        int pac_map_x = (int) Math.ceil(pac_x / GameScene.TILE_SIZE);
        int pac_map_y = (int) Math.ceil(pac_y / GameScene.TILE_SIZE);

        Random r = new Random();
        // select if we modify x, y, or neither
        int choice = r.nextInt(81) % 3;
        if (choice == 1) {
            pac_map_x += r.nextInt(-2, 3);
        } else if (choice == 2) {
            pac_map_y += r.nextInt(-2, 3);
        }
        List<Direction> viable_moves = new ArrayList<>(priorities);
        int tile_up = map.mapArray2D[ghost_map_x][ghost_map_y - 1];
        int tile_right = map.mapArray2D[ghost_map_x + 1][ghost_map_y];
        int tile_down = map.mapArray2D[ghost_map_x][ghost_map_y + 1];
        int tile_left = map.mapArray2D[ghost_map_x - 1][ghost_map_y];
        if (map.tileType[tile_up].getCollisionOn()) {
            viable_moves.remove(Direction.UP);
        }
        if (map.tileType[tile_right].getCollisionOn()) {
            viable_moves.remove(Direction.RIGHT);
        }
        if (map.tileType[tile_down].getCollisionOn()) {
            viable_moves.remove(Direction.DOWN);
        }
        if (map.tileType[tile_left].getCollisionOn()) {
            viable_moves.remove(Direction.LEFT);
        }
        viable_moves.removeAll(restriction);

        Direction most_viable_so_far = Direction.STOP;
        int max_so_far = Integer.MIN_VALUE;
        for (Direction dir : viable_moves) {
            int dist = 0;
            if (dir.equals(Direction.UP)) {
                dist = (int) (Math.pow(pac_map_x - ghost_map_x, 2)
                        + Math.pow(pac_map_y - ghost_map_y - 1 , 2));
            } else if (dir.equals(Direction.RIGHT)) {
                dist = (int) (Math.pow(pac_map_x - ghost_map_x + 1, 2)
                        + Math.pow(pac_map_y - ghost_map_y, 2));
            } else if (dir.equals(Direction.DOWN)) {
                dist = (int) (Math.pow(pac_map_x - ghost_map_x, 2)
                        + Math.pow(pac_map_y - ghost_map_y + 1, 2));
            } else if (dir.equals(Direction.LEFT)) {
                dist = (int) (Math.pow(pac_map_x - ghost_map_x - 1, 2)
                        + Math.pow(pac_map_y - ghost_map_y, 2));
            }
            if (dist > max_so_far) {
                max_so_far = dist;
                most_viable_so_far = dir;
            }
        }
        return most_viable_so_far;
    }

    public Direction scatter(
            double ghost_x,
            double ghost_y,
            double pac_x,
            double pac_y,
            Set<Direction> restriction
    ) {
        int ghost_map_x = (int) Math.round(ghost_x / GameScene.TILE_SIZE);
        int ghost_map_y = (int) Math.round(ghost_y / GameScene.TILE_SIZE);

        int pac_map_x = (int) Math.ceil(pac_x / GameScene.TILE_SIZE);
        int pac_map_y = (int) Math.ceil(pac_y / GameScene.TILE_SIZE);

        Random r = new Random();
        // select if we modify x, y, or neither
        int choice = r.nextInt(81) % 3;
        if (choice == 1) {
            pac_map_x += r.nextInt(-2, 3);
        } else if (choice == 2) {
            pac_map_y += r.nextInt(-2, 3);
        }
        List<Direction> viable_moves = new ArrayList<>(priorities);
        int tile_up = map.mapArray2D[ghost_map_x][ghost_map_y - 1];
        int tile_right = map.mapArray2D[ghost_map_x + 1][ghost_map_y];
        int tile_down = map.mapArray2D[ghost_map_x][ghost_map_y + 1];
        int tile_left = map.mapArray2D[ghost_map_x - 1][ghost_map_y];
        if (map.tileType[tile_up].getCollisionOn()) {
            viable_moves.remove(Direction.UP);
        }
        if (map.tileType[tile_right].getCollisionOn()) {
            viable_moves.remove(Direction.RIGHT);
        }
        if (map.tileType[tile_down].getCollisionOn()) {
            viable_moves.remove(Direction.DOWN);
        }
        if (map.tileType[tile_left].getCollisionOn()) {
            viable_moves.remove(Direction.LEFT);
        }
        viable_moves.removeAll(restriction);

        Direction least_viable_so_far = Direction.STOP;
        int min_so_far = Integer.MAX_VALUE;
        for (Direction dir : viable_moves) {
            int dist = 0;
            if (dir.equals(Direction.UP)) {
                dist = (int) (Math.pow(pac_map_x - ghost_map_x, 2)
                        + Math.pow(pac_map_y - ghost_map_y - 1 , 2));
            } else if (dir.equals(Direction.RIGHT)) {
                dist = (int) (Math.pow(pac_map_x - ghost_map_x + 1, 2)
                        + Math.pow(pac_map_y - ghost_map_y, 2));
            } else if (dir.equals(Direction.DOWN)) {
                dist = (int) (Math.pow(pac_map_x - ghost_map_x, 2)
                        + Math.pow(pac_map_y - ghost_map_y + 1, 2));
            } else if (dir.equals(Direction.LEFT)) {
                dist = (int) (Math.pow(pac_map_x - ghost_map_x - 1, 2)
                        + Math.pow(pac_map_y - ghost_map_y, 2));
            }
            if (dist <= min_so_far) {
                min_so_far = dist;
                least_viable_so_far = dir;
            }
        }
        return least_viable_so_far;
    }
}
