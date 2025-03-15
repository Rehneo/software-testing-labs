package com.rehneo.domain;

public class Room extends Protection {
    private Temperature temperature;
    private NoiseLevel noiseLevel;
    private final ComputerBank computerBank;
    private final Corner corner;
    private People people;

    public Room(int initialHealth) {
        super(initialHealth);
        this.computerBank = new ComputerBank(this, health);
        this.corner = new Corner(this, health);
        this.temperature = Temperature.MEDIUM;
        this.noiseLevel = NoiseLevel.CALM;
    }

    @Override
    public void takeDamage(int damage) {
        int diff = (health - damage) * (health - damage < 0 ? -1 : 0);
        health = (health - damage) * (health - damage < 0 ? 0 : 1);
        if (this.noiseLevel == NoiseLevel.CALM && (double) health / initialHealth <= 0.5) {
            this.noiseLevel = NoiseLevel.MEDIUM;
        }
        if (this.noiseLevel == NoiseLevel.MEDIUM && health == 0) {
            this.noiseLevel = NoiseLevel.LOUD;
            this.temperature = Temperature.HOT;
        }
        if (diff > 0) computerBank.takeDamage(diff);
    }

    public Temperature getTemperature() {
        return this.temperature;
    }

    public NoiseLevel getNoiseLevel() {
        return this.noiseLevel;
    }

    public Corner getCorner() {
        return this.corner;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public ComputerBank getComputerBank() {
        return this.computerBank;
    }

    public People getPeople() {
        return this.people;
    }
}
