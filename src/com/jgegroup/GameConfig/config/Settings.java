package com.jgegroup.GameConfig.config;

public class Settings {
    private int pacmanLives;
    private int pacmanSpeed;
    private int ghostSpeed;

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
