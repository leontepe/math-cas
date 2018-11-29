package com.leontepe;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
        assertEquals(new Expression("3 - (6 / 2)").evaluate(), 0);
        assertEquals(new Expression("9 + (3 * 8)").evaluate(), 33);
        assertEquals(new Expression("2 + 8/3").evaluate(), 4);
        assertEquals(new Expression("3 - 8 / 2").evaluate(), -1);
    }

    // @Test
    public void testUnaryOperatorExpressions() {
        assertEquals(new Expression("-4+3").evaluate(), "-1");
        assertEquals(new Expression("-(4+3)").evaluate(), "-7");
    }

    @Test
    public void testExpressionElementSlicing() {
        Expression ex1 = new Expression("-512 * 32");
        List<ExpressionElement> elements1 = new ArrayList<ExpressionElement>();
        elements1.add(Number.get("-512"));
        elements1.add(Operator.get('*'));
        elements1.add(Number.get("32"));
        assertEquals(ex1.getExpressionElements(), elements1);

        Expression ex2 = new Expression("(-3.55 + 2.13) / 5481");
        List<ExpressionElement> elements2 = new ArrayList<ExpressionElement>();
        elements2.add(Bracket.get('('));
        elements2.add(Number.get("-3.55"));
        elements2.add(Operator.get('+'));
        elements2.add(Number.get("2.13"));
        elements2.add(Bracket.get(')'));
        elements2.add(Operator.get('/'));
        elements2.add(Number.get("5481"));
        assertEquals(ex2.getExpressionElements(), elements2);

        Expression ex3 = new Expression("-3");
        List<ExpressionElement> elements3 = new ArrayList<ExpressionElement>();
        elements3.add(Number.get("-3"));
        assertEquals(ex3.getExpressionElements(), elements3);
    }
}