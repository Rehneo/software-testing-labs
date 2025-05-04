package com.rehneo.trigonometry;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CosecantTest {
    private static final double EPSILON = 1e-6;
    private final Sine sine = new Sine();
    private final Cosecant cosecant = new Cosecant(sine);

    @ParameterizedTest
    @CsvFileSource(resources = "/test_cosecant.csv", numLinesToSkip = 1)
    void testCosecant(double input, double expected) {
        double result = cosecant.calculate(input);
        assertEquals(expected, result, EPSILON);
    }
}
