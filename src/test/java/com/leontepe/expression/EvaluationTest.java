package com.leontepe.expression;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leontepe.expression.Number;

public class EvaluationTest {

    @Test
    public void testEvaluateExpressions() {
        assertEquals(new Expression("3 - (6 / 2)").evaluate(), new Number(0));
        assertEquals(new Expression("9 + (3 * 8)").evaluate(), new Number(33));
        assertEquals(new Expression("2 - (- 8/4)").evaluate(), new Number(4));
        assertEquals(new Expression("3 - 8 / 2").evaluate(), new Number(-1));
        assertEquals(new Expression("-3^2").evaluate(), new Number(-9));
        assertEquals(new Expression("-3+5").evaluate(), new Number(2));
        assertEquals(new Expression("1.5+0.5-4^(1/2)-5").evaluate(), new Number(-5));
        assertEquals(new Expression(".005-((-12+13)^12*3.005)").evaluate(), new Number(-3));
        assertEquals(new Expression("2^10/2^9").evaluate(), new Number(2));
        assertEquals(new Expression("64^(1/4)").evaluate(), new Number(Math.sqrt(8)));
    }
 
}