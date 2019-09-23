package com.gkozlenko.math;

@SuppressWarnings("WeakerAccess")
public class InvalidExpressionException extends ExpressionException {

    public InvalidExpressionException() {
        super("Invalid expression");
    }

}
