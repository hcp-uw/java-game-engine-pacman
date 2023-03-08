package com.jgegroup.pacman.objects.characters;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GhostTest {

    @Test
    void testGetAndSetRadius() {
        Ghost ghost = new Ghost();
        ghost.setRadius(5);
        assertEquals(5, ghost.getRadius());
    }

    @Test
    void testGetAndSetDirection() {
        Ghost ghost = new Ghost();
        ghost.setDirection(Direction.LEFT);
        assertEquals(Direction.LEFT, ghost.getDirection());
    }

    @Test
    void testGetPosition() {
        Ghost ghost = new Ghost();
        ghost.setPosition(10, 20);
        assertEquals(10, ghost.getPosition().getX());
        assertEquals(20, ghost.getPosition().getY());
    }

    @Test
    void testSetAndGetSpooked() {
        Ghost ghost = new Ghost();
        ghost.setSpooked(true);
        assertTrue(ghost.isSpooked());
    }

    @Test
    void testUpdateSpooked() {
        Ghost ghost = new Ghost();
        ghost.setSpooked(true);
        assertTrue(ghost.isSpooked());
        ghost.updateSpooked();
        assertFalse(ghost.isSpooked());
    }

    @Test
    void testCollisionCheck() {
        Ghost ghost = new Ghost();
        Pacman pacman = new Pacman();
        assertFalse(ghost.collisionCheck(pacman));
    }

    @Test
    void testCollisionHandle() {
        Ghost ghost = new Ghost();
        Pacman pacman = new Pacman();
        ghost.collisionHandle(pacman);
        assertTrue(pacman.isDead());
    }

    @Test
    void testGetColor() {
        Ghost ghost = new Ghost();
        assertEquals(Color.RED, ghost.getColor());
    }

    @Test
    void testThink() {
        Ghost ghost = new Ghost();
        ghost.think();
        assertNotNull(ghost.getDirection());
    }

    @Test
    void testThinkPrep() {
        Ghost ghost = new Ghost();
        ghost.thinkPrep();
        assertNotNull(ghost.getDestination());
    }
}