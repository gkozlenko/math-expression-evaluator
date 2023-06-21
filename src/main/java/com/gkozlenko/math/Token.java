package com.gkozlenko.math;

abstract class Token {

    private final int position;

    private final String token;

    Token(int position, String token) {
        this.position = position;
        this.token = token;
    }

    int getPosition() {
        return position;
    }

    String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return token;
    }

}
