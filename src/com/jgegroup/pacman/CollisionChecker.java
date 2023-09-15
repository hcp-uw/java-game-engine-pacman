package com.jgegroup.pacman;

import com.jgegroup.pacman.objects.Entity;
import com.jgegroup.pacman.objects.Enums.*;
import com.jgegroup.pacman.objects.Map;

public class CollisionChecker {
  private Map map;

  public CollisionChecker(Map map) {
    this.map = map;
  }

  public boolean isValidDirection(Entity entity, Direction direction) {
    int entity_left_worldX = entity.x + (int) entity.collision_range.getX();
    int entity_right_worldX = entity.x + (int) entity.collision_range.getX() + (int) entity.collision_range.getWidth();
    int entity_top_worldY = entity.y + (int) entity.collision_range.getY();
    int entity_bottom_worldY = entity.y + (int) entity.collision_range.getY() + (int) entity.collision_range.getHeight();

    int entity_left_col = entity_left_worldX / MainScene.TILE_SIZE;
    int entity_right_col = entity_right_worldX / MainScene.TILE_SIZE;
    int entity_top_row = entity_top_worldY / MainScene.TILE_SIZE;
    int entity_bottom_row = entity_bottom_worldY / MainScene.TILE_SIZE;

    int tileNum1, tileNum2;

    switch (direction) {
      case UP:
        entity_top_row = (entity_top_worldY - entity.speed) / MainScene.TILE_SIZE;
        tileNum1 = map.mapArray2D[entity_left_col][entity_top_row];
        tileNum2 = map.mapArray2D[entity_right_col][entity_top_row];
        if (map.tileType[tileNum1].getCollisionOn() || map.tileType[tileNum2].getCollisionOn()) {
          return true;
        }
        break;
      case DOWN:
        entity_bottom_row = (entity_bottom_worldY + entity.speed) / MainScene.TILE_SIZE;
        tileNum1 = map.mapArray2D[entity_left_col][entity_bottom_row];
        tileNum2 = map.mapArray2D[entity_right_col][entity_bottom_row];
        if (map.tileType[tileNum1].getCollisionOn() || map.tileType[tileNum2].getCollisionOn()) {
          return true;
        }
        break;
      case LEFT:
        entity_left_col = (entity_left_worldX - entity.speed) / MainScene.TILE_SIZE;
        tileNum1 = map.mapArray2D[entity_left_col][entity_top_row];
        tileNum2 = map.mapArray2D[entity_left_col][entity_bottom_row];
        if (map.tileType[tileNum1].getCollisionOn() || map.tileType[tileNum2].getCollisionOn()) {
          return true;
        }
        break;
      case RIGHT:
        entity_right_col = (entity_right_worldX + entity.speed) / MainScene.TILE_SIZE;
        tileNum1 = map.mapArray2D[entity_right_col][entity_top_row];
        tileNum2 = map.mapArray2D[entity_right_col][entity_bottom_row];
        if (map.tileType[tileNum1].getCollisionOn() || map.tileType[tileNum2].getCollisionOn()) {
          return true;
        }
        break;
      default:
        break;
    }
    return false;
  }
}
