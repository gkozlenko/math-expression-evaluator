package com.gkozlenko.math;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExpressionCleanerTest {

    @Test
    public void testExpression01() throws ExpressionException {
        String expression = "10 + 2 * 3.5";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionCleaner.clean(tokens);

        // Validate number of tokens
        assertEquals(5, tokens.size());

        // Validate tokens themselves
        assertTrue(tokens.get(0) instanceof NumberToken);
        assertEquals("10", tokens.get(0).getToken());
        assertEquals(0, tokens.get(0).getPosition());

        assertTrue(tokens.get(1) instanceof AdditionOperatorToken);
        assertEquals("+", tokens.get(1).getToken());
        assertEquals(3, tokens.get(1).getPosition());

        assertTrue(tokens.get(2) instanceof NumberToken);
        assertEquals("2", tokens.get(2).getToken());
        assertEquals(5, tokens.get(2).getPosition());

        assertTrue(tokens.get(3) instanceof MultiplicationOperatorToken);
        assertEquals("*", tokens.get(3).getToken());
        assertEquals(7, tokens.get(3).getPosition());

        assertTrue(tokens.get(4) instanceof NumberToken);
        assertEquals("3.5", tokens.get(4).getToken());
        assertEquals(9, tokens.get(4).getPosition());
    }

    @Test
    public void testExpression02() throws ExpressionException {
        String expression = "(10 + 2 * 3.5)";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionCleaner.clean(tokens);

        // Validate number of tokens
        assertEquals(5, tokens.size());

        // Validate tokens themselves
        assertTrue(tokens.get(0) instanceof NumberToken);
        assertEquals("10", tokens.get(0).getToken());
        assertEquals(1, tokens.get(0).getPosition());

        assertTrue(tokens.get(1) instanceof AdditionOperatorToken);
        assertEquals("+", tokens.get(1).getToken());
        assertEquals(4, tokens.get(1).getPosition());

        assertTrue(tokens.get(2) instanceof NumberToken);
        assertEquals("2", tokens.get(2).getToken());
        assertEquals(6, tokens.get(2).getPosition());

        assertTrue(tokens.get(3) instanceof MultiplicationOperatorToken);
        assertEquals("*", tokens.get(3).getToken());
        assertEquals(8, tokens.get(3).getPosition());

        assertTrue(tokens.get(4) instanceof NumberToken);
        assertEquals("3.5", tokens.get(4).getToken());
        assertEquals(10, tokens.get(4).getPosition());
    }

    @Test
    public void testExpression03() throws ExpressionException {
        String expression = "(10 + ((2 * 3.5)))";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionCleaner.clean(tokens);

        // Validate number of tokens
        assertEquals(7, tokens.size());

        // Validate tokens themselves
        assertTrue(tokens.get(0) instanceof NumberToken);
        assertEquals("10", tokens.get(0).getToken());
        assertEquals(1, tokens.get(0).getPosition());

        assertTrue(tokens.get(1) instanceof AdditionOperatorToken);
        assertEquals("+", tokens.get(1).getToken());
        assertEquals(4, tokens.get(1).getPosition());

        assertTrue(tokens.get(2) instanceof OpenGroupToken);
        assertEquals("(", tokens.get(2).getToken());
        assertEquals(6, tokens.get(2).getPosition());

        assertTrue(tokens.get(3) instanceof NumberToken);
        assertEquals("2", tokens.get(3).getToken());
        assertEquals(8, tokens.get(3).getPosition());

        assertTrue(tokens.get(4) instanceof MultiplicationOperatorToken);
        assertEquals("*", tokens.get(4).getToken());
        assertEquals(10, tokens.get(4).getPosition());

        assertTrue(tokens.get(5) instanceof NumberToken);
        assertEquals("3.5", tokens.get(5).getToken());
        assertEquals(12, tokens.get(5).getPosition());

        assertTrue(tokens.get(6) instanceof CloseGroupToken);
        assertEquals(")", tokens.get(6).getToken());
        assertEquals(16, tokens.get(6).getPosition());
    }

    @Test
    public void testExpression04() throws ExpressionException {
        String expression = "((10 + 2 * (3.5)))";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionCleaner.clean(tokens);

        // Validate number of tokens
        assertEquals(5, tokens.size());

        // Validate tokens themselves
        assertTrue(tokens.get(0) instanceof NumberToken);
        assertEquals("10", tokens.get(0).getToken());
        assertEquals(2, tokens.get(0).getPosition());

        assertTrue(tokens.get(1) instanceof AdditionOperatorToken);
        assertEquals("+", tokens.get(1).getToken());
        assertEquals(5, tokens.get(1).getPosition());

        assertTrue(tokens.get(2) instanceof NumberToken);
        assertEquals("2", tokens.get(2).getToken());
        assertEquals(7, tokens.get(2).getPosition());

        assertTrue(tokens.get(3) instanceof MultiplicationOperatorToken);
        assertEquals("*", tokens.get(3).getToken());
        assertEquals(9, tokens.get(3).getPosition());

        assertTrue(tokens.get(4) instanceof NumberToken);
        assertEquals("3.5", tokens.get(4).getToken());
        assertEquals(12, tokens.get(4).getPosition());
    }

    @Test
    public void testExpression05() throws ExpressionException {
        String expression = "(((0)))";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionCleaner.clean(tokens);

        // Validate number of tokens
        assertEquals(1, tokens.size());

        assertTrue(tokens.get(0) instanceof NumberToken);
        assertEquals("0", tokens.get(0).getToken());
        assertEquals(3, tokens.get(0).getPosition());
    }

    @Test
    public void testExpression06() throws ExpressionException {
        String expression = "(10 + 2) * (3.5 - (12 - 7))";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionCleaner.clean(tokens);

        // Validate number of tokens
        assertEquals(15, tokens.size());

        // Validate tokens themselves
        assertTrue(tokens.get(0) instanceof OpenGroupToken);
        assertEquals("(", tokens.get(0).getToken());
        assertEquals(0, tokens.get(0).getPosition());

        assertTrue(tokens.get(1) instanceof NumberToken);
        assertEquals("10", tokens.get(1).getToken());
        assertEquals(1, tokens.get(1).getPosition());

        assertTrue(tokens.get(2) instanceof AdditionOperatorToken);
        assertEquals("+", tokens.get(2).getToken());
        assertEquals(4, tokens.get(2).getPosition());

        assertTrue(tokens.get(3) instanceof NumberToken);
        assertEquals("2", tokens.get(3).getToken());
        assertEquals(6, tokens.get(3).getPosition());

        assertTrue(tokens.get(4) instanceof CloseGroupToken);
        assertEquals(")", tokens.get(4).getToken());
        assertEquals(7, tokens.get(4).getPosition());

        assertTrue(tokens.get(5) instanceof MultiplicationOperatorToken);
        assertEquals("*", tokens.get(5).getToken());
        assertEquals(9, tokens.get(5).getPosition());

        assertTrue(tokens.get(6) instanceof OpenGroupToken);
        assertEquals("(", tokens.get(6).getToken());
        assertEquals(11, tokens.get(6).getPosition());

        assertTrue(tokens.get(7) instanceof NumberToken);
        assertEquals("3.5", tokens.get(7).getToken());
        assertEquals(12, tokens.get(7).getPosition());

        assertTrue(tokens.get(8) instanceof SubtractionOperatorToken);
        assertEquals("-", tokens.get(8).getToken());
        assertEquals(16, tokens.get(8).getPosition());

        assertTrue(tokens.get(9) instanceof OpenGroupToken);
        assertEquals("(", tokens.get(9).getToken());
        assertEquals(18, tokens.get(9).getPosition());

        assertTrue(tokens.get(10) instanceof NumberToken);
        assertEquals("12", tokens.get(10).getToken());
        assertEquals(19, tokens.get(10).getPosition());

        assertTrue(tokens.get(11) instanceof SubtractionOperatorToken);
        assertEquals("-", tokens.get(11).getToken());
        assertEquals(22, tokens.get(11).getPosition());

        assertTrue(tokens.get(12) instanceof NumberToken);
        assertEquals("7", tokens.get(12).getToken());
        assertEquals(24, tokens.get(12).getPosition());

        assertTrue(tokens.get(13) instanceof CloseGroupToken);
        assertEquals(")", tokens.get(13).getToken());
        assertEquals(25, tokens.get(13).getPosition());

        assertTrue(tokens.get(14) instanceof CloseGroupToken);
        assertEquals(")", tokens.get(14).getToken());
        assertEquals(26, tokens.get(14).getPosition());
    }

    @Test
    public void testExpression07() throws ExpressionException {
        String expression = "(2) * (3.5) + (7)";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionCleaner.clean(tokens);

        // Validate number of tokens
        assertEquals(5, tokens.size());

        // Validate tokens themselves
        assertTrue(tokens.get(0) instanceof NumberToken);
        assertEquals("2", tokens.get(0).getToken());
        assertEquals(1, tokens.get(0).getPosition());

        assertTrue(tokens.get(1) instanceof MultiplicationOperatorToken);
        assertEquals("*", tokens.get(1).getToken());
        assertEquals(4, tokens.get(1).getPosition());

        assertTrue(tokens.get(2) instanceof NumberToken);
        assertEquals("3.5", tokens.get(2).getToken());
        assertEquals(7, tokens.get(2).getPosition());

        assertTrue(tokens.get(3) instanceof AdditionOperatorToken);
        assertEquals("+", tokens.get(3).getToken());
        assertEquals(12, tokens.get(3).getPosition());

        assertTrue(tokens.get(4) instanceof NumberToken);
        assertEquals("7", tokens.get(4).getToken());
        assertEquals(15, tokens.get(4).getPosition());
    }

}
