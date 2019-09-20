package com.gkozlenko.math;

class CloseGroupToken extends GroupToken {

    private static final String VALUE = ")";

    CloseGroupToken(int position) {
        super(position, VALUE);
    }

}
