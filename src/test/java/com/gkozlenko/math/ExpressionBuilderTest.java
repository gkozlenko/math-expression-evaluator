package com.gkozlenko.math;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class ExpressionBuilderTest {

    @Test
    public void testExpression01() throws ExpressionException {
        String expression = "10 + 2 * 5";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        Node root = ExpressionBuilder.build(tokens);

        assertNotNull(root);

        Token token = root.getToken();
        assertTrue(token instanceof AdditionOperatorToken);
        assertEquals(3, token.getPosition());

        token = root.getLeftChild().getToken();
        assertTrue(token instanceof NumberToken);
        assertEquals(0, token.getPosition());
        assertEquals("10", token.getToken());

        token = root.getRightChild().getToken();
        assertTrue(token instanceof MultiplicationOperatorToken);
        assertEquals(7, token.getPosition());

        token = root.getRightChild().getLeftChild().getToken();
        assertTrue(token instanceof NumberToken);
        assertEquals(5, token.getPosition());
        assertEquals("2", token.getToken());

        token = root.getRightChild().getRightChild().getToken();
        assertTrue(token instanceof NumberToken);
        assertEquals(9, token.getPosition());
        assertEquals("5", token.getToken());
    }

    @Test
    public void testExpression02() throws ExpressionException {
        String expression = " :param ";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        Node root = ExpressionBuilder.build(tokens);

        assertNotNull(root);

        Token token = root.getToken();
        assertTrue(token instanceof ParameterToken);
        assertEquals(1, token.getPosition());
        assertEquals(":param", token.getToken());

        assertNull(root.getLeftChild());
        assertNull(root.getRightChild());
    }

    @Test
    public void testExpression03() throws ExpressionException {
        String expression = "(10 + 2) * 5";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        Node root = ExpressionBuilder.build(tokens);

        assertNotNull(root);

        Token token = root.getToken();
        assertTrue(token instanceof MultiplicationOperatorToken);
        assertEquals(9, token.getPosition());

        token = root.getLeftChild().getToken();
        assertTrue(token instanceof AdditionOperatorToken);
        assertEquals(4, token.getPosition());

        token = root.getLeftChild().getLeftChild().getToken();
        assertTrue(token instanceof NumberToken);
        assertEquals(1, token.getPosition());
        assertEquals("10", token.getToken());

        token = root.getLeftChild().getRightChild().getToken();
        assertTrue(token instanceof NumberToken);
        assertEquals(6, token.getPosition());
        assertEquals("2", token.getToken());

        token = root.getRightChild().getToken();
        assertTrue(token instanceof NumberToken);
        assertEquals(11, token.getPosition());
        assertEquals("5", token.getToken());
    }

}
