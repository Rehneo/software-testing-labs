package com.rehneo;

public abstract class MathFunction {
    protected static final double EPSILON = 1e-10;

    public abstract double calculate(double x);
}
