package org.example.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionTest {
    private final static double DELTA = 0.000_1;
    private final double[] xValues = new double[]{1., 3.5, 5., 7};
    private final double[] yValues = new double[]{2., 4., 6.9, 8.};

    private final MathFunction sqr = new SqrFunction();

    @Test
    void floorIndexOfX() {
        assertEquals(new ArrayTabulatedFunction(xValues, yValues).floorIndexOfX(3), 0, DELTA);
        assertEquals(new ArrayTabulatedFunction(new SqrFunction(), 1, 4, 5).floorIndexOfX(7), 5, DELTA);
        assertEquals(new ArrayTabulatedFunction(xValues, yValues).floorIndexOfX(8), 4, DELTA);
    }

    @Test
    void extrapolateLeft() {
        assertEquals(new ArrayTabulatedFunction(xValues, yValues).extrapolateLeft(3), 3.6, DELTA);
        assertEquals(new ArrayTabulatedFunction(new SqrFunction(), 1, 4, 5).extrapolateLeft(7), 17.5, DELTA);
        assertEquals(new ArrayTabulatedFunction(xValues, yValues).extrapolateLeft(8), 7.6, DELTA);
    }

    @Test
    void extrapolateRight() {
        assertEquals(new ArrayTabulatedFunction(xValues, yValues).extrapolateRight(7), 8, DELTA);
        assertEquals(new ArrayTabulatedFunction(new SqrFunction(), 1, 4, 10).extrapolateRight(7), 39, DELTA);
        assertEquals(new ArrayTabulatedFunction(xValues, yValues).extrapolateRight(1.4), 4.92, DELTA);
    }

    @Test
    void interpolate() {
        assertEquals(new ArrayTabulatedFunction(xValues, yValues).interpolate(5.34, 2), 7.087, DELTA);
        assertEquals(new ArrayTabulatedFunction(new SqrFunction(), 1, 4, 10).interpolate(3, 2), 7.6666, DELTA);
        assertEquals(new ArrayTabulatedFunction(new CosFunction(), 1.7, 8., 3).interpolate(6.2, 1), 0.01602, DELTA);
    }

    @Test
    void getCount() {
        assertEquals(new ArrayTabulatedFunction(xValues, yValues).getCount(), 4, DELTA);
        assertEquals(new ArrayTabulatedFunction(new SqrFunction(), 1, 4, 10).getCount(), 10, DELTA);
        assertEquals(new ArrayTabulatedFunction(new CosFunction(), 1.7, 8., 3).getCount(), 3, DELTA);
    }

    @Test
    void getX() {
        assertEquals(new ArrayTabulatedFunction(xValues, yValues).getX(1), 3.5, DELTA);
        assertEquals(new ArrayTabulatedFunction(new SqrFunction(), 1, 4, 10).getX(5), 2.6666, DELTA);
        assertEquals(new ArrayTabulatedFunction(new CosFunction(), 1.7, 8., 3).getX(2), 8, DELTA);
    }

    @Test
    void getY() {
        assertEquals(new ArrayTabulatedFunction(xValues, yValues).getY(1), 4, DELTA);
        assertEquals(new ArrayTabulatedFunction(new SqrFunction(), 1, 4, 10).getY(7), 11.1111, DELTA);
        assertEquals(new ArrayTabulatedFunction(new CosFunction(), 1.7, 8., 3).getY(1), 0.1371, DELTA);
    }

    @Test
    void setY() {
        ArrayTabulatedFunction f1 = new ArrayTabulatedFunction(xValues, yValues);
        ArrayTabulatedFunction f2 = new ArrayTabulatedFunction(new SqrFunction(), 1, 4, 10);
        ArrayTabulatedFunction f3 = new ArrayTabulatedFunction(new CosFunction(), 1.7, 8., 3);

        f1.setY(3, 5);
        f2.setY(7, 0.9);
        f3.setY(2, 2.89);

        assertEquals(f1.getY(3), 5, DELTA);
        assertEquals(f2.getY(7), 0.9, DELTA);
        assertEquals(f3.getY(2), 2.89, DELTA);
    }

    @Test
    void indexOfX() {
        assertEquals(new ArrayTabulatedFunction(xValues, yValues).indexOfX(1), 0, DELTA);
        assertEquals(new ArrayTabulatedFunction(new SqrFunction(), 1, 4, 10).indexOfX(7), -1., DELTA);
        assertEquals(new ArrayTabulatedFunction(new CosFunction(), 1.7, 8., 3).indexOfX(1), -1., DELTA);
    }

    @Test
    void indexOfY() {
        assertEquals(new ArrayTabulatedFunction(xValues, yValues).indexOfY(1), -1., DELTA);
        assertEquals(new ArrayTabulatedFunction(new SqrFunction(), 1, 4, 10).indexOfY(4), 3., DELTA);
        assertEquals(new ArrayTabulatedFunction(new CosFunction(), 1.7, 8., 3).indexOfY(1), -1., DELTA);
    }

    @Test
    void leftBound() {
        assertEquals(new ArrayTabulatedFunction(xValues, yValues).leftBound(), 1, DELTA);
        assertEquals(new ArrayTabulatedFunction(new SqrFunction(), 1, 4, 10).leftBound(), 1, DELTA);
        assertEquals(new ArrayTabulatedFunction(new CosFunction(), 1.7, 8., 3).leftBound(), 1.7, DELTA);
    }

    @Test
    void rightBound() {
        assertEquals(new ArrayTabulatedFunction(xValues, yValues).rightBound(), 7, DELTA);
        assertEquals(new ArrayTabulatedFunction(new SqrFunction(), 1, 4, 10).rightBound(), 4, DELTA);
        assertEquals(new ArrayTabulatedFunction(new CosFunction(), 1.7, 8., 3).rightBound(), 8, DELTA);
    }
}