package jgegroup.pacman.objects;



public class Ghost extends Character {

    private Color color;
    private boolean spooked;
    public Ghost(int x, int y, Color color) {
        super(x, y);
        this.color = color;
        spooked = false;
    }

    // Todo: Add method signatures/code
    public void setSpooked() {
        spooked = true;
        //TODO: Add a timer, after the timer stopped, set spooked to false.
    }


//    public void moveLeft() {
//        // test
//        System.out.println("test");
//        System.out.println("Iman's test HAHA");
//
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

    @Override
    protected int collisionHandle(GameObjects object) {
        if (object instanceof Pacman) {
            if (object.isSuper()) {
                death();
                // Wait x Seconds
                respawn();
            }
        }
        return 0;
    }
}
