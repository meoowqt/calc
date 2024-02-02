package org.example.functions;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable {
    private double[] xValues;
    private double[] yValues;
    private int count;

    ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
        this.count = xValues.length;
    }

    ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        xValues = new double[count];
        yValues = new double[count];
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            xValues[i] = xFrom + i * step;
            yValues[i] = source.apply(xFrom + i * step);
        }
        this.count = count;
    }

    @Override
    protected int floorIndexOfX(double x) {
        for (int i = 0; i + 1 < count; i++) {
            if (xValues[i + 1] > x) {
                return i;
            }
        }
        return count;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, 0);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, count - 2);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
    }


    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        this.yValues[index] = value;
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    public void insert(double x, double y) {
        boolean inArray = false;
        for (int i = 0; i < count; i++) {
            if (this.xValues[i] == x) {
                this.yValues[i] = y;
                inArray = true;
                break;
            }
        }
        if (!inArray) {
            double[] yValues1 = new double[count + 1];
            double[] xValues1 = new double[count + 1];

            if (x < this.xValues[0]) {
                xValues1[0] = x;
                yValues1[0] = y;
                inArray = true;
            }
            for (int i = 0; i < count - 1; i++) {
                if (x >= this.xValues[i]) {
                    xValues1[i] = this.xValues[i];
                    yValues1[i] = this.yValues[i];
                }
                if ((this.xValues[i] < x) & (x < this.xValues[i + 1])) {
                    xValues1[i + 1] = x;
                    yValues1[i + 1] = y;
                    inArray = true;
                }
                if (inArray & x < this.xValues[i]) {
                    xValues1[i + 1] = this.xValues[i];
                    yValues1[i + 1] = this.yValues[i];
                } else if (this.xValues[i] > x) {
                    xValues1[i] = this.xValues[i];
                    yValues1[i] = this.yValues[i];
                }
            }
            if (inArray) {
                xValues1[count] = this.xValues[count - 1];
                yValues1[count] = this.yValues[count - 1];
            } else if (x < this.xValues[count - 1]) {
                xValues1[count - 1] = x;
                yValues1[count - 1] = y;
                xValues1[count] = this.xValues[count - 1];
                yValues1[count] = this.yValues[count - 1];
            } else {
                xValues1[count] = x;
                yValues1[count] = y;
                xValues1[count - 1] = this.xValues[count - 1];
                yValues1[count - 1] = this.yValues[count - 1];
            }
            count++;
            this.yValues = yValues1;
            this.xValues = xValues1;
        }
    }

    @Override
    public void remove(int index) {
        double[] xValues1 = new double[count - 1];
        double[] yValues1 = new double[count - 1];
        System.arraycopy(this.yValues, 0, yValues1, 0, index);
        System.arraycopy(this.yValues, index + 1, yValues1, index, count - index - 1);

        System.arraycopy(this.xValues, 0, xValues1, 0, index);
        System.arraycopy(this.xValues, index + 1, xValues1, index, count - index - 1);
        count--;
        this.xValues = xValues1;
        this.yValues = yValues1;
    }
}
