package com.gkozlenko.math;

final class NumberToken extends ValueToken {

    private final Number number;

    NumberToken(int position, Number number) {
        super(position, String.valueOf(number));
        this.number = number;
    }

    Number getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

}
