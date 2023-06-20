package com.gkozlenko.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpressionTest {

    @Test
    public void testToString01() throws ExpressionException {
        assertEquals("1 + 1", Expression.parse("1+1").toString());
    }

    @Test
    public void testToString02() throws ExpressionException {
        assertEquals("((10 - 2) * (3 + 5))", Expression.parse("(( 10-2 ) *(3+5))").toString());
    }

    @Test
    public void testToString03() throws ExpressionException {
        assertEquals(":param / :param", Expression.parse(":param/:param").toString());
    }

    @Test
    public void testToString04() throws ExpressionException {
        assertEquals("15.5", Expression.parse("15.5").toString());
    }

    @Test
    public void testToString05() throws ExpressionException {
        assertEquals("(12.2 - 17) + (13 * 2)", Expression.parse("(12.2 - 17) + (13 * 2)").toString());
    }

    @Test
    public void testToString06() throws ExpressionException {
        assertEquals("(-1 - -1) - 1", Expression.parse("(-1-  -1)  -1").toString());
    }

    @Test
    public void testParameters01() throws ExpressionException {
        Expression expression = Expression.parse("1 + :param");

        expression.setParameter(":param", 10);
        assertEquals(11L, expression.calculate());

        expression.setParameter(":param", -1);
        assertEquals(0L, expression.calculate());
    }

    @Test
    public void testParameters02() throws ExpressionException {
        Expression expression = Expression.parse(":param + :param * :param");

        expression.setParameter(":param", 10);
        assertEquals(110L, expression.calculate());

        expression.setParameter(":param", -10);
        assertEquals(90L, expression.calculate());
    }

    @Test
    public void testParameters03() throws ExpressionException {
        Expression expression = Expression.parse(":x + :y - :z");

        expression.setParameter(":x", 1);
        expression.setParameter(":y", 2);
        expression.setParameter(":z", 3);
        assertEquals(0L, expression.calculate());

        expression.setParameter(":z", -1.4);
        assertEquals(4.4D, expression.calculate().doubleValue(), 0.0001D);
    }

    @Test
    public void testParameters04() throws ExpressionException {
        Expression expression = Expression.parse(":x + :y - :z");

        expression.setParameter(":x", 1);
        expression.setParameter(":z", 3);

        Exception exception = assertThrows(UndefinedParameterException.class, expression::calculate);
        assertEquals("Undefined parameter :y at position 5", exception.getMessage());
    }

    @Test
    public void testParameters05() throws ExpressionException {
        assertEquals(12.5D,
            Expression.parse(":param").setParameter(":param", 12.5).calculate().doubleValue(),
            0.0001D);
    }

    @Test
    public void testParameters06() throws ExpressionException {
        Expression expression = Expression.parse("(10 - :x) * (5 + :y)");

        expression.setParameter(":x", 2).setParameter(":y", 3);
        assertEquals(64L, expression.calculate());

        expression.setParameter(":x", 4.5).setParameter(":y", -1.5);
        assertEquals(19.25D, expression.calculate().doubleValue(), 0.0001D);
    }

    @Test
    public void testCalculate01() throws ExpressionException {
        assertEquals(2L, Expression.parse("1 + 1").calculate());
    }

    @Test
    public void testCalculate02() throws ExpressionException {
        assertEquals(56L, Expression.parse("(10 - 2) * (3 + 4)").calculate());
    }

    @Test
    public void testCalculate03() throws ExpressionException {
        assertEquals(8L, Expression.parse("10 - 2 * 3 + 4").calculate());
    }

    @Test
    public void testCalculate04() throws ExpressionException {
        assertEquals(12L, Expression.parse("10 + 2 * 3 - 4").calculate());
    }

    @Test
    public void testCalculate05() throws ExpressionException {
        assertEquals(11L, Expression.parse("10 / 2 + 3 * 2").calculate());
    }

    @Test
    public void testCalculate06() throws ExpressionException {
        assertEquals(21.5D,
            Expression.parse("10 * 2 + 3 / 2").calculate().doubleValue(),
            0.0001D);
    }

    @Test
    public void testCalculate07() throws ExpressionException {
        assertEquals(1L, Expression.parse("1 + 1 - 1").calculate());
    }

    @Test
    public void testCalculate08() throws ExpressionException {
        assertEquals(1L, Expression.parse("1 - 1 + 1").calculate());
    }

    @Test
    public void testCalculate09() throws ExpressionException {
        assertEquals(2L, Expression.parse("2 * 2 / 2").calculate());
    }

    @Test
    public void testCalculate10() throws ExpressionException {
        assertEquals(2L, Expression.parse("2 / 2 * 2").calculate());
    }

    @Test
    public void testCalculate11() throws ExpressionException {
        assertEquals(1L, Expression.parse("4 - 1 - 1 - 1").calculate());
    }

    @Test
    public void testCalculate12() throws ExpressionException {
        assertEquals(2L, Expression.parse("16 / 2 / 2 / 2").calculate());
    }

    @Test
    public void testCalculate13() throws ExpressionException {
        assertEquals(2L, Expression.parse("4 - 1 - 1 - 1 + 1").calculate());
    }

    @Test
    public void testCalculate14() throws ExpressionException {
        assertEquals(4L, Expression.parse("16 / 2 / 2 / 2 * 2").calculate());
    }

    @Test
    public void testCalculate15() throws ExpressionException {
        assertEquals(13L, Expression.parse("10 + 16 / 2 / 2 / 2 * 2 - 6 / 2 / 3").calculate());
    }

    @Test
    public void testCalculate16() throws ExpressionException {
        assertEquals(5.5D,
            Expression.parse("(10 + 16) / 2 / 2 / 2 * 2 - 6 / 2 / 3").calculate().doubleValue(),
            0.0001D);
    }

    @Test
    public void testCalculate17() throws ExpressionException {
        assertEquals(-2.1666D,
            Expression.parse("(10 + 16) / 2 / 2 / 2 * (2 - 6) / 2 / 3").calculate().doubleValue(),
            0.0001D);
    }

    @Test
    public void testCalculate18() throws ExpressionException {
        assertEquals(Double.POSITIVE_INFINITY, Expression.parse("1 / 0 + 3").calculate());
    }

    @Test
    public void testCalculate19() throws ExpressionException {
        assertEquals(Double.NEGATIVE_INFINITY, Expression.parse("-1 / 0 + 3").calculate());
    }

    @Test
    public void testCalculate20() throws ExpressionException {
        assertEquals(Double.POSITIVE_INFINITY, Expression.parse("100 / (2 - 2)").calculate());
    }

}
