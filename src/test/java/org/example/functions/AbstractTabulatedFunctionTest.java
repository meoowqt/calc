package org.example.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractTabulatedFunctionTest {

    private final static double DELTA = 0.000_1;

    private final MockTabulatedFunction function = new MockTabulatedFunction();

    @Test
    public void interpolate() {
        assertEquals(AbstractTabulatedFunction.interpolate(0.7, function.getX(0), function.getX(1), function.getY(0), function.getY(1)), 2.3939, DELTA);
        assertEquals(AbstractTabulatedFunction.interpolate(2.5, 0.5, 3.5, 1.0, 2.0), 1.6666, DELTA);
        assertEquals(AbstractTabulatedFunction.interpolate(3.6, -2.4, 4.2, -1.1, 5.7), 5.0818, DELTA);
        assertEquals(AbstractTabulatedFunction.interpolate(-1.4, -3.7, 7.6, -5.9, 8.6), -2.9486, DELTA);
    }

    @Test
    public void apply() {
        assertEquals(function.apply(2.9), 5.4, DELTA);
        assertEquals(function.apply(5.7), 7.0909, DELTA);
        assertEquals(function.apply(-4.6), -2.5848, DELTA);
    }


}