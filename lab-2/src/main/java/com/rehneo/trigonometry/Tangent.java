package com.rehneo.trigonometry;

import com.rehneo.MathFunction;

public class Tangent extends MathFunction {
    private final Sine sine;
    private final Cosine cosine;

    public Tangent(Sine sine, Cosine cosine) {
        this.sine = sine;
        this.cosine = cosine;
    }

    @Override
    public double calculate(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return Double.NaN;
        }
        return sine.calculate(x) / cosine.calculate(x);
    }

    @Override
    public String toString() {
        return "tan(x)";
    }
}
