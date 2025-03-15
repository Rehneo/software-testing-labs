package com.rehneo.domain;

import static com.rehneo.domain.MetalState.LIQUID;
import static com.rehneo.domain.MetalState.SOLID;

public class Metal extends Protection {
    private final ComputerBank computerBank;
    private MetalState state = SOLID;

    public Metal(ComputerBank computerBank, int health) {
        super(health);
        this.computerBank = computerBank;
    }

    @Override
    public void takeDamage(int damage) {
        int diff = (health - damage) * (health - damage < 0 ? -1 : 0);
        health = (health - damage) * (health - damage < 0 ? 0 : 1);
        if (state == SOLID && health == 0) {
            state = LIQUID;
        }
        if (diff > 0) melt(damage);
    }

    private void melt(int damage) {
        computerBank.getRoom().getCorner().takeDamage(damage);
    }

    public MetalState getState() {
        return state;
    }
}
