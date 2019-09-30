package com.gkozlenko.math;

@SuppressWarnings("WeakerAccess")
public class UndefinedParameterException extends ExpressionException {

    public UndefinedParameterException(ParameterToken token) {
        super("Undefined parameter " + token.getToken() + " at position " + token.getPosition());
    }

}
