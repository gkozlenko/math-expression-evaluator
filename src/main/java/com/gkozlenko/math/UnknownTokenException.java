package com.gkozlenko.math;

@SuppressWarnings("WeakerAccess")
public class UnknownTokenException extends ExpressionException {

    public UnknownTokenException(int position, String token) {
        super("Unknown token " + token + " at position " + position);
    }

}
