package org.example.functions;

import java.lang.Math;

public class CosFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return Math.cos(x);
    }
}
