package org.example.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantFunctionTest {
    private final static double DELTA = 0.000_1;
    ConstantFunction constantFunction1 = new ConstantFunction(100);
    ConstantFunction constantFunction2 = new ConstantFunction(3);
    ConstantFunction constantFunction3 = new ConstantFunction(0.8);
    ConstantFunction constantFunction4 = new ConstantFunction(10);

    @Test
    void apply() {
        assertEquals(constantFunction1.apply(1.), 100, DELTA);
        assertEquals(constantFunction1.apply(10.), 100, DELTA);
        assertEquals(constantFunction2.apply(1.), 3, DELTA);
        assertEquals(constantFunction3.apply(15.), 0.8, DELTA);
        assertEquals(constantFunction4.apply(2.), 10, DELTA);
    }

    @Test
    void getCONST() {
        assertEquals(constantFunction1.getCONST(), 100., DELTA);
        assertEquals(constantFunction2.getCONST(), 3., DELTA);
        assertEquals(constantFunction3.getCONST(), 0.8, DELTA);
        assertEquals(constantFunction4.getCONST(), 10, DELTA);
    }
}