package com.rehneo.domain;

public abstract class Protection {
    protected final int initialHealth;
    protected int health;

    public Protection(int initialHealth) {
        if (initialHealth <= 0) {
            throw new IllegalArgumentException("Initial health must be a positive number");
        }
        this.initialHealth = initialHealth;
        health = initialHealth;
    }

    public abstract void takeDamage(int damage);

    public int getHealth() {
        return health;
    }
}
