package com.gkozlenko.math;

final class SubtractionOperatorToken extends OperatorToken {

    private static final String VALUE = "-";
    private static final int PRIORITY = 3;

    SubtractionOperatorToken(int position) {
        super(position, VALUE);
    }

    @Override
    int getPriority() {
        return PRIORITY;
    }

    @Override
    Number calculate(long leftValue, long rightValue) {
        return leftValue - rightValue;
    }

    @Override
    Number calculate(double leftValue, double rightValue) {
        return leftValue - rightValue;
    }

}
