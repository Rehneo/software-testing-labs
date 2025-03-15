package com.rehneo.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CornerTest {
    private Corner corner;
    private Room room;

    @BeforeEach
    public void setUp() {
        room = new Room(100);
        corner = room.getCorner();
    }

    @Test
    public void testInitialHealth() {
        assertEquals(100, corner.getHealth());
    }

    @Test
    public void testTakeDamage() {
        corner.takeDamage(40);
        assertEquals(60, corner.getHealth());
    }

    @Test
    public void testTakeDamageZeroHealth() {
        corner.takeDamage(100);
        assertEquals(0, corner.getHealth());
    }

    @Test
    public void testPeopleSeeDanger() {
        room.setPeople(new People(List.of(room)));
        corner.takeDamage(100);
        assertTrue(room.getPeople().isDead());
    }
}
