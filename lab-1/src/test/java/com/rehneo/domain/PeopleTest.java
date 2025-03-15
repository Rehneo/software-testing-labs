package com.rehneo.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PeopleTest {
    private Room room1;
    private Room room2;
    private Room room3;
    private People people;

    @BeforeEach
    public void setUp() {
        room1 = new Room(100);
        room2 = new Room(100);
        room3 = new Room(100);
        people = new People(List.of(room1, room2, room3));

        room1.setPeople(people);
    }

    @Test
    public void testInitialRoom() {
        assertEquals(room1, people.getCurrentRoom());
    }

    @Test
    public void testSeeDangerAndMoveToNextRoom() {
        room1.getCorner().takeDamage(100);
        assertEquals(room2, people.getCurrentRoom());
        assertFalse(people.isDead());
    }

    @Test
    public void testSeeDangerAndMoveToLastRoom() {
        room1.getCorner().takeDamage(100);
        room2.getCorner().takeDamage(100);
        assertEquals(room3, people.getCurrentRoom());
    }

    @Test
    public void testMoveBeyondAvailableRooms() {
        room1.getCorner().takeDamage(100);
        room2.getCorner().takeDamage(100);
        room3.getCorner().takeDamage(100);
        assertTrue(people.isDead());
    }

    @Test
    public void testDeathConditionWhenNoAvailableRooms() {
        people = new People(List.of(room1));
        room1.setPeople(people);
        room1.getCorner().takeDamage(120);
        assertTrue(people.isDead());
    }

    @Test
    public void testStayAliveIfThereAreAvailableRooms() {
        people = new People(List.of(room1, room2));
        room1.setPeople(people);

        room1.getCorner().takeDamage(100);
        assertEquals(room2, people.getCurrentRoom());

        assertFalse(people.isDead());
    }

    @Test
    public void testEmptyList() {
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> new People(null)
        );
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> new People(List.of())
        );
    }
}
