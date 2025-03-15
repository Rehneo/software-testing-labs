package com.rehneo.domain;

public class ComputerBank extends Protection {
    private final Metal frontSide;
    private final Room room;

    public ComputerBank(Room room, int health) {
        super(health);
        this.room = room;
        this.frontSide = new Metal(this, health);
    }

    @Override
    public void takeDamage(int damage) {
        int diff = (health - damage) * (health - damage < 0 ? -1 : 0);
        health = (health - damage) * (health - damage < 0 ? 0 : 1);
        if (diff > 0) frontSide.takeDamage(diff);
    }

    public Room getRoom() {
        return this.room;
    }

    public Metal getFrontSide() {
        return this.frontSide;
    }
}
