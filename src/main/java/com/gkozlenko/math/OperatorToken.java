package com.gkozlenko.math;

abstract class OperatorToken extends Token {

    static final int HIGHEST_PRIORITY = 1;

    OperatorToken(int position, String token) {
        super(position, token);
    }

    abstract int getPriority();

}
