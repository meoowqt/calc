package org.example.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZeroFunctionTest {
    private final static double DELTA = 0.000_1;

    @Test
    void apply() {
        assertEquals(new ZeroFunction().apply(100.0), 0.0, DELTA);
        assertEquals(new ZeroFunction().apply(90.0), 0.0, DELTA);
        assertEquals(new ZeroFunction().apply(667.0), 0.0, DELTA);
    }


}