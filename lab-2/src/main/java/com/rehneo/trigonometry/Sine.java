package com.rehneo.trigonometry;

import com.rehneo.MathFunction;

public class Sine extends MathFunction {
    @Override
    public double calculate(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return Double.NaN;
        }
        x = x % (2 * Math.PI);

        double term = x;
        double sum = term;
        int n = 1;

        while (Math.abs(term) >= EPSILON) {
            n += 2;
            term *= -x * x / (n * (n - 1));
            sum += term;
        }

        return sum;
    }

    @Override
    public String toString() {
        return "sin(x)";
    }
}
