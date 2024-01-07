package org.example.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompositeFunctionTest {
    private final static double DELTA = 0.000_1;

    @Test
    void apply() {
        assertEquals(new CompositeFunction(new CosFunction(), new SinFunction()).apply(1.), 0.5143, DELTA);
        assertEquals(new CompositeFunction(new SqrFunction(), new SinFunction()).apply(1.), 0.84147, DELTA);
        assertEquals(new CompositeFunction(new CosFunction(), new SqrFunction()).apply(1.), 0.29192, DELTA);
        assertEquals(new CompositeFunction(new CompositeFunction(new CosFunction(), new SinFunction()), new SqrFunction()).apply(1.), 0.2646, DELTA);
    }
}