package org.example.functions;

public class ConstantFunction implements MathFunction {
    private final double CONST;

    public ConstantFunction(double CONST) {
        this.CONST = CONST;
    }

    @Override
    public double apply(double x) {
        return CONST;
    }

    public double getCONST() {
        return CONST;
    }
}
