package com.jgegroup.pacman.objects.characters;


import com.jgegroup.pacman.MainScene;
import com.jgegroup.pacman.PathFinder;
import com.jgegroup.pacman.objects.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import com.jgegroup.pacman.objects.Enums.*;
import javafx.scene.shape.Rectangle;

import java.util.HashSet;
import java.util.Set;


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

    private Pac pacman;
    private GhostMovement gm;

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



    private MainScene mainScene;
    private long last_time;
    private long moveCounter = 0;
    private PathFinder pf;
    public Ghost(int spookLength, MainScene mainScene, Color color, Pac pacman) {
        this.spookLength = 5;
        this.mainScene = mainScene;
        last_time = System.currentTimeMillis();
        this.base_color = color;
        this.current_color = color;
        this.pacman = pacman;
        gm = new GhostMovement(color, pacman);
        pf = new PathFinder(mainScene.map);
        setGhostImage();
        setWhiteGhostImage();
        initialize();
        last_time = System.currentTimeMillis();
    }

    public void initialize() {
        spriteImage = up1;
        spriteNumber = 0;
        spriteCounter = 0;
        x = 32 * 11;
        y = 32 * 13;
        collision_range = new Rectangle(0, 0, 31, 31);
        spookState = -1;
        speed = 1;
        direction = Direction.STOP;
    }


    public void update() {
        collisionDetected = mainScene.collisionChecker.isValidDirection(this, direction);
        Set<Direction> restrictions = new HashSet<>();
        if (direction.equals(Direction.DOWN)) {
            restrictions.add(Direction.UP);
        } else if (direction.equals(Direction.UP)) {
            restrictions.add(Direction.DOWN);
        } else if (direction.equals(Direction.RIGHT)) {
            restrictions.add(Direction.LEFT);
        } else if (direction.equals(Direction.LEFT)) {
            restrictions.add(Direction.RIGHT);
        }
        if (moveCounter == 32) {
            direction = isSpooked() ? pf.scatter(this.x, this.y, pacman.x, pacman.y, restrictions) :
            pf.chase(this.x, this.y, pacman.x, pacman.y, restrictions);
            collisionDetected = mainScene.collisionChecker.isValidDirection(this, direction);
            while (collisionDetected) {
                restrictions.add(direction);
                direction = isSpooked() ? pf.scatter(this.x, this.y, pacman.x, pacman.y, restrictions) :
                        pf.chase(this.x, this.y, pacman.x, pacman.y, restrictions);
                collisionDetected = mainScene.collisionChecker.isValidDirection(this, direction);
            }
            moveCounter = 0;
        }
        moveCounter++;
        pacmanCollision();
        if (pacman.isSuper() && !isSpooked()) {
            System.out.println("Pacman is luffy");
            setSpooked();
        }
        if (System.currentTimeMillis() >= last_time + 1000) {
            updateSpooked();
            last_time = System.currentTimeMillis();
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
      spriteCounter++;
      if (spriteCounter > 10) {
        this.spriteNumber = spriteNumber == 1 ? 2: 1;
        spriteCounter = 0;
      }
    }

    public void redraw(GraphicsContext painter) {
        painter.clearRect(0, 0, MainScene.RESOLUTION_HORIZONTAL, MainScene.RESOLUTION_VERTICAL);
        if (isSpooked()) {
          updateSpookedImage();
        } else {
          updateNormalImage();
        }
        painter.drawImage(spriteImage, x, y, mainScene.TILE_SIZE, mainScene.TILE_SIZE);
//        painter.clearRect(x - speed, y - speed, mainScene.RESOLUTION_HORIZONTAL, mainScene.RESOLUTION_VERTICAL);
//        painter.drawImage(up, x, y, 400, 100);
    }

    public boolean pacmanCollision() {
        int pacman_min_x = pacman.x, pacman_min_y = pacman.y;
        int ghost_min_x = this.x, ghost_min_y = this.y;
        Rectangle ghost_collision_range = this.collision_range;

        int ghost_max_x = ghost_min_x + (int) ghost_collision_range.getWidth();
        int ghost_max_y = ghost_min_y + (int) ghost_collision_range.getHeight();

        // max > min

        if (ghost_max_x - pacman_min_x >= 0 && ghost_max_x - pacman_min_x <= 2 * MainScene.TILE_SIZE
            && ghost_max_y - pacman_min_y <= 2 * MainScene.TILE_SIZE && ghost_max_y - pacman_min_y >= 0) {
            if (!pacman.isSuper()) {
                pacman.death();
            } else {
                this.initialize();
            }
            return true;
        }
        return false;
    }


    /** @@Authors: Noah / Nikola
     * Sets spookState to maximum spook state 'spookLength'
     * spook length represents the number of cycles to be spooked
     * Throws no exceptions
     * Returns nothing
     * Takes no parameters
    */
    public void setSpooked() {
      spookState += spookLength;
      spriteImage = white1;
    }

    public void updateSpookedImage() {
      System.out.println("state" + spookState);
      this.spriteImage = this.spriteNumber == 1 ? white1 : white2;
      if (spookState % 2 == 0) {
        this.spriteImage = this.spriteNumber == 1 ? blue1 : blue2;
      }
    }

    /** @@Authors: Noah / Nikola
     * Updatess the spook state, if the state is even it will change the color of the ghost
     * to white, and if its odd then it will change to blue, if not spooked it will return to original color
     * Throws no exceptions
     * Returns true if is currently spooked or if its coming out of being spooked
     * Takes no parameters
    */
    public boolean updateSpooked() {
        if (spookState >= 0) {
            spookState--;
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
    public boolean isSpooked() { return spookState >= 0;
    }
    public void updateNormalImage() {
      switch (direction) {
        case UP: this.spriteImage = this.spriteNumber == 1 ? up1: up2; break;
          case DOWN: this.spriteImage = this.spriteNumber == 1 ? down1: down2; break;
          case LEFT: this.spriteImage = this.spriteNumber == 1 ? left1: left2; break;
          case RIGHT: this.spriteImage = this.spriteNumber == 1 ? right1: right2; break;
          default: break;
      }
    }
    public void setGhostImage() {
      if (base_color.equals(Color.RED)) {
        System.out.println("RED");
        up1 = new Image("ghosts/red_up1.png");
        up2 = new Image("ghosts/red_up2.png");
        down1 = new Image("ghosts/red_down1.png");
        down2 = new Image("ghosts/red_down2.png");
        left1 = new Image("ghosts/red_left1.png");
        left2 = new Image("ghosts/red_left2.png");
        right1 = new Image("ghosts/red_right1.png");
        right2 = new Image("ghosts/red_right2.png");
      } else if (base_color.equals(Color.BLUE)) {
        System.out.println("BLUE");
        up1 = new Image("ghosts/blue_up1.png");
        up2 = new Image("ghosts/blue_up2.png");
        down1 = new Image("ghosts/blue_down1.png");
        down2 = new Image("ghosts/blue_down2.png");
        left1 = new Image("ghosts/blue_left1.png");
        left2 = new Image("ghosts/blue_left2.png");
        right1 = new Image("ghosts/blue_right1.png");
        right2 = new Image("ghosts/blue_right2.png");
      } else if (base_color.equals(Color.YELLOW)) {
        System.out.println("YELLOW");
        up1 = new Image("ghosts/yellow_up1.png");
        up2 = new Image("ghosts/yellow_up2.png");
        down1 = new Image("ghosts/yellow_down1.png");
        down2 = new Image("ghosts/yellow_down2.png");
        left1 = new Image("ghosts/yellow_left1.png");
        left2 = new Image("ghosts/yellow_left2.png");
        right1 = new Image("ghosts/yellow_right1.png");
        right2 = new Image("ghosts/yellow_right2.png");
      } else if (base_color.equals(Color.PINK)) {
        System.out.println("PINK");
        up1 = new Image("ghosts/pink_up1.png");
        up2 = new Image("ghosts/pink_up2.png");
        down1 = new Image("ghosts/pink_down1.png");
        down2 = new Image("ghosts/pink_down2.png");
        left1 = new Image("ghosts/pink_left1.png");
        left2 = new Image("ghosts/pink_left2.png");
        right1 = new Image("ghosts/pink_right1.png");
        right2 = new Image("ghosts/pink_right2.png");
      }
    }
    public void setWhiteGhostImage() {
      blue1 = new Image("ghosts/spook1.png");
      blue2 = new Image("ghosts/spook2.png");
      white1 = new Image("ghosts/spook3.png");
      white2 = new Image("ghosts/spook4.png");
    }

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
