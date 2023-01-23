package jgegroup.pacman.objects;

public abstract class Character extends GameObjects {
    public Character(int x, int y) {
        super(x,y);
    }
    public Character(Position position){
        super(position);
    }
//    public abstract void moveLeft();
//    public abstract void moveRight();
//    public abstract void moveUp();
//    public abstract void moveDown();

}
