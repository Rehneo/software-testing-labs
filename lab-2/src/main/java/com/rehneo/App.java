package com.rehneo;

import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import com.rehneo.logarithm.*;
import com.rehneo.trigonometry.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws PythonExecutionException, IOException {
        Sine sine = new Sine();
        Cosine cosine = new Cosine(sine);
        Cosecant cosecant = new Cosecant(sine);
        Secant secant = new Secant(cosine);
        Tangent tangent = new Tangent(sine, cosine);
        NaturalLog ln = new NaturalLog();
        Log2 log2 = new Log2(ln);
        Log3 log3 = new Log3(ln);
        Log5 log5 = new Log5(ln);
        Log10 log10 = new Log10(ln);

        plot(
                -12,
                0.1,
                1000,
                new LabSystem(
                        cosine,
                        secant,
                        cosecant,
                        tangent,
                        ln,
                        log2,
                        log3,
                        log5,
                        log10
                )
        );

        MathFunctionWriter.write(
                -8,
                0.01,
                1000,
                new LabSystem(
                        cosine,
                        secant,
                        cosecant,
                        tangent,
                        ln,
                        log2,
                        log3,
                        log5,
                        log10
                )
        );

        double x1 = 1.000044;
        double x2 = 1.000352;
        double x3 = 1.001272;
        double x4 = 0.527226;
        double x5 = 0.600869;
        double x6 = 1.704919;
        double x7 = -14.9524;
        double x8 = -14.9215;

        write(x1);
        write(x2);
        write(x3);
        write(x4);
        write(x5);
        write(x6);
        write(x7);
        write(x8);

//        System.out.println("ln(x): " + Math.log(x));
//        System.out.println("log2(x): " + Math.log(x)/Math.log(2));
//        System.out.println("log3(x): " + Math.log(x)/Math.log(3));
//        System.out.println("log5(x): " + Math.log(x)/Math.log(5));
//        System.out.println("log10(x): " + Math.log(x)/Math.log(10));
    }

    public static void write(double x) {
        System.out.print(Math.log(x) + ",");
        System.out.print(Math.log(x) / Math.log(2) + ",");
        System.out.print(Math.log(x) / Math.log(3) + ",");
        System.out.print(Math.log(x) / Math.log(5) + ",");
        System.out.print(Math.log(x) / Math.log(10));
        System.out.println();
    }


    private static void plot(
            double start,
            double step,
            int count,
            MathFunction f
    ) throws PythonExecutionException, IOException {
        List<Double> xs = new ArrayList<>();
        List<Double> ys = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            double x = start + i * step;
            double y = f.calculate(x);
            if (!Double.isNaN(y)) {
                xs.add(x);
                ys.add(y);
            }
        }

        Plot plt = Plot.create();

        plt.plot()
                .add(xs, ys, ".")
                .label(f.toString());

        plt.legend().loc("upper right");
        plt.title("scatter");

        plt.ylim(0, 5321143.62242 + 10000);

        plt.show();
    }
}