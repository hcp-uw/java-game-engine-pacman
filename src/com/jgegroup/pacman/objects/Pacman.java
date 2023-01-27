package com.jgegroup.pacman.objects;

public class Pacman extends GameObjects {
    private boolean Super;
    private int lives;
    private int score;

    private final int

    public Pacman(int x, int y, int lives) {
        super(x, y);
        this.lives = lives;
        Super = false;
        score = 0;
    }

    public boolean death() {
        lives--;
        if (lives >= 0) {
            this.position.setX(/* SpawnX */);
            this.position.setY(/* SpawnY */);
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
