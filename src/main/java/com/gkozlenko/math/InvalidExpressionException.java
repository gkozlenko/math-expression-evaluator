package com.gkozlenko.math;

final public class InvalidExpressionException extends ExpressionException {

    public InvalidExpressionException() {
        super("Invalid expression");
    }

}
