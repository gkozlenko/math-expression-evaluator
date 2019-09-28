package com.gkozlenko.math;

class DivisionOperatorToken extends OperatorToken {

    private static final String VALUE = "/";
    private static final int PRIORITY = 1;

    DivisionOperatorToken(int position) {
        super(position, VALUE);
    }

    @Override
    int getPriority() {
        return PRIORITY;
    }

}
