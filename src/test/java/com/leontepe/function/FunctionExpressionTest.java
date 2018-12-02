package com.leontepe.function;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leontepe.expression.*;

public class FunctionExpressionTest {

    @Test
    public void testFunctionExpressionParsing() {
        Function f1 = new Function("f(x) = 20x - 2");
        assertEquals(f1.getExpression(), new Expression("20x-2"));

        Function f2 = new Function("g (x )=   -40 x + 3 x");
        assertEquals(f2.getExpression(), new Expression("-40x + 3x"));

        Function f3 = new Function("z(x, y) = - 7 x + 2y - 10x + 0");
        assertEquals(f3.getExpression(), new Expression("-7x + 2y - 10x + 0"));
    }
}