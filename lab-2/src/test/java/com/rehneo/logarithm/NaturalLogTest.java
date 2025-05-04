package com.rehneo.logarithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class NaturalLogTest {
    private static final double EPSILON = 1e-6;
    private final NaturalLog ln = new NaturalLog();

    @ParameterizedTest
    @CsvFileSource(resources = "/test_ln.csv", numLinesToSkip = 1)
    void testSine(double input, double expected) {
        double result = ln.calculate(input);
        Assertions.assertEquals(expected, result, EPSILON);
    }
}
