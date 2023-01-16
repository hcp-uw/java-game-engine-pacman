package src.com.jgegroup.pacman;

import src.com.jgegroup.pacman.objects.Ghost;
import src.com.jgegroup.pacman.objects.Pacman;
import src.com.jgegroup.pacman.objects.Position;

import java.util.HashSet;

public class GameCycle {
    HashSet<Pacman> pacmen;
    HashSet<Ghost>  ghosts;
    public GameCycle(){}

    private void actualCycle(){
        for(Pacman _pacman: pacmen){
            mapCollision(_pacman.getPosition());
            for(Ghost _ghost : ghosts){
                _pacman.collisionCheck(_ghost);
            }
        }
        for(Ghost _ghost: ghosts){
            //TODO: do what we want the ghost do here
            //We can't put the logic in the inner loop, as multiple pacman will cause
            //multiple moves.
        }
    }

    private void mapCollision(Position pos){
        //TODO: add actual thing here
    }

}
