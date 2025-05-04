package com.rehneo;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class MathFunctionWriter {

    public static void write(
            double start,
            double step,
            int count,
            MathFunction f
    ) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(f.toString()))) {
            for (int i = 0; i < count; i++) {
                double x = start + i * step;
                double y = f.calculate(x);
                writer.printf(Locale.US, "%.8f,%.8f%n", x, y);
            }
        } catch (IOException e) {
            System.err.println("Failed to write CSV: " + e.getMessage());
        }
    }
}
