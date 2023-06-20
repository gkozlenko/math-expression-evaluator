package com.gkozlenko.math;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpressionValidatorTest {

    @Test
    public void testExpression01() throws ExpressionException {
        String expression = "(10 + 12.5) / (13 - 22.6) * 25";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionValidator.validate(tokens);
    }

    @Test
    public void testExpression02() throws ExpressionException {
        String expression = "((12 + 13) / :x) * (17 - (:y + 4))";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionValidator.validate(tokens);
    }

    @Test
    public void testExpression03() throws ExpressionException {
        String expression = "(10 + 12.5) / )13 - 22.6)";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);

        Exception exception = assertThrows(
            UnexpectedTokenException.class,
            () -> ExpressionValidator.validate(tokens)
        );
        assertEquals("Unexpected token ) at position 14", exception.getMessage());
    }

    @Test
    public void testExpression04() throws ExpressionException {
        String expression = "(10 + 12.5 / (13 - 22.6)";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);

        Exception exception = assertThrows(
            UnexpectedTokenException.class,
            () -> ExpressionValidator.validate(tokens)
        );
        assertEquals("Unexpected token ( at position 0", exception.getMessage());
    }

    @Test
    public void testExpression05() throws ExpressionException {
        String expression = "";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);

        Exception exception = assertThrows(
            InvalidExpressionException.class,
            () -> ExpressionValidator.validate(tokens)
        );
        assertEquals("Invalid expression", exception.getMessage());
    }

    @Test
    public void testExpression06() throws ExpressionException {
        String expression = "() * ()";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);

        Exception exception = assertThrows(
            UnexpectedTokenException.class,
            () -> ExpressionValidator.validate(tokens)
        );
        assertEquals("Unexpected token ) at position 1", exception.getMessage());
    }

    @Test
    public void testExpression07() throws ExpressionException {
        String expression = "(())";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);

        Exception exception = assertThrows(
            UnexpectedTokenException.class,
            () -> ExpressionValidator.validate(tokens)
        );
        assertEquals("Unexpected token ) at position 2", exception.getMessage());
    }

    @Test
    public void testExpression08() throws ExpressionException {
        String expression = "(28.9 * 21) 43";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);

        Exception exception = assertThrows(
            UnexpectedTokenException.class,
            () -> ExpressionValidator.validate(tokens)
        );
        assertEquals("Unexpected token 43 at position 12", exception.getMessage());
    }

    @Test
    public void testExpression09() throws ExpressionException {
        String expression = "10 -- 10";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);

        Exception exception = assertThrows(
            UnexpectedTokenException.class,
            () -> ExpressionValidator.validate(tokens)
        );
        assertEquals("Unexpected token - at position 4", exception.getMessage());
    }

    @Test
    public void testExpression10() throws ExpressionException {
        String expression = "(1 + 2 * 3) - 17 /";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);

        Exception exception = assertThrows(
            UnexpectedTokenException.class,
            () -> ExpressionValidator.validate(tokens)
        );
        assertEquals("Unexpected token / at position 17", exception.getMessage());
    }

}
