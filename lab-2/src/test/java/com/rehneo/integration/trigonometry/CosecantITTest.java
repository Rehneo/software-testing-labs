package com.rehneo.integration.trigonometry;

import com.rehneo.trigonometry.Cosecant;
import com.rehneo.trigonometry.Sine;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CosecantITTest {
    private static final double EPSILON = 1e-6;
    private final Sine sine = mock();
    private final Cosecant cosecant = new Cosecant(sine);

    @ParameterizedTest
    @CsvFileSource(resources = "/integration/test_cosecant.csv", numLinesToSkip = 1)
    void testCosecant(double input, double expected, double sineValue) {
        when(sine.calculate(input)).thenReturn(sineValue);
        double result = cosecant.calculate(input);
        assertEquals(expected, result, EPSILON);
    }
}
