package test;

import com.jgegroup.pacman.objects.Enums.Direction;
import com.jgegroup.pacman.objects.Position;
import com.jgegroup.pacman.objects.characters.Ghost;
import com.jgegroup.pacman.objects.characters.Pacman;
import com.jgegroup.pacman.objects.characters.Pink;
import com.jgegroup.pacman.objects.consumables.Consumable;
import com.jgegroup.pacman.objects.consumables.Dot;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class PacmanTests {

    @Test
    void death() {
        Pacman testSubject = new Pacman(0,0,10);
        int lives = testSubject.getLives();
        boolean result = testSubject.death();
        assertEquals(lives - 1, testSubject.getLives());
        assertTrue(result);
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
        assertFalse(result);
    }

    @Test
    void eat() {
        Pacman testSubject = new Pacman(0,0,10);
        Consumable dot = new Dot(0,0);
        Consumable eatenDot = testSubject.eat(dot);
        assertEquals(testSubject.getScore(), dot.getScore());
        assertEquals(eatenDot, dot);
    }

    @Test
    void checkQueue() {
        Pacman testSubject = new Pacman(0,0,10);
        Consumable dots[] = new Dot[17];
        for (int i = 0; i < 17; i++) {
            dots[i] = new Dot(i, i);
        }
        Consumable firstEaten = dots[0];
        for (int i = 0; i < 17; i++) {
            testSubject.eat(dots[i]);
        }
        Consumable queueOutput = testSubject.checkQueue();
        assertEquals(queueOutput, firstEaten);
    }

    @Test
    void setSuper() {
        Pacman testSubject = new Pacman(0,0,10);
        System.out.println("Setting super");
        testSubject.setSuper();
        System.out.println("Super is " + testSubject.isSuper());
        assertTrue(testSubject.isSuper());
    }

    @Test
    void updateSuper() {
        Pacman testSubject = new Pacman(0,0,10);
        System.out.println("Setting Super");
        testSubject.setSuper();
        System.out.println("Starting update super loop");
        for (int i = 1; i <= 11; i++) {
            int superBefore = testSubject.getSuper();
            boolean result = testSubject.updateSuper();
            int superAfter = testSubject.getSuper();
            System.out.printf("Super state before %d, super state after %d, " +
                    "update super returned: %s\n", superBefore, superAfter, result ? "True" : "False");
            if (superBefore != 0)
                assertEquals(superBefore, superAfter + 1);
            if (superBefore > 0) {
                assertTrue(result);
            } else {
                assertFalse(result);
            }
        }

    }

    @Test
    void isSuper() {
        Pacman testSubject = new Pacman(0,0,10);
        int sb4 = testSubject.getSuper();
        testSubject.setSuper();
        int sAfter = testSubject.getSuper();
        System.out.printf("%d Super before setting, %d super after setting\n", sb4, sAfter);
        assertNotEquals(sb4, sAfter);
        System.out.println("Now testing repeated setting");
        int superBase = 20;
        for (int i = 0; i <= 10; i++) {
            testSubject.setSuper();
            int superAfter = testSubject.getSuper();
            assertEquals(superAfter, superBase + (10 * i));
        }

    }

    @Test
    void getLives() {
        Pacman testSubject = new Pacman(0,0,10);
        System.out.println("Created Pacman, now checking initial lives are correct");
        assertEquals(3, testSubject.getLives());
        System.out.println("Initial lives are correct\n");
        System.out.println("Now checking to see Death is correctly decrementing lives");
        for (int i = 1; i <= 4; i++) {
            int livesBefore = testSubject.getLives();
            testSubject.death();
            int livesAfter = testSubject.getLives();
            System.out.printf("%d lives before death call, %d lives after death call\n", livesBefore, livesAfter);
            assertEquals(livesBefore, livesAfter + 1);
        }
        System.out.println("Death is decrementing correctly");
    }

    @Test
    void simulateMovement() {
        Pacman testSubject = new Pacman(0,0,10);
        System.out.println("Created Pacman testsubject. Now conducting movement tests");
        System.out.println("Creating Direction array with indices [LEFT, UP, RIGHT, DOWN, STOP] to test movement");
        Position pacPos = testSubject.getPosition();
        int x = pacPos.getX();
        int y = pacPos.getY();
        Direction dirs[] = {Direction.LEFT, Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.STOP};
        for (int i = 0; i < dirs.length; i++) {
            System.out.printf("Testing Direction %s, with x and y inital cordinates at %d, %d\n",
                    "" + dirs[i], x, y);
            System.out.println("Performing move");
            if (dirs[i] == Direction.LEFT) {
                pacPos.translate(-1, 0);
                assertEquals(x - 1, pacPos.getX());
                assertEquals(y, pacPos.getY());
                System.out.println("Direction Right tests passed, proceeding");
            } else if (dirs[i] == Direction.UP) {
                pacPos.translate(0, 1);
                assertEquals(x, pacPos.getX());
                assertEquals(y + 1, pacPos.getY());
                System.out.println("Direction Up tests passed, proceeding");
            } else if (dirs[i] == Direction.RIGHT) {
                pacPos.translate(1, 0);
                assertEquals(x + 1, pacPos.getX());
                assertEquals(y, pacPos.getY());
                System.out.println("Direction Right tests passed, proceeding");
            } else if (dirs[i] == Direction.DOWN) {
                pacPos.translate(0, -1);
                assertEquals(x, pacPos.getX());
                assertEquals(y - 1, pacPos.getY());
                System.out.println("Direction Down tests passed, proceeding");
            }
            x = pacPos.getX();
            y = pacPos.getY();
        }
        System.out.println("All direction tests passed :)");
    }
    @Test
    void collisionHandle() {
        Pacman testSubject = new Pacman(0,0,10);
        Pacman testSubject2 = new Pacman(0,0,10);
        Ghost pinkie = new Pink(0,0,10);
        testSubject.setRadius(1);
        testSubject2.setRadius(1);
        pinkie.setRadius(1);
        System.out.println("Created Pacmen and Pink Ghost to test with");
        System.out.println("Testing Pacman vs Pink Ghost no Super");
        int result = testSubject.collisionCheck(pinkie);
        assertEquals(result, 100);
        System.out.println("No super Pacman vs Ghost tests passed");

        System.out.println("Testing Pacman vs Ghost with super");
        testSubject.setSuper();
        result = testSubject.collisionCheck(pinkie);
        assertEquals(result, 101);
        System.out.println("Super Pacman vs Ghost tests passed");

        System.out.println("Testing Pacman vs Pacman");
        result = testSubject.collisionCheck(testSubject2);
        assertEquals(result, 102);
        System.out.println("Pacman vs Pacman tests passed");

        System.out.println("Moving ghost to another position");
        pinkie.getPosition().moveTo(25,25);
        System.out.println("Testing collision with entity out of radius");
        result = testSubject.collisionCheck(pinkie);
        assertEquals(result, -1);
        System.out.println("All Tests passed");
    }
}