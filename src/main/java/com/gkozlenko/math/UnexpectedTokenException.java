package com.gkozlenko.math;

final public class UnexpectedTokenException extends ExpressionException {

    public UnexpectedTokenException(int position, String token) {
        super("Unexpected token " + token + " at position " + position);
    }

    public UnexpectedTokenException(Token token) {
        this(token.getPosition(), token.getToken());
    }

}
