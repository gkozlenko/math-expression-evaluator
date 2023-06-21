package com.gkozlenko.math;

final class DivisionOperatorToken extends OperatorToken {

    private static final String VALUE = "/";
    private static final int PRIORITY = 1;

    DivisionOperatorToken(int position) {
        super(position, VALUE);
    }

    @Override
    int getPriority() {
        return PRIORITY;
    }

    @Override
    Number calculate(long leftValue, long rightValue) {
        return 1.0D * leftValue / rightValue;
    }

    @Override
    Number calculate(double leftValue, double rightValue) {
        return leftValue / rightValue;
    }

}
