package com.jgegroup.GameConfig.config;

import javafx.scene.image.Image;

public class Settings {
    private Integer pacmanLives = null;
    private Integer pacmanSpeed = null;
    private Integer ghostSpeed = null;
    private Image floorImage = null;
    private Image wallImage = null;

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
