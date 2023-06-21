package com.gkozlenko.math;

final class OpenGroupToken extends GroupToken {

    private static final String VALUE = "(";

    OpenGroupToken(int position) {
        super(position, VALUE);
    }

}
