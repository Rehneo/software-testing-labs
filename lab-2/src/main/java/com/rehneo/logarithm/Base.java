package com.rehneo.logarithm;

public enum Base {
    TWO(2),
    THREE(3),
    FIVE(5),
    TEN(10);

    private final int value; // store the value

    Base(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
