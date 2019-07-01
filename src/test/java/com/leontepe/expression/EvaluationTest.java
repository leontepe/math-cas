package com.leontepe.expression;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leontepe.expression.Number;

public class EvaluationTest {

    @Test
    public void testBasicExpressions() {
        assertEquals(new Expression("4^3^2").evaluate(), new Number(262144));
        assertEquals(new Expression("4 + ((5/8)^3) / 12.001 * 10^3").evaluate(), new Number(24.343356803599700024997916840263));
        assertEquals(new Expression("3 - 8 / 2").evaluate(), new Number(-1));
        assertEquals(new Expression("1.5+0.5-4^(1/2)-5").evaluate(), new Number(-5));
        assertEquals(new Expression("3 - (6 / 2)").evaluate(), new Number(0));
        assertEquals(new Expression("9 + (3 * 8)").evaluate(), new Number(33));
        assertEquals(new Expression(".005-((-12+13)^12*3.005)").evaluate(), new Number(-3));
        assertEquals(new Expression("2^10/2^9").evaluate(), new Number(2));
        assertEquals(new Expression("64^(1/4)").evaluate(), new Number(Math.sqrt(8)));
        assertEquals(new Expression("2 - (- 8/4)").evaluate(), new Number(4));
        assertEquals(new Expression("-3^2").evaluate(), new Number(-9));
        assertEquals(new Expression("-3+5").evaluate(), new Number(2));
    }

    @Test
    public void testPredefinedFunctionExpressions() {
        assertEquals(new Expression("ln(3)").evaluate(), new Number(1.0986122886681096913952452369225));
        assertEquals(new Expression("lg(10^12)").evaluate(), new Number(12));
        assertEquals(new Expression("lg(lg(10^10))").evaluate(), new Number(1));
        assertEquals(new Expression("sin(cos(2))").evaluate(), new Number(-0.40423915385226577591226940618127));
    }

    @Test
    public void testConstantExpressions() {
        assertEquals(new Expression("e*2 + 14").evaluate(), new Number(19.436563656918090470720574942705));
        assertEquals(new Expression("-e / 2").evaluate(), new Number(-1.3591409142295226176801437356763));
    }

    @Test
    public void testUnaryOperatorExpressions() {
        assertEquals(new Number(10), new Expression("3!+4").evaluate());
        assertEquals(new Number(767), new Expression("-0!+4!*2-(-3!!)").evaluate());
    }
 
}