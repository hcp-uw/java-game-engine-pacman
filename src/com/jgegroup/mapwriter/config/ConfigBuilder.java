package com.jgegroup.mapwriter.config;

public class ConfigBuilder {

    private String[] resources;
    private int xBound;
    private int yBound;
    private String path;
    private int tileSize;


    public ConfigBuilder() {}

    public void setResources(String... resources) {
        this.resources = resources;
    }

    public void setxBound(int x) {
        xBound = x;
    }

    public void setyBound(int y) {
        yBound = y;
    }

    public void setPathToResources(String path) {
        this.path = path;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }
    public Config build() {
        return new Config(resources, xBound, yBound, tileSize, path);
    }
}
