package com.gkozlenko.math;

class AdditionOperatorToken extends OperatorToken {

    private static final String VALUE = "+";
    private static final int PRIORITY = 2;

    AdditionOperatorToken(int position) {
        super(position, VALUE);
    }

    @Override
    int getPriority() {
        return PRIORITY;
    }

}
