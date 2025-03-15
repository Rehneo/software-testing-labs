package com.rehneo.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RoomTest {
    private Room room;

    @BeforeEach
    public void setUp() {
        room = new Room(100);
        People people = new People(List.of(room));
        room.setPeople(people);
    }

    @Test
    public void testInitialHealth() {
        assertEquals(100, room.getHealth());
    }

    @Test
    public void testTakeDamage() {
        room.takeDamage(50);
        assertEquals(50, room.getHealth());
    }

    @Test
    public void testTakeDamageBelowZero() {
        room.takeDamage(150);
        assertEquals(0, room.getHealth());
    }

    @Test
    public void testNoiseLevelAfterDamage() {
        room.takeDamage(50);
        assertEquals(NoiseLevel.MEDIUM, room.getNoiseLevel());

        room.takeDamage(50);
        assertEquals(NoiseLevel.LOUD, room.getNoiseLevel());
    }

    @Test
    public void testTemperatureAfterZeroHealth() {
        room.takeDamage(100);
        assertEquals(Temperature.HOT, room.getTemperature());
    }

    @Test
    public void testZeroHealth() {
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> new Room(0)
        );
    }
}
