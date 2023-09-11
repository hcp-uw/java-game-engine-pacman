package com.jgegroup.pacman.objects.characters;


import com.jgegroup.pacman.MainScene;
import com.jgegroup.pacman.objects.Entity;
import com.jgegroup.pacman.objects.immovable.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import com.jgegroup.pacman.objects.Enums.*;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Random;


// Note: Death and respawning will be handled by the game cycle by the reassignment of the ghost to
// another ghost.

public class Ghost extends Entity // implements GhostMovement
         {
    //Spook length is the cycle length interval for how long a ghost will
    // be spooked. Can exceed this length if more big dots are eaten
    public final int spookLength;
    // Container for base color of the Ghost which is the color that the Ghost is when not spooked
    private Color base_color;
    // Container for the color of the Ghost that we're currently showing to the screen
    private Color current_color;
    // State container for the spook timer
    private int spookState;

//
//    public Ghost(int x, int y, int spookLength, Color color) {
//        super(x,y);
//        this.spookLength = spookLength;
//        this.base_color = color;
//        this.current_color = color;
//        // this indicates that it is not spooked, -1 <- no spook. > 0 <- yes spook
//        this.spookState = -1;
//        this.direction = Direction.STOP;
//    }



    MainScene mainScene;
    long lastTime;
    public Ghost(int spookLength, MainScene mainScene, Color color) {
        this.spookLength = spookLength;
        this.mainScene = mainScene;
        lastTime = System.currentTimeMillis();
        this.base_color = color;
        setDefaultValues();
        setGhostImage();
    }

    public void setDefaultValues() {
        x = 300;
        y = 200;
        collision_range = new Rectangle(0, 0, 28, 28);

        speed = 1;
        direction = Direction.STOP;
    }

    public void setGhostImage() {
//        up = new Image("characters/Pacman Up.png");
    }

    public void update() {

        collisionDetected = false;
        mainScene.collisionChecker.checkTile(this);

            Direction nextMove = Direction.STOP;
            // figure out what ghost will do here
            {
                if (System.currentTimeMillis() - lastTime > 1000) {
                    lastTime = System.currentTimeMillis();
                    Random random = new Random();
                    int decision = random.nextInt(128);
                    decision = decision % 4;
                    switch (decision) {
                        case 0:
                            nextMove = Direction.LEFT;
                            break;
                        case 1:
                            nextMove = Direction.RIGHT;
                            break;
                        case 2:
                            nextMove = Direction.DOWN;
                            break;
                        case 3:
                            nextMove = Direction.UP;
                            break;
                        default:
                            nextMove = Direction.STOP;
                            break;
                    }
                }

            }
            if (nextMove != Direction.STOP) {
                direction = nextMove;
            }
            if (!collisionDetected) {
                switch (direction) {
                    case UP:
                        this.y -= speed;
                        break;
                    case DOWN:
                        this.y += speed;
                        break;
                    case LEFT:
                        this.x -= speed;
                        break;
                    case RIGHT:
                        this.x += speed;
                        break;
                    default:
                        break;
                }
            }


        System.out.println("Entity collisionDetected is " + collisionDetected);
    }

    public void redraw(GraphicsContext painter) {
        painter.clearRect(x - 5, y - 5, MainScene.RESOLUTION_HORIZONTAL, MainScene.RESOLUTION_VERTICAL);
        painter.setFill(base_color);
        painter.fillRect(x, y, mainScene.TILE_SIZE, mainScene.TILE_SIZE);
//        painter.clearRect(x - speed, y - speed, mainScene.RESOLUTION_HORIZONTAL, mainScene.RESOLUTION_VERTICAL);
//        painter.drawImage(up, x, y, 400, 100);



    }



    /** @@Authors: Noah / Nikola
     * Sets spookState to maximum spook state 'spookLength'
     * spook length represents the number of cycles to be spooked
     * Throws no exceptions
     * Returns nothing
     * Takes no parameters
    */
    public void setSpooked() { spookState += spookLength; }

    /** @@Authors: Noah / Nikola
     * Updatess the spook state, if the state is even it will change the color of the ghost
     * to white, and if its odd then it will change to blue, if not spooked it will return to original color
     * Throws no exceptions
     * Returns true if is currently spooked or if its coming out of being spooked
     * Takes no parameters
    */
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

    /** @@Author: Noah
     * Checks to see if the ghost is spooked
     * Throws no exceptions
     * Returns true if spookState container is greater than or equal to 0, else false
     * Takes in no paramters
    */
    public boolean isSpooked() { return spookState >= 0; }

    /** @@Authors: Jesse / Noah / Davin
     * Determines if the object has collided with something or not
     * Throws, no exceptions
     * Returns 1 if collided with Pacman or Wall, 0 otherwise
     * Takes in a GameObject as a parameter
    */
//    @Override
//    protected int collisionHandle(MovingObject object) {
// //        if (object instanceof Pacman) {
// //            return 1;
// //        } else if (object instanceof Wall) {
// //            return 2;
// //        }
//
//        // here we will need to look for the overlap in the MovingObject
//        // we will try to see if the circles overlap
//
//        // HOW WE WILL DO IT:
//        // WE will need to get the centers of each of the circles.
//        // We will need to check in all 4 directions, and we will need
//        // check to see if there is any overlap in the four directions
//
//        Position otherPosition = object.getPosition();
//
////        Position currentPosition = this.position;
//
//        // now we will need to check
//        // all of the possible cases
//        int currentX = this.getPosition().getX();
//        int currentY = this.getPosition().getY();
//
//        int currentRadius = object.getRadius();
//
//        int otherX = otherPosition.getX();
//        int otherY = otherPosition.getY();
//
//        int otherRadius = this.getRadius();
//
//        // you will check for overlap in the X direction
//        if (currentX == otherX) {
//            if (currentY > otherY) {
//                if (currentY - currentRadius > otherY + otherRadius) {
//                    return 0;
//                } else {
//                    return 1;
//                }
//            } else if (currentY < otherY) {
//                if (currentY + currentRadius < otherY - otherRadius) {
//                    return 0;
//                } else {
//                    return 1;
//                }
//            } else {
//                return 1;
//            }
//        }
//
//        // this is where you check for overlap in the Y direction
//        if (currentY == otherY) {
//            if (currentX > otherX) {
//                if (currentX - currentRadius > otherX + otherRadius) {
//                    return 0;
//                } else {
//                    return 1;
//                }
//            } else if (currentX < otherX) {
//                if (currentX + currentRadius < otherX - otherRadius) {
//                    return 0;
//                } else {
//                    return 1;
//                }
//            } else {
//                return 1;
//            }
//        }
//
//        // this is when you do not have any overlap that could be possible in any direction
//        return 0;
//    }

}
