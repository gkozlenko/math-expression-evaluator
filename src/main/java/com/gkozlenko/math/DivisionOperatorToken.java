package com.gkozlenko.math;

class DivisionOperatorToken extends OperatorToken {

    private static final String VALUE = "/";

    DivisionOperatorToken(int position) {
        super(position, VALUE);
    }

    @Override
    int getPriority() {
        return HIGHEST_PRIORITY;
    }

}
