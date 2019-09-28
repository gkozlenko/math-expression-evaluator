package com.gkozlenko.math;

class MultiplicationOperatorToken extends OperatorToken {

    private static final String VALUE = "*";
    private static final int PRIORITY = 1;

    MultiplicationOperatorToken(int position) {
        super(position, VALUE);
    }

    @Override
    int getPriority() {
        return PRIORITY;
    }

}
