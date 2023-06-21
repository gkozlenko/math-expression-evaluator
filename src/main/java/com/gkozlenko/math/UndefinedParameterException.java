package com.gkozlenko.math;

final public class UndefinedParameterException extends ExpressionException {

    public UndefinedParameterException(ParameterToken token) {
        super("Undefined parameter " + token.getToken() + " at position " + token.getPosition());
    }

}
