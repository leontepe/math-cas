package com.leontepe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ExpressionTest {
    
    /**
     * Test infix-to-postfix conversion for simple expressions (no parantheses and single digit integers).
     */
    @Test
    public void testBasicExpressions() {
        assertEquals(new Expression("3 - 4 / 2").getPostfix(), "342/-");
        assertEquals(new Expression("3+4+4-2").getPostfix(), "34+4+2-");
        assertEquals(new Expression("7/8 - 4*3").getPostfix(), "78/43*-");
        assertEquals(new Expression("2^3^3").getPostfix(), "233^^");
        assertEquals(new Expression("5").getPostfix(), "5");
    }

    @Test
    public void testParanthesesExpressions() {
        assertEquals(new Expression("3 + (2 - 3)").getPostfix(), "323-+");
        assertEquals(new Expression("9 * (5 + 2)").getPostfix(), "952+*");
        assertEquals(new Expression("3 ^ (4 / 3)").getPostfix(), "343/^");
        assertEquals(new Expression("3 * (2 / (4-3))").getPostfix(), "3243-/*");
        assertEquals(new Expression("(8 / 3) - 3").getPostfix(), "83/3-");
    }

    @Test
    public void testEvaluateExpressions() {
        assertEquals(new Expression("3 - (24 / 2)").evaluate(), -9);
    }
}