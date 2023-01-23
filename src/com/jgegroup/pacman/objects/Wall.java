package jgegroup.pacman.objects;


public class Wall extends GameObjects{

    public Wall(int x, int y) {
        super(x, y);
    }

    public Wall(Position position) {
        super(position);
    }
    @Override
    protected int collisionHandle(GameObjects object) {
        if (object instanceof Pacman || object instanceof Ghost) {
            return 1;
        }
        return 0;
    }
}
