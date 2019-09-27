package com.gkozlenko.math;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExpressionBuilderTest {

    @Test
    public void testExpression01() throws ExpressionException {
        String expression = "10 + 2 * 5";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        Node root = ExpressionBuilder.build(tokens);

        Token token = root.getToken();
        assertTrue(token instanceof MultiplicationOperatorToken);
        assertEquals(7, token.getPosition());

        token = root.getLeftChild().getToken();
        assertTrue(token instanceof AdditionOperatorToken);
        assertEquals(3, token.getPosition());

        token = root.getLeftChild().getLeftChild().getToken();
        assertTrue(token instanceof NumberToken);
        assertEquals(0, token.getPosition());
        assertEquals("10", token.getToken());

        token = root.getLeftChild().getRightChild().getToken();
        assertTrue(token instanceof NumberToken);
        assertEquals(5, token.getPosition());
        assertEquals("2", token.getToken());

        token = root.getRightChild().getToken();
        assertTrue(token instanceof NumberToken);
        assertEquals(9, token.getPosition());
        assertEquals("5", token.getToken());
    }

}
