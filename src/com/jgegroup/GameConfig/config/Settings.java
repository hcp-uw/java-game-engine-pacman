package com.jgegroup.GameConfig.config;

import javafx.scene.image.Image;

public class Settings {
    private Integer pacmanLives = null;
    private Integer pacmanSpeed = null;
    private Integer ghostSpeed = null;
    private Image floorImage = null;
    private Image wallImage = null;

    private String mapPath = null;
    private Integer mapWidth = null;
    private Integer mapHeight = null;

    public boolean selectedMapSize() {
        return mapHeight != null && mapWidth != null;
    }

    public Integer getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(Integer mapWidth) {
        this.mapWidth = mapWidth;
    }

    public Integer getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(Integer mapHeight) {
        this.mapHeight = mapHeight;
    }

    public String getMapPath() {
        return mapPath;
    }

    public void setMapPath(String mapPath) {
        this.mapPath = mapPath;
    }

    public boolean selectedMap() {
        return mapPath != null;
    }

    public boolean selectedLives() {
        return pacmanLives != null;
    }

    public boolean selectedPacmanSpeed() {
        return pacmanSpeed != null;
    }

    public boolean selectedGhostSpeed() {
        return ghostSpeed != null;
    }

    public boolean selectedFloorImage() {
        return floorImage != null;
    }

    public boolean selectedWallImage() {
        return wallImage != null;
    }

    public Image getFloorImage() {
        return floorImage;
    }

    public void setFloorImage(Image floorImage) {
        this.floorImage = floorImage;
    }

    public Image getWallImage() {
        return wallImage;
    }

    public void setWallImage(Image wallImage) {
        this.wallImage = wallImage;
    }

    public int getPacmanLives() {
        return pacmanLives;
    }

    public void setPacmanLives(int pacmanLives) {
        this.pacmanLives = pacmanLives;
    }

    public int getPacmanSpeed() {
        return pacmanSpeed;
    }

    public void setPacmanSpeed(int pacmanSpeed) {
        this.pacmanSpeed = pacmanSpeed;
    }

    public int getGhostSpeed() {
        return ghostSpeed;
    }

    public void setGhostSpeed(int ghostSpeed) {
        this.ghostSpeed = ghostSpeed;
    }
}
