package org.example.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitFunctionTest {
    private final static double DELTA = 0.000_1;

    @Test
    void apply() {
        assertEquals(new UnitFunction().apply(100.0), 1.0, DELTA);
        assertEquals(new UnitFunction().apply(90.0), 1.0, DELTA);
        assertEquals(new UnitFunction().apply(667.0), 1.0, DELTA);
    }

}