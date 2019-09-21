package com.gkozlenko.math;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.LinkedList;

public class ExpressionValidatorTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testBrackets1() throws UnexpectedTokenException {
        String expression = "(10 + 12.5) / (13 - 22.6) * 25";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionValidator.validate(tokens);
    }

    @Test
    public void testBrackets2() throws UnexpectedTokenException {
        String expression = "((12 + 13) / :x) * (17 - :y)";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionValidator.validate(tokens);
    }

    @Test
    public void testBrackets3() throws UnexpectedTokenException {
        exceptionRule.expect(UnexpectedTokenException.class);
        exceptionRule.expectMessage("Unexpected token ) at position 14");

        String expression = "(10 + 12.5) / )13 - 22.6)";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionValidator.validate(tokens);
    }

    @Test
    public void testBrackets4() throws UnexpectedTokenException {
        exceptionRule.expect(UnexpectedTokenException.class);
        exceptionRule.expectMessage("Unexpected token ( at position 0");

        String expression = "(10 + 12.5 / (13 - 22.6)";
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionValidator.validate(tokens);
    }

}
