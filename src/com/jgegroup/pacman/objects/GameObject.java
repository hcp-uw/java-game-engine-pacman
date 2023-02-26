package com.jgegroup.pacman.objects;

public abstract class GameObject {

    protected Position position;
    public GameObject(int x, int y) {
        this.position = new Position(x, y);
    }
    public GameObject(Position position) {
        this.position = position;
    }

    /** Author: Jesse
     * Gets the position of the object
     * Throws no exceptions
     * @return Objects positions
     */
    public Position getPosition() { return this.position; }
}
