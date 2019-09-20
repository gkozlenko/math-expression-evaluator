package com.gkozlenko.math;

class MultiplicationOperatorToken extends OperatorToken {

    private static final String VALUE = "*";

    MultiplicationOperatorToken(int position) {
        super(position, VALUE);
    }

}
