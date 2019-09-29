package com.gkozlenko.math;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class ExpressionTest {

    @Test
    public void testToString01() throws ExpressionException {
        assertEquals("1 + 1", compileExpression("1+1").toString());
    }

    @Test
    public void testToString02() throws ExpressionException {
        assertEquals("((10 - 2) * (3 + 5))", compileExpression("(( 10-2 ) *(3+5))").toString());
    }

    @Test
    public void testToString03() throws ExpressionException {
        assertEquals(":param / :param", compileExpression(":param/:param").toString());
    }

    @Test
    public void testToString04() throws ExpressionException {
        assertEquals("15.5", compileExpression("15.5").toString());
    }

    @Test
    public void testToString05() throws ExpressionException {
        assertEquals("(12.2 - 17) + (13 * 2)", compileExpression("(12.2 - 17) + (13 * 2)").toString());
    }

    private Expression compileExpression(String expression) throws ExpressionException {
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        Node root = ExpressionBuilder.build(tokens);
        return new Expression(tokens, root);
    }

}
