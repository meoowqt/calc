package org.example.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentityFunctionTest {
    private final static double DELTA = 0.000_1;

    @Test
    void apply() {
        assertEquals(new IdentityFunction().apply(3.), 3., DELTA);
        assertEquals(new IdentityFunction().apply(25.), 25., DELTA);
        assertEquals(new IdentityFunction().apply(5000.), 5000., DELTA);
    }
}