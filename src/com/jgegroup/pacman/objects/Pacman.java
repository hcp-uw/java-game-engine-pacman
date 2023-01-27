package com.jgegroup.pacman.objects;

public class Pacman extends GameObjects {
    private boolean Super;
    private int lives;
    private int score;
    // spawnX and spawnY are the original postion of pacman
    // at start of game
    private final int spawnX;

    private final int spawnY;

    public Pacman(int spawnX, int spawnY) {
        super(spawnX, spawnY);
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.lives = 3;
        Super = false;
        score = 0;
    }

    public boolean death() {
        lives--;
        if (lives >= 0) {
            this.position.moveTo(spawnX, spawnY);
            return true;
        }
        return false;
    }

    public void eat(Consumables consumable) {
        this.score += consumable.score;
    }

    public boolean isSuper() {
        return this.Super;
    }

    @Override
    protected int collisionHandle(GameObjects object) {
        if (object instanceof Ghost) {
            this.death();
            return 1;
        } else if (object instanceof Wall) {
            return 1;
        } else if (object instanceof Consumables) {
            this.eat((Consumables)object);
            return 1;
        }
        return 0;
    }
}
