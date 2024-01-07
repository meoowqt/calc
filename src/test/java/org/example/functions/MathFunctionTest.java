package org.example.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathFunctionTest {
    private final static double DELTA = 0.000_1;

    private final MathFunction x = new IdentityFunction();
    private final MathFunction sqr = new SqrFunction();
    private final MathFunction one = new UnitFunction();
    private final MathFunction cos = new CosFunction();
    private final MathFunction sin = new SinFunction();

    private final MathFunction function1 = (sqr).andThen(sqr).andThen(x);
    private final MathFunction function2 = (sin).andThen(sqr).andThen(sqr).andThen(x);

    @Test
    void andThen() {
        assertNotEquals(function1.apply(1000.0), 100.0, DELTA);
        assertEquals(function1.andThen(one).apply(0.0), 1.0, DELTA);
        assertEquals(function1.apply(10.0), 10000.0, DELTA);
        assertEquals(function2.apply(2.0), 0.6836, DELTA);
        assertEquals(function2.andThen(cos).apply(7.0), 0.98269, DELTA);
    }
}