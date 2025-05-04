package com.rehneo.logarithm;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseLogTest {
    private static final double EPSILON = 1e-6;
    private final NaturalLog ln = new NaturalLog();
    private final BaseLog base2Log = new Log2(ln);
    private final BaseLog base3Log = new Log3(ln);
    private final BaseLog base5Log = new Log5(ln);
    private final BaseLog base10Log = new Log10(ln);


    @ParameterizedTest
    @CsvFileSource(resources = "/test_log2.csv", numLinesToSkip = 1)
    void testBase2Log(double input, double expected) {
        double result = base2Log.calculate(input);
        assertEquals(expected, result, EPSILON);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_log3.csv", numLinesToSkip = 1)
    void testBase3Log(double input, double expected) {
        double result = base3Log.calculate(input);
        assertEquals(expected, result, EPSILON);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_log5.csv", numLinesToSkip = 1)
    void testBase5Log(double input, double expected) {
        double result = base5Log.calculate(input);
        assertEquals(expected, result, EPSILON);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_log10.csv", numLinesToSkip = 1)
    void testBase10Log(double input, double expected) {
        double result = base10Log.calculate(input);
        assertEquals(expected, result, EPSILON);
    }
}
