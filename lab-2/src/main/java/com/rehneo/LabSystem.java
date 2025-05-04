package com.rehneo;

import com.rehneo.logarithm.*;
import com.rehneo.trigonometry.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LabSystem extends MathFunction {
    private Cosine cosine;
    private Secant secant;
    private Cosecant cosecant;
    private Tangent tangent;
    private NaturalLog naturalLog;
    private Log2 log2;
    private Log3 log3;
    private Log5 log5;
    private Log10 log10;

    public LabSystem(
            Cosine cosine, Secant secant,
            Cosecant cosecant,
            Tangent tangent,
            NaturalLog naturalLog,
            Log2 log2,
            Log3 log3,
            Log5 log5,
            Log10 log10
    ) {
        this.cosine = cosine;
        this.secant = secant;
        this.cosecant = cosecant;
        this.tangent = tangent;
        this.naturalLog = naturalLog;
        this.log2 = log2;
        this.log3 = log3;
        this.log5 = log5;
        this.log10 = log10;
    }

    @Override
    public double calculate(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return Double.NaN;
        }
        if (x <= 0) {
            BigDecimal cos = BigDecimal.valueOf(cosine.calculate(x));
            BigDecimal tan = BigDecimal.valueOf(tangent.calculate(x));
            double secV = secant.calculate(x);
            double cscV = cosecant.calculate(x);
            if (Double.isNaN(secV) || Double.isInfinite(secV)) {
                return Double.NaN;
            }
            if (Double.isNaN(cscV) || Double.isInfinite(cscV)) {
                return Double.NaN;
            }
            BigDecimal sec = BigDecimal.valueOf(secV);
            BigDecimal csc = BigDecimal.valueOf(cscV);

            return sec.pow(3)
                    .multiply(csc)
                    .multiply(csc)
                    .add(cos)
                    .multiply(csc.pow(2))
                    .subtract(tan).pow(2)
                    .pow(3)
                    .doubleValue();
        } else {
            BigDecimal ln = BigDecimal.valueOf(naturalLog.calculate(x));
            BigDecimal lg2 = BigDecimal.valueOf(log2.calculate(x));
            BigDecimal lg3 = BigDecimal.valueOf(log3.calculate(x));
            BigDecimal lg5 = BigDecimal.valueOf(log5.calculate(x));
            BigDecimal lg10 = BigDecimal.valueOf(log10.calculate(x));

            try {
                return lg2.multiply(lg2).
                        add(lg3.pow(3)).
                        pow(3).pow(3).
                        add(lg10.divide(lg5, RoundingMode.HALF_UP).
                                divide(ln.multiply(lg2), RoundingMode.HALF_UP).
                                subtract(lg2.add(lg10)).
                                multiply(lg10)).
                        doubleValue();
            } catch (ArithmeticException e) {
                return Double.NaN;
            }
        }
    }

    public void setCosine(Cosine cosine) {
        this.cosine = cosine;
    }

    public void setCosecant(Cosecant cosecant) {
        this.cosecant = cosecant;
    }

    public void setSecant(Secant secant) {
        this.secant = secant;
    }

    public void setTangent(Tangent tangent) {
        this.tangent = tangent;
    }

    public void setNaturalLog(NaturalLog naturalLog) {
        this.naturalLog = naturalLog;
    }

    public void setLog2(Log2 log2) {
        this.log2 = log2;
    }

    public void setLog3(Log3 log3) {
        this.log3 = log3;
    }

    public void setLog5(Log5 log5) {
        this.log5 = log5;
    }

    public void setLog10(Log10 log10) {
        this.log10 = log10;
    }

    @Override
    public String toString(){
        return "system(x)";
    }
}
