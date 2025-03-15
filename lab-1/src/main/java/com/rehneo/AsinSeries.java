package com.rehneo;

public class AsinSeries {
    private static final int TERMS = 1000000;

    public static double asin(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x) || (x < -1) || (x > 1)) {
            return Double.NaN;
        }

        if (x == 1) {
            return Math.PI / 2;
        }
        if (x == -1) {
            return -Math.PI / 2;
        }

        double sum = x;
        double term = 1;
        double xm = x;
        for (double i = 1; i <= TERMS; i++) {
            xm *= x * x;
            term *= ((2 * i - 1) * 2 * i) / (i * i * 4);
            sum += (term * xm) / (2 * i + 1);
        }
        return sum;
    }
}
