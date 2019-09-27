package com.gkozlenko.math;

class AdditionOperatorToken extends OperatorToken {

    private static final String VALUE = "+";

    AdditionOperatorToken(int position) {
        super(position, VALUE);
    }

    @Override
    int getPriority() {
        return HIGHEST_PRIORITY + 1;
    }

}
