package com.rehneo.domain;

public class Bombing {
    public void bomb(Room room, int damage) {
        room.takeDamage(damage);
    }
}