package com.gkozlenko.math;

class NumberToken extends ValueToken {

    private Number number;

    NumberToken(int position, Number number) {
        super(position, "" + number);
        this.number = number;
    }

    public Number getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "" + number;
    }

}
