package com.rehneo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AsinSeriesTest {
    private static final double DELTA = 1e-6;

    @Test
    @DisplayName("Тестирование значений из области определения")
    public void testAsinTypicalValues() {
        double[] testValues = {-0.9, -0.5, -0, 3, 0.0, 0.3, 0.5, 0.9};
        for (double x : testValues) {
            assertEquals(Math.asin(x), AsinSeries.asin(x), DELTA);
        }
    }

    @Test
    @DisplayName("Тестирование границ области определения")
    public void testAsinBoundaryValues() {
        assertEquals(Math.asin(-1.0), AsinSeries.asin(-1.0), DELTA);
        assertEquals(Math.asin(1.0), AsinSeries.asin(1.0), DELTA);
    }

    @Test
    @DisplayName("Тестирование значений, близких к границам области определения")
    public void testAsinNearBoundaryValues() {
        assertEquals(Math.asin(-0.99999), AsinSeries.asin(-0.99999), DELTA);
        assertEquals(Math.asin(0.99999), AsinSeries.asin(0.99999), DELTA);
        assertEquals(Math.asin(-0.9990), AsinSeries.asin(-0.9990), DELTA);
        assertEquals(Math.asin(0.9990), AsinSeries.asin(0.9990), DELTA);
    }

    @Test
    @DisplayName("Тестирование значений за областью определения")
    public void testInfiniteAndNaNValues() {
        assertEquals(Double.NaN, AsinSeries.asin(Double.NaN));
        assertEquals(Double.NaN, AsinSeries.asin(Double.POSITIVE_INFINITY));
        assertEquals(Double.NaN, AsinSeries.asin(Double.NEGATIVE_INFINITY));
        assertEquals(Double.NaN, AsinSeries.asin(3));
        assertEquals(Double.NaN, AsinSeries.asin(-2));
    }
}
