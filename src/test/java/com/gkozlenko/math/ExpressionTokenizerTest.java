package com.gkozlenko.math;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExpressionTokenizerTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testExpression01() throws ExpressionException {
        String expression = "10 + 2 * 3.5";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);

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
        String expression = "-10--20.5 / :x";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);

        // Validate number of tokens
        assertEquals(5, tokens.size());

        // Validate tokens themselves
        assertTrue(tokens.get(0) instanceof NumberToken);
        assertEquals("-10", tokens.get(0).getToken());
        assertEquals(0, tokens.get(0).getPosition());

        assertTrue(tokens.get(1) instanceof SubtractionOperatorToken);
        assertEquals("-", tokens.get(1).getToken());
        assertEquals(3, tokens.get(1).getPosition());

        assertTrue(tokens.get(2) instanceof NumberToken);
        assertEquals("-20.5", tokens.get(2).getToken());
        assertEquals(4, tokens.get(2).getPosition());

        assertTrue(tokens.get(3) instanceof DivisionOperatorToken);
        assertEquals("/", tokens.get(3).getToken());
        assertEquals(10, tokens.get(3).getPosition());

        assertTrue(tokens.get(4) instanceof ParameterToken);
        assertEquals(":x", tokens.get(4).getToken());
        assertEquals(12, tokens.get(4).getPosition());
    }

    @Test
    public void testExpression03() throws ExpressionException {
        String expression = "(:val + 11)";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);

        // Validate number of tokens
        assertEquals(5, tokens.size());

        // Validate tokens themselves
        assertTrue(tokens.get(0) instanceof OpenGroupToken);
        assertEquals("(", tokens.get(0).getToken());
        assertEquals(0, tokens.get(0).getPosition());

        assertTrue(tokens.get(1) instanceof ParameterToken);
        assertEquals(":val", tokens.get(1).getToken());
        assertEquals(1, tokens.get(1).getPosition());

        assertTrue(tokens.get(2) instanceof AdditionOperatorToken);
        assertEquals("+", tokens.get(2).getToken());
        assertEquals(6, tokens.get(2).getPosition());

        assertTrue(tokens.get(3) instanceof NumberToken);
        assertEquals("11", tokens.get(3).getToken());
        assertEquals(8, tokens.get(3).getPosition());

        assertTrue(tokens.get(4) instanceof CloseGroupToken);
        assertEquals(")", tokens.get(4).getToken());
        assertEquals(10, tokens.get(4).getPosition());
    }

    @Test
    public void testExpression04() throws ExpressionException {
        String expression = "10---10";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);

        // Validate number of tokens
        assertEquals(4, tokens.size());

        // Validate tokens themselves
        assertTrue(tokens.get(0) instanceof NumberToken);
        assertEquals("10", tokens.get(0).getToken());
        assertEquals(0, tokens.get(0).getPosition());

        assertTrue(tokens.get(1) instanceof SubtractionOperatorToken);
        assertEquals("-", tokens.get(1).getToken());
        assertEquals(2, tokens.get(1).getPosition());

        assertTrue(tokens.get(2) instanceof SubtractionOperatorToken);
        assertEquals("-", tokens.get(2).getToken());
        assertEquals(3, tokens.get(2).getPosition());

        assertTrue(tokens.get(3) instanceof NumberToken);
        assertEquals("-10", tokens.get(3).getToken());
        assertEquals(4, tokens.get(3).getPosition());
    }

    @Test
    public void testExpression05() throws ExpressionException {
        String expression = "(10-)--10";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);

        // Validate number of tokens
        assertEquals(6, tokens.size());

        // Validate tokens themselves
        assertTrue(tokens.get(0) instanceof OpenGroupToken);
        assertEquals("(", tokens.get(0).getToken());
        assertEquals(0, tokens.get(0).getPosition());

        assertTrue(tokens.get(1) instanceof NumberToken);
        assertEquals("10", tokens.get(1).getToken());
        assertEquals(1, tokens.get(1).getPosition());

        assertTrue(tokens.get(2) instanceof SubtractionOperatorToken);
        assertEquals("-", tokens.get(2).getToken());
        assertEquals(3, tokens.get(2).getPosition());

        assertTrue(tokens.get(3) instanceof CloseGroupToken);
        assertEquals(")", tokens.get(3).getToken());
        assertEquals(4, tokens.get(3).getPosition());

        assertTrue(tokens.get(4) instanceof SubtractionOperatorToken);
        assertEquals("-", tokens.get(4).getToken());
        assertEquals(5, tokens.get(4).getPosition());

        assertTrue(tokens.get(5) instanceof NumberToken);
        assertEquals("-10", tokens.get(5).getToken());
        assertEquals(6, tokens.get(5).getPosition());
    }

    @Test
    public void testExpression06() throws ExpressionException {
        String expression = "(10--+-10";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);

        // Validate number of tokens
        assertEquals(6, tokens.size());

        // Validate tokens themselves
        assertTrue(tokens.get(0) instanceof OpenGroupToken);
        assertEquals("(", tokens.get(0).getToken());
        assertEquals(0, tokens.get(0).getPosition());

        assertTrue(tokens.get(1) instanceof NumberToken);
        assertEquals("10", tokens.get(1).getToken());
        assertEquals(1, tokens.get(1).getPosition());

        assertTrue(tokens.get(2) instanceof SubtractionOperatorToken);
        assertEquals("-", tokens.get(2).getToken());
        assertEquals(3, tokens.get(2).getPosition());

        assertTrue(tokens.get(3) instanceof SubtractionOperatorToken);
        assertEquals("-", tokens.get(3).getToken());
        assertEquals(4, tokens.get(3).getPosition());

        assertTrue(tokens.get(4) instanceof AdditionOperatorToken);
        assertEquals("+", tokens.get(4).getToken());
        assertEquals(5, tokens.get(4).getPosition());

        assertTrue(tokens.get(5) instanceof NumberToken);
        assertEquals("-10", tokens.get(5).getToken());
        assertEquals(6, tokens.get(5).getPosition());
    }

    @Test
    public void testExpression07() throws ExpressionException {
        exceptionRule.expect(UnexpectedTokenException.class);
        exceptionRule.expectMessage("Unexpected token x at position 12");

        String expression = "(12 + 3) / (x - y)";
        ExpressionTokenizer.tokenize(expression);
    }

    @Test
    public void testExpression08() throws UnexpectedTokenException {
        exceptionRule.expect(UnexpectedTokenException.class);
        exceptionRule.expectMessage("Unexpected token 12i at position 1");

        String expression = "(12i + 3) / (x - y)";
        ExpressionTokenizer.tokenize(expression);
    }

    @Test
    public void testExpression09() throws ExpressionException {
        String expression = "";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);

        // Validate number of tokens
        assertEquals(0, tokens.size());
    }

    @Test
    public void testExpression10() throws ExpressionException {
        String expression = "10-2";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);

        // Validate number of tokens
        assertEquals(3, tokens.size());

        // Validate tokens themselves
        assertTrue(tokens.get(0) instanceof NumberToken);
        assertEquals("10", tokens.get(0).getToken());
        assertEquals(0, tokens.get(0).getPosition());

        assertTrue(tokens.get(1) instanceof SubtractionOperatorToken);
        assertEquals("-", tokens.get(1).getToken());
        assertEquals(2, tokens.get(1).getPosition());

        assertTrue(tokens.get(2) instanceof NumberToken);
        assertEquals("2", tokens.get(2).getToken());
        assertEquals(3, tokens.get(2).getPosition());
    }

    @Test
    public void testExpression11() throws ExpressionException {
        String expression = "(-1-  -1)  -1";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);

        // Validate number of tokens
        assertEquals(7, tokens.size());

        // Validate tokens themselves
        assertTrue(tokens.get(0) instanceof OpenGroupToken);
        assertEquals("(", tokens.get(0).getToken());
        assertEquals(0, tokens.get(0).getPosition());

        assertTrue(tokens.get(1) instanceof NumberToken);
        assertEquals("-1", tokens.get(1).getToken());
        assertEquals(1, tokens.get(1).getPosition());

        assertTrue(tokens.get(2) instanceof SubtractionOperatorToken);
        assertEquals("-", tokens.get(2).getToken());
        assertEquals(3, tokens.get(2).getPosition());

        assertTrue(tokens.get(3) instanceof NumberToken);
        assertEquals("-1", tokens.get(3).getToken());
        assertEquals(6, tokens.get(3).getPosition());

        assertTrue(tokens.get(4) instanceof CloseGroupToken);
        assertEquals(")", tokens.get(4).getToken());
        assertEquals(8, tokens.get(4).getPosition());

        assertTrue(tokens.get(5) instanceof SubtractionOperatorToken);
        assertEquals("-", tokens.get(5).getToken());
        assertEquals(11, tokens.get(5).getPosition());

        assertTrue(tokens.get(6) instanceof NumberToken);
        assertEquals("1", tokens.get(6).getToken());
        assertEquals(12, tokens.get(6).getPosition());
    }

}
