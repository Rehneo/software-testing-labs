package com.rehneo.domain;

public class Corner extends Protection {
    private final Room room;

    public Corner(Room room, int health) {
        super(health);
        this.room = room;
    }

    @Override
    public void takeDamage(int damage) {
        int diff = (health - damage) * (health - damage < 0 ? -1 : 0);
        health = (health - damage) * (health - damage < 0 ? 0 : 1);
        if (health == 0) {
            if (room.getPeople() != null) {
                room.getPeople().seeDanger();
            }
        }
    }
}
