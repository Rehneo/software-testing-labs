package com.rehneo.integration.trigonometry;

import com.rehneo.trigonometry.Cosine;
import com.rehneo.trigonometry.Sine;
import com.rehneo.trigonometry.Tangent;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TangentITTest {
    private static final double EPSILON = 1e-6;
    private final Sine sine = mock();
    private final Cosine cosine = mock();
    private final Tangent tangent = new Tangent(sine, cosine);

    @ParameterizedTest
    @CsvFileSource(resources = "/integration/test_tangent.csv", numLinesToSkip = 1)
    void testTangent(double input, double expected, double sineValue, double cosineValue) {
        when(sine.calculate(input)).thenReturn(sineValue);
        when(cosine.calculate(input)).thenReturn(cosineValue);
        double result = tangent.calculate(input);
        assertEquals(expected, result, EPSILON);
    }
}
