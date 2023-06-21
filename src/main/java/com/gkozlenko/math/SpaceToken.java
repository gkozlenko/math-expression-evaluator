package com.gkozlenko.math;

final class SpaceToken extends Token {

    private static final String VALUE = " ";

    SpaceToken(int position) {
        super(position, VALUE);
    }

}
