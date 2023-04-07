package com.jgegroup.mapwriter.config;

public class Config {
    private final String[] resources;
    private final int xBound;
    private final int yBound;
    private final int tileSize;
    private final String path;

    protected Config(String[] resources, int x, int y, int tileSize, String path) {
        this.resources = resources;
        this.path = path;
        this.tileSize = tileSize;
        xBound = x;
        yBound = y;
    }

    public String[] getResources() {
        return resources;
    }

    public int getxBound() {
        return xBound;
    }

    public int getyBound() {
        return yBound;
    }

    public int getTileSize() {
        return tileSize;
    }

    public String getPath() {
        return path;
    }
}
