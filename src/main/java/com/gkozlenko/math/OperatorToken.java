package com.gkozlenko.math;

abstract class OperatorToken extends Token {

    OperatorToken(int position, String token) {
        super(position, token);
    }

    abstract int getPriority();

}
