package com.jgegroup.pacman.objects.characters;

import com.jgegroup.pacman.KeyHandler;
import com.jgegroup.pacman.MainScene;
import com.jgegroup.pacman.objects.Entity;
import com.jgegroup.pacman.objects.Enums.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Pac extends Entity {

    private MainScene mainScene;
    private KeyHandler keyHandler;
    int life;
    int point;
    boolean collidedGhost;
    int Super;
    int superLength;
    int pacman_spawn_x;
    int pacman_spawn_y;
    long last_time;


    public Pac(MainScene mainScene, KeyHandler keyHandler) {
        this.mainScene = mainScene;
        this.keyHandler = keyHandler;
        last_time = System.currentTimeMillis();
        Super = -1;
        superLength = 20;
        initialize();
        setPacImage();
    }

    public void initialize() {
      setPacImage();
      spriteImage = pacZero;
      spriteNumber = 1;
      life = 3;
      point = 0;
      collidedGhost = false;
      pacman_spawn_x = 32 * 13;
      pacman_spawn_y = 32 * 15;
      x = pacman_spawn_x;
      y = pacman_spawn_y;
      speed = 1;
      collision_range = new Rectangle(0, 0, MainScene.TILE_SIZE - 1, MainScene.TILE_SIZE - 1);
      direction = Direction.STOP;
      newDirection = Direction.STOP;
    }


    public void update() {
        setNewDirection(keyHandler.movement);
        setCurrentDirection(newDirection);
        collisionDetected = mainScene.collisionChecker.isValidDirection(this, direction);
        updatePosition(collisionDetected);
        eatDot();
        updateSuper();
//        if (System.currentTimeMillis() >= last_time + 1000) {
//            updateSuper();
//            last_time = System.currentTimeMillis();
//        }

        spriteCounter++;
        if (spriteCounter > 10) {
          this.spriteNumber = spriteNumber == 1 ? 2: 1;

          spriteCounter = 0;
      }
    }

  public void redraw(GraphicsContext painter) {
    if (painter != null) {
      painter.clearRect(0, 0, mainScene.RESOLUTION_HORIZONTAL, mainScene.RESOLUTION_VERTICAL);
      updateImage();
      painter.drawImage(spriteImage, x, y, MainScene.TILE_SIZE, MainScene.TILE_SIZE);
    }
  }

  public void eatDot() {
      int current_column = x / MainScene.TILE_SIZE;
      int current_row = y / MainScene.TILE_SIZE;
      if (mainScene.map.mapArray2D[current_column][current_row] == 0) {
        mainScene.map.mapArray2D[current_column][current_row] = 2;
        point += 10;
      } else if (mainScene.map.mapArray2D[current_column][current_row] == 3) {
          mainScene.map.mapArray2D[current_column][current_row] = 2;
          point += 100;
          setSuper();
      }
  }
    /** @@Author Noah
     * Sets the super state
     * @Throws no exceptions
     * @Returns nothing
     * Takes in no parameters
     **/
    public void setSuper() {
        setSuper(superLength);
    }

    public void setSuper(int length) {
        if (this.Super <= 0)
            this.Super = length;
        else
            this.Super += length;
    }

    /** @@Author: Noah
     * Updates the super state
     * @Throws no exceptions
     * @Returns true if the pacman is super, else false
     * Takes in no parameters
     **/
    public boolean updateSuper() {
        if (this.Super >= 0) {
            this.Super--;
            return true;
        }
        return false;
    }

    /** @@Author: Noah
     * Checks to see if Pacman is super
     * @Throws no exceptions
     * @Returns true if super state container is greater than or equal to 0, else false
     * Takes in no parameters
     */
    public boolean isSuper() { return this.Super >= 0; }

    /** @@Author: Noah
     * Gets the remaining super cycles
     * Throws no exceptions
     * @return Integer denoting how many super cycles are left
     * Takes in no parameters
     */
    public int getSuper() { return this.Super; }

    /** @@Author: Noah
     * Gets num of lives left
     * @Throws no exceptions
     * @Returns number of lives left
     * Takes in no parameters
     */
    public int getLives() { return this.life; }

    public int getPoint() {
      return point;
    }

    public synchronized void death() {
        spawn();
        life--;
        //setSuper(3);
        System.out.println("life: " + life);
    }
    public void spawn() {
      x = pacman_spawn_x;
      y = pacman_spawn_y;
    }

    public void setNewDirection(Direction key) {
        switch (key) {
            case UP:
                newDirection = Direction.UP;
                break;
            case DOWN:
                newDirection = Direction.DOWN;
                break;
            case LEFT:
                newDirection = Direction.LEFT;
                break;
            case RIGHT:
                newDirection = Direction.RIGHT;
                break;
            default:
                break;
        }
    }
    public void setCurrentDirection(Direction newDirection) {
      if (!mainScene.collisionChecker.isValidDirection(this, newDirection)) {
        direction = newDirection;
      }
    }


    public void updatePosition(boolean collisionDetected) {
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
    }
    public void updateImage() {
      switch (direction) {
        case UP:
          this.spriteImage = this.spriteNumber == 1 ? up1: up2;
          break;
        case DOWN:
          this.spriteImage = this.spriteNumber == 1 ? down1: down2;
          break;
        case LEFT:
          this.spriteImage = this.spriteNumber == 1 ? left1: left2;
          break;
        case RIGHT:
          this.spriteImage = this.spriteNumber == 1 ? right1: right2;
          break;
        default:
          break;
      }
    }
    public void setPacImage() {
      pacZero = new Image("pac/pacman_0.png");
      up1 = new Image("pac/pacman_up1.png");
      up2 = new Image("pac/pacman_up2.png");
      down1 = new Image("pac/pacman_down1.png");
      down2 = new Image("pac/pacman_down2.png");
      left1 = new Image("pac/pacman_left1.png");
      left2 = new Image("pac/pacman_left2.png");
      right1 = new Image("pac/pacman_right1.png");
      right2 = new Image("pac/pacman_right2.png");
    }
}
