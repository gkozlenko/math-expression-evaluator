package com.gkozlenko.math;

abstract class OperatorToken extends Token {

    OperatorToken(int position, String token) {
        super(position, token);
    }

    abstract int getPriority();

    abstract Number calculate(long leftValue, long rightValue);

    abstract Number calculate(double leftValue, double rightValue);

    Number calculate(Number leftValue, Number rightValue) {
        if (leftValue.doubleValue() != leftValue.longValue() || rightValue.doubleValue() != rightValue.longValue()) {
            return calculate(leftValue.doubleValue(), rightValue.doubleValue());
        } else {
            return calculate(leftValue.longValue(), rightValue.longValue());
        }
    }

}
