package com.jgegroup.pacman.objects;
public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** @@Author: Noah
     * Gets the location of the position object
     * Throws no exceptions
     * @return Position object
     * Takes in no parameters
     */
    public Position getLocation() {
        return this;
    }

    /** @@Author: Noah
     * Gets the x coordinate of the position
     * Throws no exceptions
     * @return X coordinate integer
     * Takes in no parameters
     */
    public int getX() {
        return x;
    }

    /** @@Author: Noah
     * Sets the Y coordinate of the position
     * Throws no exceptions
     * Returns nothing
     * @param x new x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /** @@Author: Noah
     * Gets the y coordinate of the position
     * Throws no exceptions
     * @return nothing
     * Takes in no parameters
     */
    public int getY() {
        return y;
    }

    /** @@Author: Noah
     * Sets the y coordinate of the position
     * Throws no errors
     * Returns noting
     * @param y new Y coordinate of the position
     */
    public void setY(int y) {
        this.y = y;
    }

    /** @@Author: Noah
     * Translates the coordinates of the position by the given amounts
     * Throws no exceptions
     * Returns nothing
     * @param dx x coordinate change
     * @param dy y coordinate change
     */
    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /** @@Author: Noah
     * Compares this position to another position
     * Throws no exceptions
     * @param other Other position to compare with
     * @return boolean related to if they equate or not
     */
    public boolean equals(Position other) {
        if (this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        return false;
    }

    /** @@Author: Noah
     * Sets both the coordinates to a new value
     * Throws no exceptions
     * Returns nothing
     * @param x new x coordinate
     * @param y new y coordinate
     */
    public void moveTo(int x, int y) {
        this.setX(x);
        this.setY(y);
    }
}
