package com.rehneo.trigonometry;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecantTest {
    private static final double EPSILON = 1e-4;
    private final Sine sine = new Sine();
    private final Cosine cosine = new Cosine(sine);
    private final Secant secant = new Secant(cosine);

    @ParameterizedTest
    @CsvFileSource(resources = "/test_secant.csv", numLinesToSkip = 1)
    void testSecant(double input, double expected) {
        double result = secant.calculate(input);
        assertEquals(expected, result, EPSILON);
    }
}
