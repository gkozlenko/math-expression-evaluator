package com.gkozlenko.math;

class SubtractionOperatorToken extends OperatorToken {

    private static final String VALUE = "-";

    SubtractionOperatorToken(int position) {
        super(position, VALUE);
    }

    @Override
    int getPriority() {
        return HIGHEST_PRIORITY + 1;
    }

}
