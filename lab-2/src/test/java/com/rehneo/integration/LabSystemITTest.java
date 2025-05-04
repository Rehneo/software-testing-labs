package com.rehneo.integration;

import com.rehneo.LabSystem;
import com.rehneo.logarithm.*;
import com.rehneo.trigonometry.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

public class LabSystemITTest {
    private static final double EPSILON = 1e-2;

    record TestSource(String resourcePath, String label) { }

    record TrigInputs(double input, double expected, double cos, double sec, double csc, double tg) {}
    record LogInputs(
            double input,
            double expected,
            double lnV,
            double log2V,
            double log3V,
            double log5V,
            double log10V
    ) {}

    static List<TestSource> trigInputFiles() {
        return List.of(
                new TestSource("/integration/system/test_system_trig_boundary.csv", "Boundary"),
                new TestSource("/integration/system/test_system_trig_decrease.csv", "Decrease"),
                new TestSource("/integration/system/test_system_trig_increase.csv", "Increase")
        );
    }

    static List<TestSource> logInputFiles() {
        return List.of(
                new TestSource("/integration/system/test_system_log_increase_1.csv", "Increase One"),
                new TestSource("/integration/system/test_system_log_increase_2.csv", "Increase Two"),
                new TestSource("/integration/system/test_system_log_increase_3.csv", "Increase Three"),
                new TestSource("/integration/system/test_system_log_boundary.csv", "Boundary"),
                new TestSource("/integration/system/test_system_log_decrease_1.csv", "Decrease One"),
                new TestSource("/integration/system/test_system_log_decrease_2.csv", "Decrease Two")
        );
    }


    @ParameterizedTest(name = "{0} | {1} | {2}")
    @MethodSource("provideAllTrigTests")
    void testTrigFunction(
            TrigInputs inputs,
            TrigDependencySet deps,
            String fileLabel
    ) {
        if (isMock(deps.cos())) when(deps.cos().calculate(inputs.input)).thenReturn(inputs.cos);
        if (isMock(deps.sec())) when(deps.sec().calculate(inputs.input)).thenReturn(inputs.sec);
        if (isMock(deps.csc())) when(deps.csc().calculate(inputs.input)).thenReturn(inputs.csc);
        if (isMock(deps.tg())) when(deps.tg().calculate(inputs.input)).thenReturn(inputs.tg);

        LabSystem system = new LabSystem(
                deps.cos(), deps.sec(), deps.csc(), deps.tg(),
                mock(), mock(), mock(), mock(), mock()
        );

        double result = system.calculate(inputs.input);
        assertEquals(inputs.expected, result, EPSILON, "Failed in: " + fileLabel + " with deps: " + deps.label);
    }

    @ParameterizedTest(name = "{0} | {1} | {2}")
    @MethodSource("provideAllLogTests")
    void testLogFunction(
            LogInputs inputs,
            LogDependencySet deps,
            String fileLabel
    ) {
        if(isMock(deps.ln)) when(deps.ln.calculate(inputs.input)).thenReturn(inputs.lnV);
        if(isMock(deps.log2)) when(deps.log2.calculate(inputs.input)).thenReturn(inputs.log2V);
        if(isMock(deps.log3)) when(deps.log3.calculate(inputs.input)).thenReturn(inputs.log3V);
        if(isMock(deps.log5))  when(deps.log5.calculate(inputs.input)).thenReturn(inputs.log5V);
        if(isMock(deps.log10)) when(deps.log10.calculate(inputs.input)).thenReturn(inputs.log10V);

        LabSystem system = new LabSystem(
                mock(), mock(), mock(), mock(),
                deps.ln, deps.log2, deps.log3, deps.log5, deps.log10
        );

        double result = system.calculate(inputs.input);
        assertEquals(inputs.expected, result, EPSILON, "Failed in: " + fileLabel + " with deps: " + deps.label);
    }

    private record TrigDependencySet(Cosine cos, Secant sec, Cosecant csc, Tangent tg, String label) { }
    private record LogDependencySet(NaturalLog ln, Log2 log2, Log3 log3, Log5 log5, Log10 log10, String label) { }

    private static Stream<TrigDependencySet> trigDependencyVariants() {
        Sine sin = new Sine();
        Cosine cos = new Cosine(sin);
        Secant sec = new Secant(cos);
        Cosecant csc = new Cosecant(sin);
        Tangent tg = new Tangent(sin, cos);

        return Stream.of(
                new TrigDependencySet(mock(), mock(), mock(), mock(), "all mocked"),
                new TrigDependencySet(cos, mock(), mock(), mock(), "cos"),
                new TrigDependencySet(cos, sec, mock(), mock(), "cos, sec"),
                new TrigDependencySet(cos, sec, csc, mock(), "cos, sec, csc"),
                new TrigDependencySet(cos, sec, csc, tg, "all ")
        );
    }

    private static Stream<LogDependencySet> logDependencyVariants() {
        NaturalLog ln = new NaturalLog();
        Log2 log2 = new Log2(ln);
        Log3 log3 = new Log3(ln);
        Log5 log5 = new Log5(ln);
        Log10 log10 = new Log10(ln);
        return Stream.of(
                new LogDependencySet(mock(), mock(), mock(), mock(), mock(), "all mocked"),
                new LogDependencySet(ln, mock(), mock(), mock(), mock(), "ln "),
                new LogDependencySet(ln, log2, mock(), mock(), mock(), "ln, log2"),
                new LogDependencySet(ln, log2, log3, mock(), mock(), "ln, log2, log3"),
                new LogDependencySet(ln, log2, log3, log5, mock(), "ln, log2, log3, log5"),
                new LogDependencySet(ln, log2, log3, log5, log10, "ln, log2, log3, log5, log10")
        );
    }

    private static Stream<Arguments> provideAllTrigTests() {
        return trigInputFiles().stream().flatMap(source -> {
            var resource = LabSystemITTest.class.getResourceAsStream(source.resourcePath());
            if (resource == null) {
                System.err.println("Resource not found: " + source.resourcePath());
                return Stream.empty();
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource))) {
                List<Arguments> argumentList = reader.lines()
                        .skip(1)
                        .map(line -> line.split(","))
                        .flatMap(parts -> {
                            TrigInputs input = new TrigInputs(
                                    Double.parseDouble(parts[0]),
                                    Double.parseDouble(parts[1]),
                                    Double.parseDouble(parts[2]),
                                    Double.parseDouble(parts[3]),
                                    Double.parseDouble(parts[4]),
                                    Double.parseDouble(parts[5])
                            );
                            return trigDependencyVariants().map(deps ->
                                    arguments(input, deps, source.label())
                            );
                        }).toList();
                return argumentList.stream();
            } catch (Exception e) {
                throw new RuntimeException("Failed reading " + source.resourcePath(), e);
            }
        });
    }

    private static Stream<Arguments> provideAllLogTests() {
        return logInputFiles().stream().flatMap(source -> {
            var resource = LabSystemITTest.class.getResourceAsStream(source.resourcePath());
            if (resource == null) {
                System.err.println("Resource not found: " + source.resourcePath());
                return Stream.empty();
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource))) {
                List<Arguments> argumentList = reader.lines()
                        .skip(1)
                        .map(line -> line.split(","))
                        .flatMap(parts -> {
                            LogInputs input = new LogInputs(
                                    Double.parseDouble(parts[0]),
                                    Double.parseDouble(parts[1]),
                                    Double.parseDouble(parts[2]),
                                    Double.parseDouble(parts[3]),
                                    Double.parseDouble(parts[4]),
                                    Double.parseDouble(parts[5]),
                                    Double.parseDouble(parts[6])
                            );
                            return logDependencyVariants().map(deps ->
                                    arguments(input, deps, source.label())
                            );
                        }).toList();
                return argumentList.stream();
            } catch (Exception e) {
                throw new RuntimeException("Failed reading " + source.resourcePath(), e);
            }
        });
    }

    private boolean isMock(Object obj) {
        return mockingDetails(obj).isMock();
    }
}
