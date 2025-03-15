package com.rehneo.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DomainTest {
    private Room room1;
    private Room room2;
    private Room room3;
    private People people;
    private Bombing bombing;
    private ComputerBank computerBank;
    private Metal frontSide;
    private Corner corner;

    @BeforeEach
    public void setUp() {
        room1 = new Room(100);
        room2 = new Room(100);
        room3 = new Room(100);
        corner = room1.getCorner();
        computerBank = room1.getComputerBank();
        frontSide = computerBank.getFrontSide();
        people = new People(List.of(room1, room2, room3));
        bombing = new Bombing();
    }

    @Test
    public void testPeopleMovement() {
        bombing.bomb(room1, 100);
        bombing.bomb(room2, 100);
        bombing.bomb(room3, 100);
        assertEquals(room1, people.getCurrentRoom());
        assertFalse(people.isDead());
        bombing.bomb(room1, 300);
        assertEquals(room2, people.getCurrentRoom());
        assertFalse(people.isDead());
    }

    @Test
    public void testBombingImpactOnRoom() {
        bombing.bomb(room1, 50);
        assertEquals(50, room1.getHealth());
        assertEquals(NoiseLevel.MEDIUM, room1.getNoiseLevel());
        assertEquals(Temperature.MEDIUM, room1.getTemperature());
        bombing.bomb(room1, 60);
        assertEquals(0, room1.getHealth());
        assertEquals(NoiseLevel.LOUD, room1.getNoiseLevel());
        assertEquals(Temperature.HOT, room1.getTemperature());
    }

    @Test
    public void testMetalStateAfterDamage() {
        assertEquals(MetalState.SOLID, frontSide.getState());
        bombing.bomb(room1, 100);
        assertEquals(MetalState.SOLID, frontSide.getState());
        bombing.bomb(room1, 100);
        assertEquals(MetalState.SOLID, frontSide.getState());
        bombing.bomb(room1, 100);
        assertEquals(MetalState.LIQUID, frontSide.getState());
    }
}
