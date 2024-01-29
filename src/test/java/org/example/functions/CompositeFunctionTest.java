package org.example.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompositeFunctionTest {
    private final static double DELTA = 0.000_1;
    private final double[] xValues = new double[]{1., 3.5, 5., 7};
    private final double[] yValues = new double[]{2., 4., 6.9, 8.};

    @Test
    void apply() {
        assertEquals(new CompositeFunction(new CosFunction(), new SinFunction()).apply(1.), 0.5143, DELTA);
        assertEquals(new CompositeFunction(new SqrFunction(), new SinFunction()).apply(1.), 0.84147, DELTA);
        assertEquals(new CompositeFunction(new CosFunction(), new SqrFunction()).apply(1.), 0.29192, DELTA);
        assertEquals(new CompositeFunction(new CompositeFunction(new CosFunction(), new SinFunction()), new SqrFunction()).apply(1.), 0.2646, DELTA);
        assertEquals(new CompositeFunction(new ArrayTabulatedFunction(xValues, yValues), new SqrFunction()).apply(1.), 4.0, DELTA);
        assertEquals(new CompositeFunction(new CosFunction(), new LinkedListTabulatedFunction(xValues, yValues)).apply(1.), 1.6322, DELTA);
        assertEquals(new CompositeFunction(new SqrFunction(), new ArrayTabulatedFunction(new CosFunction(), 1.7, 8., 3)).apply(1.), -0.1879, DELTA);
    }
}