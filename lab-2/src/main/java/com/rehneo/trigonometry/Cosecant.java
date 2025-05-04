package com.rehneo.trigonometry;

import com.rehneo.MathFunction;

public class Cosecant extends MathFunction {
    private final Sine sine;

    public Cosecant(Sine sine) {
        this.sine = sine;
    }

    @Override
    public double calculate(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return Double.NaN;
        }
        return 1 / sine.calculate(x);
    }

    @Override
    public String toString() {
        return "csc(x)";
    }
}
