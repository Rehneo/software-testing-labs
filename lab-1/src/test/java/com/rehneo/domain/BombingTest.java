package com.rehneo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class BombingTest {
    private Bombing bombing;
    private Room room;

    @BeforeEach
    public void setUp() {
        bombing = new Bombing();
        room = mock(Room.class);
    }

    @Test
    public void testBombRoom() {
        bombing.bomb(room, 30);
        verify(room, times(1)).takeDamage(30);
    }

    @Test
    public void testBombRoomWithZeroDamage() {
        bombing.bomb(room, 0);
        verify(room, times(1)).takeDamage(0);
    }
}
