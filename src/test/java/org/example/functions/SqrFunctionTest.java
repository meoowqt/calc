package org.example.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqrFunctionTest {
    private final static double DELTA = 0.000_1;

    @Test
    void apply() {
        assertEquals(new SqrFunction().apply(3.), 9., DELTA);
        assertEquals(new SqrFunction().apply(25.), 625., DELTA);
        assertEquals(new SqrFunction().apply(5000.), 25000000., DELTA);
    }
}