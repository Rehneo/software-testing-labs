package com.rehneo.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ComputerBankTest {
    private ComputerBank computerBank;

    @BeforeEach
    public void setUp() {
        Room room = new Room(100);
        computerBank = room.getComputerBank();
    }

    @Test
    public void testInitialHealth() {
        assertEquals(100, computerBank.getHealth());
    }

    @Test
    public void testTakeDamage() {
        computerBank.takeDamage(30);
        assertEquals(70, computerBank.getHealth());
    }

    @Test
    public void testTakeDamageZeroHealth() {
        computerBank.takeDamage(100);
        assertEquals(0, computerBank.getHealth());
    }
}
