package com.rehneo.integration.logarithm;

import com.rehneo.logarithm.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseLogITTest {
    private static final double EPSILON = 1e-6;
    private static final double LN_2 = 0.6931472;
    private static final double LN_3 = 1.0986122;
    private static final double LN_5 = 1.6094379;
    private static final double LN_10 = 2.3025851;
    private final NaturalLog ln = mock();
    private final BaseLog base2Log = new Log2(ln);
    private final BaseLog base3Log = new Log3(ln);
    private final BaseLog base5Log = new Log5(ln);
    private final BaseLog base10Log = new Log10(ln);


    @ParameterizedTest
    @CsvFileSource(resources = "/integration/test_log2.csv", numLinesToSkip = 1)
    void testBase2Log(double input, double expected, double lnValue) {
        when(ln.calculate(input)).thenReturn(lnValue);
        when(ln.calculate(2)).thenReturn(LN_2);
        double result = base2Log.calculate(input);
        assertEquals(expected, result, EPSILON);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/integration/test_log3.csv", numLinesToSkip = 1)
    void testBase3Log(double input, double expected, double lnValue) {
        when(ln.calculate(input)).thenReturn(lnValue);
        when(ln.calculate(3)).thenReturn(LN_3);
        double result = base3Log.calculate(input);
        assertEquals(expected, result, EPSILON);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/integration/test_log5.csv", numLinesToSkip = 1)
    void testBase5Log(double input, double expected, double lnValue) {
        when(ln.calculate(input)).thenReturn(lnValue);
        when(ln.calculate(5)).thenReturn(LN_5);
        double result = base5Log.calculate(input);
        assertEquals(expected, result, EPSILON);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/integration/test_log10.csv", numLinesToSkip = 1)
    void testBase10Log(double input, double expected, double lnValue) {
        when(ln.calculate(input)).thenReturn(lnValue);
        when(ln.calculate(10)).thenReturn(LN_10);
        double result = base10Log.calculate(input);
        assertEquals(expected, result, EPSILON);
    }
}
