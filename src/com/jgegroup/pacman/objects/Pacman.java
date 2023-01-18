package jgegroup.pacman.objects;

public class Pacman extends Character {

    public Pacman(int x, int y) {
        super(x, y);
    }

//    public void moveLeft() {
//        position.translate(-1.0, 0);
//    }
//
//    public void moveRight() {
//
//    }
//
//    public void moveUp() {
//
//    }
//
//    public void moveDown() {
//
//    }

    public void death() {

    }

    public void respawn() {

    }

    public void getMove() {

    }
    public void eat() {

    }

    @Override
    protected int collisionHandle(GameObjects object) {
        return 0;
    }
}
