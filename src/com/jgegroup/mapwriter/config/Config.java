package com.jgegroup.mapwriter.config;

public class Config {
    // Items wanting to use
    private final String[] resources;
    // desired width of map
    private final int xBound;
    // desired height of map
    private final int yBound;
    // tile size width and height
    private final int tileSize;
    // path to resources folder
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
