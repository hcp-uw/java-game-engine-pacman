package com.jgegroup.pacman.objects;


import com.jgegroup.pacman.objects.immovable.Wall;
import javafx.scene.paint.Color;
import com.jgegroup.pacman.objects.Enums.*;

import java.util.HashMap;


// Note: Death and respawning will be handled by the game cycle by the reassignment of the ghost to
// another ghost.

public class Ghost extends MovingObject {
    // Spook length is the cycle length interval for how long a ghost will
    // be spooked. Can exceed this length if more big dots are eaten
    public final int spookLength;
    // Container for base color of the Ghost which is the color that the Ghost is when not spooked
    private Color base_color;
    // Container for the color of the Ghost that we're currently showing to the screen
    private Color current_color;
    // State container for the spook timer
    private int spookState;


    public Ghost(int x, int y, int spookLength, Color color) {
        super(x, y);
        this.spookLength = spookLength;
        this.base_color = color;
        this.current_color = color;
        // this indicates that it is not spooked, -1 <- no spook. > 0 <- yes spook
        this.spookState = -1;
        this.direction = Direction.STOP;
    }

    // Authors: Noah / Nicola
    // Sets spookState to maximum spook state 'spookLength'
    // spook length represents the number of cycles to be spooked
    // Throws no exceptions
    // Returns nothing
    // Takes no parameters
    public void setSpooked() { spookState += spookLength; }

    // Authors: Noah / Nicola
    // Updatess the spook state, if the state is even it will change the color of the ghost
    // to white, and if its odd then it will change to blue, if not spooked it will return to original color
    // Throws no exceptions
    // Returns true if is currently spooked or if its coming out of being spooked
    // Takes no parameters
    public boolean updateSpooked() {
        if (spookState >= 0) {
            if (spookState % 2 == 0) {
                this.current_color = Color.WHITE;
            } else {
                this.current_color = Color.BLUE;
            }
            spookState--;
            if (spookState < 0) {
                this.current_color = base_color;
            }
            return true;
        }
        return false;
    }

    // Author: Noah
    // Checks to see if the ghost is spooked
    // Throws no exceptions
    // Returns true if spookState container is greater than or equal to 0, else false
    // Takes in no paramters
    public boolean isSpooked() { return spookState >= 0; }

    // Authors: Jesse / Noah / Davin
    // Determines if the object has collided with something or not
    // Throws, no exceptions
    // Returns 1 if collided with Pacman or Wall, 0 otherwise
    // Takes in a GameObject as a parameter
    @Override
    protected int collisionHandle(MovingObject object) {
//        if (object instanceof Pacman) {
//            return 1;
//        } else if (object instanceof Wall) {
//            return 2;
//        }

        // here we will need to look for the overlap in the MovingObject
        // we will try to see if the circles overlap

        // HOW WE WILL DO IT:
        // WE will need to get the centers of each of the circles.
        // We will need to check in all 4 directions, and we will need
        // check to see if there is any overlap in the four directions

        Position otherPosition = object.position;

        Position currentPosition = this.position;

        // now we will need to check
        // all of the possible cases
        int currentX = position.getX();
        int currentY = position.getY();

        int currentRadius = object.getRadius();

        int otherX = otherPosition.getX();
        int otherY = otherPosition.getY();

        int otherRadius = this.getRadius();

        // you will check for overlap in the X direction
        if (currentX == otherX) {
            if (currentY > otherY) {
                if (currentY - currentRadius > otherY + otherRadius) {
                    return 0;
                } else {
                    return 1;
                }
            } else if (currentY < otherY) {
                if (currentY + currentRadius < otherY - otherRadius) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }

        // this is where you check for overlap in the Y direction
        if (currentY == otherY) {
            if (currentX > otherX) {
                if (currentX - currentRadius > otherX + otherRadius) {
                    return 0;
                } else {
                    return 1;
                }
            } else if (currentX < otherX) {
                if (currentX + currentRadius < otherX - otherRadius) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }

        // this is when you do not have any overlap that could be possible in any direction
        return 0;
    }

    public Color getColor() {
        return this.base_color;
    }

    @Override
    protected void think(Direction dirX, Direction dirY, int dx, int dy, HashMap<Direction, Tile> surr) {
        System.out.println("Something went wrong, in " +
                "the Ghost main class think method");
    }

    public void thinkPrep(HashMap<Position, Tile> map, Position pacPos) {
        Position pos = this.getPosition();
        int dx = pacPos.getX() - pos.getX();
        int dy = pacPos.getY() - pos.getY();
        Direction dirX, dirY = Direction.STOP;
        dirX = dx < 0 ? Direction.LEFT : dx > 0 ? Direction.RIGHT : Direction.STOP;
        dirY = dy < 0 ? Direction.DOWN : dy > 0 ? Direction.UP : Direction.STOP;
        HashMap<Direction, Tile> surr = MapUtils.getSurrounding(map, pos);
        dirX = surr.get(dirX) instanceof Wall ? Direction.STOP : dirX;
        dirY = surr.get(dirY) instanceof Wall ? Direction.STOP : dirY;
        this.think(dirX, dirY, dx, dy, surr);
    }

    public static class Red extends Ghost {
        public Red(int x, int y, int spookLength, Color color) {
            super(x, y, spookLength, color);
        }

        public void think(Direction dirX, Direction dirY, int dx, int dy, HashMap<Direction, Tile> surr) {
            dirX = (surr.get(dirX) instanceof Wall) ? Direction.STOP : dirX;
            dirY = (surr.get(dirY) instanceof Wall) ? Direction.STOP : dirY;
            if (dirY != Direction.STOP && dirX != Direction.STOP) {
                this.direction = Math.abs(dx) <= Math.abs(dy) && dx != 0 ? dirX : dirY;
            } else if (dirX == Direction.STOP) {
                this.direction = dirY;
            } else {
                this.direction = dirX;
            }
        }
    }

    public static class Blue extends Ghost {

        public Blue(int x, int y, int spookLength, Color color) {
            super(x, y, spookLength, color);
        }

        public void think(Direction dirX, Direction dirY, int dx, int dy, HashMap<Direction, Tile> surr) {
            dirX = (surr.get(dirX) instanceof Wall) ? Direction.STOP : dirX;
            dirY = (surr.get(dirY) instanceof Wall) ? Direction.STOP : dirY;
            if (dirY != Direction.STOP && dirX != Direction.STOP) {
                this.direction = Math.abs(dy) <= Math.abs(dx) && dx != 0 ? dirY : dirX;
            } else if (dirX == Direction.STOP) {
                this.direction = dirY;
            } else {
                this.direction = dirX;
            }
        }
    }

    public static class Pink extends Ghost {

        public Pink(int x, int y, int spookLength, Color color) {
            super(x, y, spookLength, color);
        }

        public void think(Direction dirX, Direction dirY, int dx, int dy, HashMap<Direction, Tile> surr) {
            dirX = dirX == Direction.LEFT ? Direction.RIGHT : Direction.LEFT;
            dirY = dirY == Direction.UP ? Direction.DOWN : Direction.UP;
            dirX = (surr.get(dirX) instanceof Wall) ? Direction.STOP : dirX;
            dirY = (surr.get(dirY) instanceof Wall) ? Direction.STOP : dirY;
            if (dirY != Direction.STOP && dirX != Direction.STOP) {
                this.direction = Math.abs(dy) <= Math.abs(dx) && dx != 0 ? dirX : dirY;
            } else if (dirX == Direction.STOP) {
                this.direction = dirY;
            } else {
                this.direction = dirX;
            }
        }
    }

    public static class Yellow extends Ghost {

        public Yellow(int x, int y, int spookLength, Color color) {
            super(x, y, spookLength, color);
        }

        public void think(Direction dirX, Direction dirY, int dx, int dy, HashMap<Direction, Tile> surr) {
            dirX = (surr.get(dirX) instanceof Wall) ? Direction.STOP : dirX;
            dirY = (surr.get(dirY) instanceof Wall) ? Direction.STOP : dirY;
            if (dirY != Direction.STOP && dirX != Direction.STOP) {
                this.direction = Math.abs(dx) >= Math.abs(dy) && dx != 0 ? dirX : dirY;
            } else if (dirX == Direction.STOP) {
                this.direction = dirY;
            } else {
                this.direction = dirX;
            }
        }
    }
}
