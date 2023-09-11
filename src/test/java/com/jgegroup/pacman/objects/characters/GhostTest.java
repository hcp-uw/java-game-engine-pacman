//package com.jgegroup.pacman.objects.characters;
//
//import com.jgegroup.pacman.objects.Enums.Direction;
//
//import javafx.scene.paint.Color;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//class GhostTest {
//
//    @Test
//    void testGetAndSetRadius() {
//        Ghost ghost = new Ghost(0, 0, 10, Color.BLUE);
//        ghost.setRadius(5);
//        assertEquals(5, ghost.getRadius());
//    }
//
//    @Test
//    void testGetAndSetDirection() {
//        Ghost ghost = new Ghost(0, 0, 10, Color.BLUE);
//        ghost.setDirection(Direction.LEFT);
//        assertEquals(Direction.LEFT, ghost.getDirection());
//    }
//
//    @Test
//    void testGetPosition() {
//        Ghost ghost = new Ghost(0, 0, 10, Color.BLUE);
//        ghost.getPosition().moveTo(10,20);
//        assertEquals(10, ghost.getPosition().getX());
//        assertEquals(20, ghost.getPosition().getY());
//    }
//
//    @Test
//    void testSetAndGetSpooked() {
//        Ghost ghost = new Ghost(0, 0, 10, Color.BLUE);
//        ghost.setSpooked();
//        assertTrue(ghost.isSpooked());
//    }
//
//    @Test
//    void testUpdateSpooked() {
//        Ghost ghost = new Ghost(0, 0, 10, Color.BLUE);
//        ghost.setSpooked();
//        assertTrue(ghost.isSpooked());
//        for (int i = 0; i < 9; i++) {
//            ghost.updateSpooked();
//            assertTrue(ghost.isSpooked());
//        }
//        ghost.updateSpooked();
//        assertFalse(ghost.isSpooked());
//    }
//
//    @Test
//    void testCollisionCheck() {
//        Ghost ghost = new Ghost(0, 0, 10, Color.BLUE);
//        ghost.setRadius(5);
//        Pacman pacman = new Pacman(6, 6, 10);
//        pacman.setRadius(5);
//        assertEquals(ghost.collisionCheck(pacman), 0);
//    }
//
//
//    @Test
//    void testGetColor() {
//        Ghost ghost = new Ghost(0, 0, 10, Color.BLUE);
//        assertEquals(Color.BLUE, ghost.getColor());
//    }
//
//}