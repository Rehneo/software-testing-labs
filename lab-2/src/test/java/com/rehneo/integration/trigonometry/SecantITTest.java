package com.rehneo.integration.trigonometry;

import com.rehneo.trigonometry.Cosine;
import com.rehneo.trigonometry.Secant;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SecantITTest {
    private static final double EPSILON = 1e-4;
    private final Cosine cosine = mock();
    private final Secant secant = new Secant(cosine);

    @ParameterizedTest
    @CsvFileSource(resources = "/integration/test_secant.csv", numLinesToSkip = 1)
    void testSecant(double input, double expected, double cosineValue) {
        when(cosine.calculate(input)).thenReturn(cosineValue);
        double result = secant.calculate(input);
        assertEquals(expected, result, EPSILON);
    }
}
