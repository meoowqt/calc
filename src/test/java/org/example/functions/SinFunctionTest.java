package org.example.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinFunctionTest {
    private final static double DELTA = 0.000_1;

    @Test
    void apply() {
        assertEquals(new SinFunction().apply(1.), 0.84147, DELTA);
        assertEquals(new SinFunction().apply(0.), 0., DELTA);
        assertEquals(new SinFunction().apply(0.5), 0.47942, DELTA);
    }
}