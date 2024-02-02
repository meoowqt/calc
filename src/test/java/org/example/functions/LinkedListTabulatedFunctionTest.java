package org.example.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionTest {

    public static final double ACCURACY = 0.00001;

    private final MathFunction function = new SqrFunction();

    private final double[] xValues = new double[]{1, 2, 3, 4, 5};
    private final double[] yValues = new double[]{2, 4, 6, 8, 10};

    private LinkedListTabulatedFunction getListOfMathFunc1() {
        return new LinkedListTabulatedFunction(function, 1, 5, 10);
    }

    private LinkedListTabulatedFunction getListOfMathFunc2() {
        return new LinkedListTabulatedFunction(function, -3, 3, 20);
    }

    private LinkedListTabulatedFunction getListOfMathFunc3() {
        return new LinkedListTabulatedFunction(function, 10, 20, 50);
    }

    private LinkedListTabulatedFunction getListOfArray() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    @Test
    public void testAddNode() {
        LinkedListTabulatedFunction listOfArray = getListOfArray();
        listOfArray.addNode(9, 99);
        assertEquals(listOfArray.rightBound(), 9, ACCURACY);
    }

    @Test
    public void testGetCount() {
        LinkedListTabulatedFunction listOfArray = getListOfArray();
        LinkedListTabulatedFunction listOfMathFunc1 = getListOfMathFunc1();
        LinkedListTabulatedFunction listOfMathFunc2 = getListOfMathFunc2();
        LinkedListTabulatedFunction listOfMathFunc3 = getListOfMathFunc3();

        assertEquals(listOfArray.getCount(), 5);
        assertEquals(listOfMathFunc1.getCount(), 10);
        assertEquals(listOfMathFunc2.getCount(), 20);
        assertEquals(listOfMathFunc3.getCount(), 50);
    }

    @Test
    public void testLeftBound() {
        LinkedListTabulatedFunction listOfArray = getListOfArray();
        LinkedListTabulatedFunction listOfMathFunc1 = getListOfMathFunc1();
        LinkedListTabulatedFunction listOfMathFunc2 = getListOfMathFunc2();
        LinkedListTabulatedFunction listOfMathFunc3 = getListOfMathFunc3();

        assertEquals(listOfMathFunc1.leftBound(), 1.0);
        assertEquals(listOfMathFunc2.leftBound(), -3.0);
        assertEquals(listOfMathFunc3.leftBound(), 10.0);
        assertEquals(listOfArray.leftBound(), 1.0);
    }

    @Test
    public void testRightBound() {
        LinkedListTabulatedFunction listOfArray = getListOfArray();
        LinkedListTabulatedFunction listOfMathFunc1 = getListOfMathFunc1();
        LinkedListTabulatedFunction listOfMathFunc2 = getListOfMathFunc2();
        LinkedListTabulatedFunction listOfMathFunc3 = getListOfMathFunc3();

        assertEquals(listOfArray.rightBound(), 5.0);

        assertEquals(listOfMathFunc1.rightBound(), 5.0, ACCURACY);
        assertEquals(listOfMathFunc2.rightBound(), 3.0, ACCURACY);
        assertEquals(listOfMathFunc3.rightBound(), 20.0, ACCURACY);
    }

    @Test
    public void testGetNode() {
        LinkedListTabulatedFunction listOfArray = getListOfArray();

        assertEquals(listOfArray.getNode(2).x, 3, ACCURACY);

    }

    @Test
    public void testGetX() {
        LinkedListTabulatedFunction listOfArray = getListOfArray();
        LinkedListTabulatedFunction listOfMathFunc1 = getListOfMathFunc1();
        LinkedListTabulatedFunction listOfMathFunc2 = getListOfMathFunc2();
        LinkedListTabulatedFunction listOfMathFunc3 = getListOfMathFunc3();

        assertEquals(listOfMathFunc1.getX(0), 1.0);
        assertEquals(listOfMathFunc1.getX(9), 5.0, ACCURACY);
        assertEquals(listOfMathFunc2.getX(0), -3.0);
        assertEquals(listOfMathFunc3.getX(0), 10.0);

        assertEquals(listOfArray.getX(0), 1.0);

    }

    @Test
    public void testGetY() {
        LinkedListTabulatedFunction listOfArray = getListOfArray();
        LinkedListTabulatedFunction listOfMathFunc1 = getListOfMathFunc1();
        LinkedListTabulatedFunction listOfMathFunc2 = getListOfMathFunc2();
        LinkedListTabulatedFunction listOfMathFunc3 = getListOfMathFunc3();

        assertEquals(listOfArray.getY(0), 2, ACCURACY);
        assertEquals(listOfMathFunc1.getY(0), 1, ACCURACY);
        assertEquals(listOfMathFunc2.getY(0), 9, ACCURACY);
        assertEquals(listOfMathFunc3.getY(0), 100, ACCURACY);



    }

    @Test
    public void testSetY() {
        LinkedListTabulatedFunction listOfArray = getListOfArray();

        listOfArray.setY(2, 39);
        assertEquals(listOfArray.getY(2), 39, ACCURACY);


    }

    @Test
    public void testIndexOfX() {
        LinkedListTabulatedFunction listOfArray = getListOfArray();
        LinkedListTabulatedFunction listOfMathFunc1 = getListOfMathFunc1();

        assertEquals(listOfArray.indexOfX(4), 3);
        assertEquals(listOfMathFunc1.indexOfX(3), -1);
        assertEquals(listOfMathFunc1.indexOfX(100), -1);
    }

    @Test
    public void testIndexOfY() {
        LinkedListTabulatedFunction listOfArray = getListOfArray();
        System.out.println(listOfArray);
        assertEquals(listOfArray.indexOfY(4), 1);
        assertEquals(listOfArray.indexOfY(6), 2);
        assertEquals(listOfArray.indexOfY(8), 3);
    }

    @Test
    public void testFloorIndexOfX() {
        LinkedListTabulatedFunction listOfArray = getListOfArray();
        LinkedListTabulatedFunction listOfMathFunc1 = getListOfMathFunc1();

        assertEquals(listOfArray.floorIndexOfX(1.1), 0);
        assertEquals(listOfArray.floorIndexOfX(3.2), 2);
        assertEquals(listOfMathFunc1.floorIndexOfX(1.1), 0);
        assertEquals(listOfMathFunc1.floorIndexOfX(100), 10);


    }

    @Test
    public void testInterpolate() {
        LinkedListTabulatedFunction listOfArray = getListOfArray();

        assertEquals(listOfArray.interpolate(4, 3), 8.0);
        assertEquals(listOfArray.interpolate(3, 2), 6.0);


    }

    @Test
    public void testInsert() {
        LinkedListTabulatedFunction t11 = new LinkedListTabulatedFunction(new double[]{1, 4, 9, 16}, new double[]{1, 16, 81, 256});
        t11.insert(4, 2);
        System.out.println(t11);
        assertEquals(t11.getX(0), 1.0);
        assertEquals(t11.getY(0), 1.0);
        assertEquals(t11.getX(1), 4.0);
        assertEquals(t11.getY(1), 2.0);
        assertEquals(t11.getX(2), 9.0);
        assertEquals(t11.getY(2), 81.0);
        assertEquals(t11.getX(3), 16.0);
        assertEquals(t11.getY(3), 256.0);
        assertEquals(t11.getCount(), 4);
        t11.insert(5, 6);
        System.out.println(t11);
        assertEquals(t11.getX(0), 1.0);
        assertEquals(t11.getY(0), 1.0);
        assertEquals(t11.getX(1), 4.0);
        assertEquals(t11.getY(1), 2.0);
        assertEquals(t11.getX(2), 5.0);
        assertEquals(t11.getY(2), 6.0);
        assertEquals(t11.getX(3), 9.0);
        assertEquals(t11.getY(3), 81.0);
        assertEquals(t11.getX(4), 16.0);
        assertEquals(t11.getY(4), 256.0);
        assertEquals(t11.getCount(), 5);
        LinkedListTabulatedFunction t1 = new LinkedListTabulatedFunction(new double[]{1, 4, 9, 16}, new double[]{1, 16, 81, 256});
        System.out.println(t1);
        t1.insert(0, 10);
        System.out.println(t1);
        t11.insert(5, 6);
        System.out.println(t11);
        assertEquals(t1.getX(0), 0.0);
        assertEquals(t1.getY(0), 10.0);
        assertEquals(t1.getX(1), 1.0);
        assertEquals(t1.getY(1), 1.0);
        assertEquals(t1.getX(2), 4.0);
        assertEquals(t1.getY(2), 16.0);
        assertEquals(t1.getX(3), 9.0);
        assertEquals(t1.getY(3), 81.0);
        assertEquals(t1.getX(4), 16.0);
        assertEquals(t1.getY(4), 256.0);
        assertEquals(t1.getCount(), 5);
        t1.insert(27, 1010);
        System.out.println(t1);
        assertEquals(t1.getX(0), 0.0);
        assertEquals(t1.getY(0), 10.0);
        assertEquals(t1.getX(1), 1.0);
        assertEquals(t1.getY(1), 1.0);
        assertEquals(t1.getX(2), 4.0);
        assertEquals(t1.getY(2), 16.0);
        assertEquals(t1.getX(3), 9.0);
        assertEquals(t1.getY(3), 81.0);
        assertEquals(t1.getX(4), 16.0);
        assertEquals(t1.getY(4), 256.0);
        assertEquals(t1.getX(5), 27.0);
        assertEquals(t1.getY(5), 1010.0);
        assertEquals(t1.getCount(), 6);
    }
}