package com.gkozlenko.math;

class SubtractionOperatorToken extends OperatorToken {

    private static final String VALUE = "-";

    SubtractionOperatorToken(int position) {
        super(position, VALUE);
    }

}
