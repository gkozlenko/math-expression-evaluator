package com.gkozlenko.math;

@SuppressWarnings("WeakerAccess")
public class UnexpectedTokenException extends ExpressionException {

    public UnexpectedTokenException(int position, String token) {
        super("Unexpected token " + token + " at position " + position);
    }

}
