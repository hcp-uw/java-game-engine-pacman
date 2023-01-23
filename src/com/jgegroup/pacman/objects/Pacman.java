package jgegroup.pacman.objects;

public class Pacman extends Character {
    private boolean Super;
    public Pacman(int x, int y) {
        super(x, y);
        Super = false;
    }

    public void death() {

    }

    public void respawn() {

    }

    public void eat() {

    }

    public boolean isSuper() {
        return this.Super;
    }

    @Override
    protected int collisionHandle(GameObjects object) {
        return 0;
    }
}
