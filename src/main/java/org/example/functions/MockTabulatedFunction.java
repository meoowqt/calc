package org.example.functions;

public class MockTabulatedFunction extends AbstractTabulatedFunction {
    public final double x0 = 0.6;
    public final double x1 = 3.9;
    public final double y0 = 2.3;
    public final double y1 = 5.4;


    @Override
    protected int floorIndexOfX(double x) {
        if (x == this.x0) return 0;
        return 1;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, 0);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, 1);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public double getX(int index) {
        if (index == 0) {
            return this.x0;
        }
        return this.x1;
    }

    @Override
    public double getY(int index) {
        if (index == 0) {
            return this.y0;
        }
        return this.y1;
    }

    @Override
    public void setY(int index, double value) {

    }

    @Override
    public int indexOfX(double x) {
        if (x == this.x0) {
            return 0;
        }
        return 1;
    }

    @Override
    public int indexOfY(double y) {
        if (y == this.y0) {
            return 0;
        }
        return 1;
    }

    @Override
    public double leftBound() {
        return this.x0;
    }

    @Override
    public double rightBound() {
        return this.x1;
    }
}
