package com.gkozlenko.math;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.LinkedList;

public class ExpressionValidatorTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

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
        exceptionRule.expect(UnexpectedTokenException.class);
        exceptionRule.expectMessage("Unexpected token ) at position 14");

        String expression = "(10 + 12.5) / )13 - 22.6)";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionValidator.validate(tokens);
    }

    @Test
    public void testExpression04() throws ExpressionException {
        exceptionRule.expect(UnexpectedTokenException.class);
        exceptionRule.expectMessage("Unexpected token ( at position 0");

        String expression = "(10 + 12.5 / (13 - 22.6)";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionValidator.validate(tokens);
    }

    @Test
    public void testExpression05() throws ExpressionException {
        exceptionRule.expect(InvalidExpressionException.class);
        exceptionRule.expectMessage("Invalid expression");

        String expression = "";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionValidator.validate(tokens);
    }

    @Test
    public void testExpression06() throws ExpressionException {
        exceptionRule.expect(UnexpectedTokenException.class);
        exceptionRule.expectMessage("Unexpected token ) at position 1");

        String expression = "() * ()";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionValidator.validate(tokens);
    }

    @Test
    public void testExpression07() throws ExpressionException {
        exceptionRule.expect(UnexpectedTokenException.class);
        exceptionRule.expectMessage("Unexpected token ) at position 2");

        String expression = "(())";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionValidator.validate(tokens);
    }

    @Test
    public void testExpression08() throws ExpressionException {
        exceptionRule.expect(UnexpectedTokenException.class);
        exceptionRule.expectMessage("Unexpected token 43 at position 12");

        String expression = "(28.9 * 21) 43";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionValidator.validate(tokens);
    }

    @Test
    public void testExpression09() throws ExpressionException {
        exceptionRule.expect(UnexpectedTokenException.class);
        exceptionRule.expectMessage("Unexpected token - at position 4");

        String expression = "10 -- 10";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionValidator.validate(tokens);
    }

    @Test
    public void testExpression10() throws ExpressionException {
        exceptionRule.expect(UnexpectedTokenException.class);
        exceptionRule.expectMessage("Unexpected token / at position 17");

        String expression = "(1 + 2 * 3) - 17 /";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionValidator.validate(tokens);
    }

}
