package org.example.functions;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CosFunctionTest {
    private final static double DELTA = 0.000_1;

    @Test
    void apply() {
        assertEquals(new CosFunction().apply(1.), 0.54030, DELTA);
        assertEquals(new CosFunction().apply(0.), 1., DELTA);
        assertEquals(new CosFunction().apply(0.5), 0.87758, DELTA);
    }
}