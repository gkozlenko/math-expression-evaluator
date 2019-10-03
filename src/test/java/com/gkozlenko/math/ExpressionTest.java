package com.gkozlenko.math;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class ExpressionTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testToString01() throws ExpressionException {
        assertEquals("1 + 1", Expression.compile("1+1").toString());
    }

    @Test
    public void testToString02() throws ExpressionException {
        assertEquals("((10 - 2) * (3 + 5))", Expression.compile("(( 10-2 ) *(3+5))").toString());
    }

    @Test
    public void testToString03() throws ExpressionException {
        assertEquals(":param / :param", Expression.compile(":param/:param").toString());
    }

    @Test
    public void testToString04() throws ExpressionException {
        assertEquals("15.5", Expression.compile("15.5").toString());
    }

    @Test
    public void testToString05() throws ExpressionException {
        assertEquals("(12.2 - 17) + (13 * 2)", Expression.compile("(12.2 - 17) + (13 * 2)").toString());
    }

    @Test
    public void testToString06() throws ExpressionException {
        assertEquals("(-1 - -1) - 1", Expression.compile("(-1-  -1)  -1").toString());
    }

    @Test
    public void testParameters01() throws ExpressionException {
        Expression expression = Expression.compile("1 + :param");

        expression.setParameter(":param", 10);
        assertEquals(11L, expression.calculate());

        expression.setParameter(":param", -1);
        assertEquals(0L, expression.calculate());
    }

    @Test
    public void testParameters02() throws ExpressionException {
        Expression expression = Expression.compile(":param + :param * :param");

        expression.setParameter(":param", 10);
        assertEquals(110L, expression.calculate());

        expression.setParameter(":param", -10);
        assertEquals(90L, expression.calculate());
    }

    @Test
    public void testParameters03() throws ExpressionException {
        Expression expression = Expression.compile(":x + :y - :z");

        expression.setParameter(":x", 1);
        expression.setParameter(":y", 2);
        expression.setParameter(":z", 3);
        assertEquals(0L, expression.calculate());

        expression.setParameter(":z", -1.4);
        assertEquals(4.4D, expression.calculate());
    }

    @Test
    public void testParameters04() throws ExpressionException {
        exceptionRule.expect(UndefinedParameterException.class);
        exceptionRule.expectMessage("Undefined parameter :y at position 5");

        Expression expression = Expression.compile(":x + :y - :z");

        expression.setParameter(":x", 1);
        expression.setParameter(":z", 3);
        expression.calculate();
    }

    @Test
    public void testParameters05() throws ExpressionException {
        assertEquals(12.5D, Expression.compile(":param").setParameter(":param", 12.5).calculate());
    }

    @Test
    public void testCalculate01() throws ExpressionException {
        assertEquals(2L, Expression.compile("1 + 1").calculate());
    }

    @Test
    public void testCalculate02() throws ExpressionException {
        assertEquals(56L, Expression.compile("(10 - 2) * (3 + 4)").calculate());
    }

    @Test
    public void testCalculate03() throws ExpressionException {
        assertEquals(8L, Expression.compile("10 - 2 * 3 + 4").calculate());
    }

    @Test
    public void testCalculate04() throws ExpressionException {
        assertEquals(12L, Expression.compile("10 + 2 * 3 - 4").calculate());
    }

    @Test
    public void testCalculate05() throws ExpressionException {
        assertEquals(11L, Expression.compile("10 / 2 + 3 * 2").calculate());
    }

    @Test
    public void testCalculate06() throws ExpressionException {
        assertEquals(21.5D, Expression.compile("10 * 2 + 3 / 2").calculate());
    }

    @Test
    public void testCalculate07() throws ExpressionException {
        assertEquals(1L, Expression.compile("1 + 1 - 1").calculate());
    }

    @Test
    public void testCalculate08() throws ExpressionException {
        assertEquals(1L, Expression.compile("1 - 1 + 1").calculate());
    }

    @Test
    public void testCalculate09() throws ExpressionException {
        assertEquals(2L, Expression.compile("2 * 2 / 2").calculate());
    }

    @Test
    public void testCalculate10() throws ExpressionException {
        assertEquals(2L, Expression.compile("2 / 2 * 2").calculate());
    }

    @Test
    public void testCalculate11() throws ExpressionException {
        assertEquals(1L, Expression.compile("4 - 1 - 1 - 1").calculate());
    }

    @Test
    public void testCalculate12() throws ExpressionException {
        assertEquals(2L, Expression.compile("16 / 2 / 2 / 2").calculate());
    }

    @Test
    public void testCalculate13() throws ExpressionException {
        assertEquals(2L, Expression.compile("4 - 1 - 1 - 1 + 1").calculate());
    }

    @Test
    public void testCalculate14() throws ExpressionException {
        assertEquals(4L, Expression.compile("16 / 2 / 2 / 2 * 2").calculate());
    }

}
