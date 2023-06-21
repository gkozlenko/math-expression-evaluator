package com.gkozlenko.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberTokenTest {

    @Test
    public void testGetNumber01() {
        NumberToken token = new NumberToken(0, 10);

        assertEquals(0, token.getPosition());
        assertEquals(10, token.getNumber());
        assertEquals("10", token.toString());
    }

    @Test
    public void testGetNumber02() {
        NumberToken token = new NumberToken(1, 12.5);

        assertEquals(1, token.getPosition());
        assertEquals(12.5, token.getNumber());
        assertEquals("12.5", token.toString());
    }

}
