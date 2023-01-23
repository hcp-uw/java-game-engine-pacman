package jgegroup.pacman.objects;

public abstract class Consumables extends GameObjects{
    protected int score;
    public Consumables(int x, int y) {
        this(x,y,0);
    }
    public Consumables(Position position){
        this(position,0);
    }
    public Consumables(int x, int y, int score) {
        super(x,y);
        this.score=score;
    }
    public Consumables(Position position, int score){
        super(position);
        this.score=score;
    }




}
