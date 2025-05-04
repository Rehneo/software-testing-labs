package com.rehneo.trigonometry;

import com.rehneo.MathFunction;

public class Cosine extends MathFunction {
    private final Sine sine;

    public Cosine(Sine sine) {
        this.sine = sine;
    }

    @Override
    public double calculate(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return Double.NaN;
        }
        double sinValue = sine.calculate(x);
        double cosValue = Math.sqrt(Math.abs(1 - sinValue*sinValue));
        x = x % (2 * Math.PI);
        if (Math.abs(x) > Math.PI / 2 && Math.abs(x) < 3 * Math.PI / 2) {
            cosValue = -cosValue;
        }

        return cosValue;
    }

    @Override
    public String toString() {
        return "cos(x)";
    }
}
