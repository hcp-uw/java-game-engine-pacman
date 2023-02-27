package test;

import com.jgegroup.pacman.objects.characters.Pacman;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacmanTests {



    @Test
    void death() {
        Pacman testSubject = new Pacman(0,0,10);
        int lives = testSubject.getLives();
        boolean result = testSubject.death();
        assertEquals(lives - 1, testSubject.getLives());
        assertEquals(result, true);
    }

    @Test
    void deathAfterNoLivesLeft() {
        Pacman testSubject = new Pacman(0,0,10);
        int lives = testSubject.getLives();
        System.out.println(lives);
        while (lives > 0) {
            lives--;
            boolean test = testSubject.death();
            System.out.println("" + lives + " " + test);
        }
        boolean result = testSubject.death();
        lives = testSubject.getLives();
        System.out.printf("%d lives, " + result + "\n", lives);
        assertEquals(lives, -1);
        assertEquals(result, false);
    }

    @Test
    void eat() {
    }

    @Test
    void checkQueue() {
    }

    @Test
    void setSuper() {
    }

    @Test
    void updateSuper() {
    }

    @Test
    void isSuper() {
    }

    @Test
    void getLives() {
    }

    @Test
    void collisionHandle() {
    }
}