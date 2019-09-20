package com.gkozlenko.math;

class OpenGroupToken extends GroupToken {

    private static final String VALUE = "(";

    OpenGroupToken(int position) {
        super(position, VALUE);
    }

}
