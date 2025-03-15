package com.rehneo.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MetalTest {
    private Metal metal;

    @BeforeEach
    public void setUp() {
        Room room = new Room(100);
        metal = room.getComputerBank().getFrontSide();
    }

    @Test
    public void testInitialState() {
        assertEquals(MetalState.SOLID, metal.getState());
    }

    @Test
    public void testTakeDamage() {
        metal.takeDamage(50);
        assertEquals(50, metal.getHealth());
        assertEquals(MetalState.SOLID, metal.getState());
    }

    @Test
    public void testMeltOnDamage() {
        metal.takeDamage(100);
        assertEquals(0, metal.getHealth());
        assertEquals(MetalState.LIQUID, metal.getState());
    }
}
