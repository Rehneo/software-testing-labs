package com.rehneo.trigonometry;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CosineTest {
    private static final double EPSILON = 1e-6;
    private final Sine sine = new Sine();
    private final Cosine cosine = new Cosine(sine);

    @ParameterizedTest
    @CsvFileSource(resources = "/test_cosine.csv", numLinesToSkip = 1)
    void testCosine(double input, double expected) {
        double result = cosine.calculate(input);
        assertEquals(expected, result, EPSILON);
    }
}
