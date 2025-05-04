package com.rehneo.trigonometry;

import com.rehneo.MathFunction;

public class Secant extends MathFunction {
    private final Cosine cosine;

    public Secant(Cosine cosine) {
        this.cosine = cosine;
    }

    @Override
    public double calculate(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return Double.NaN;
        }
        return 1 / cosine.calculate(x);
    }

    @Override
    public String toString() {
        return "sec(x)";
    }
}
