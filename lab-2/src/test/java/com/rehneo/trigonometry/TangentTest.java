package com.rehneo.trigonometry;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TangentTest {
    private static final double EPSILON = 1e-6;
    private final Sine sine = new Sine();
    private final Cosine cosine = new Cosine(sine);
    private final Tangent tangent = new Tangent(sine, cosine);

    @ParameterizedTest
    @CsvFileSource(resources = "/test_tangent.csv", numLinesToSkip = 1)
    void testTangent(double input, double expected) {
        double result = tangent.calculate(input);
        assertEquals(expected, result, EPSILON);
    }
}
