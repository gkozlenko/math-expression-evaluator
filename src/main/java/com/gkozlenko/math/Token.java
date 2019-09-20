package com.gkozlenko.math;

abstract class Token {

    private int position;

    private String token;

    Token(int position, String token) {
        this.position = position;
        this.token = token;
    }

    public int getPosition() {
        return position;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return token;
    }

}
