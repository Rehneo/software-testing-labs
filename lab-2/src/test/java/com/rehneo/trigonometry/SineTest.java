package com.rehneo.trigonometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class SineTest {
    private static final double EPSILON = 1e-8;
    private final Sine sine = new Sine();

    @ParameterizedTest
    @CsvFileSource(resources = "/test_sine.csv", numLinesToSkip = 1)
    void testSine(double input, double expected) {
        double result = sine.calculate(input);
        Assertions.assertEquals(expected, result, EPSILON);
    }

    @Test
    void testNanValues() {
        double result = sine.calculate(Double.NaN);
        Assertions.assertEquals(Double.NaN, result, EPSILON);
        double result2 = sine.calculate(Double.POSITIVE_INFINITY);
        Assertions.assertEquals(Double.NaN, result2, EPSILON);
        double result3 = sine.calculate(Double.NEGATIVE_INFINITY);
        Assertions.assertEquals(Double.NaN, result3, EPSILON);
    }
}
