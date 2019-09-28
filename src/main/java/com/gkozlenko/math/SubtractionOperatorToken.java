package com.gkozlenko.math;

class SubtractionOperatorToken extends OperatorToken {

    private static final String VALUE = "-";
    private static final int PRIORITY = 2;

    SubtractionOperatorToken(int position) {
        super(position, VALUE);
    }

    @Override
    int getPriority() {
        return PRIORITY;
    }

}
