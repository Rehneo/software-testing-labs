package com.rehneo.logarithm;

import com.rehneo.MathFunction;

public abstract class BaseLog extends MathFunction {
    private final int base;
    private final NaturalLog ln;

    public BaseLog(NaturalLog ln, Base base) {
        this.base = base.getValue();
        this.ln = ln;
    }

    @Override
    public double calculate(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x) || x <= 0) {
            return Double.NaN;
        }
        return ln.calculate(x) / ln.calculate(base);
    }

    @Override
    public String toString() {
        return "log" + base + "(x)";
    }
}
