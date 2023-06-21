package com.gkozlenko.math;

final class CloseGroupToken extends GroupToken {

    private static final String VALUE = ")";

    CloseGroupToken(int position) {
        super(position, VALUE);
    }

}
