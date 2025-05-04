package com.rehneo.logarithm;

import com.rehneo.MathFunction;

public class NaturalLog extends MathFunction {
    @Override
    public double calculate(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x) || x <= 0) {
            return Double.NaN;
        }
        double z = (x - 1) / (x + 1);
        double term = z;
        double ln = 0;
        int n = 1;
        double ln1 = 10;

        while (Math.abs(ln - ln1) >= EPSILON / 512) {
            ln1 = ln;
            ln += term;
            n += 2;
            term *= z * z * (n - 2) / n;
        }

        return 2 * ln;
    }

    @Override
    public String toString() {
        return "ln(x)";
    }
}
