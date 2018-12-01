package com.leontepe;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ExpressionTest {

    @Test
    public void testExpressionElementSlicing() {
        Expression ex1 = new Expression("-512 * 32");
        List<ExpressionElement> elements1 = new ArrayList<ExpressionElement>();
        elements1.add(Number.get("-512"));
        elements1.add(Operator.get("*"));
        elements1.add(Number.get("32"));
        assertEquals(ex1.getExpressionElements(), elements1);

        Expression ex2 = new Expression("(-3.55 + 2.13) / 5481");
        List<ExpressionElement> elements2 = new ArrayList<ExpressionElement>();
        elements2.add(Bracket.get("("));
        elements2.add(Number.get("-3.55"));
        elements2.add(Operator.get("+"));
        elements2.add(Number.get("2.13"));
        elements2.add(Bracket.get(")"));
        elements2.add(Operator.get("/"));
        elements2.add(Number.get("5481"));
        assertEquals(ex2.getExpressionElements(), elements2);

        Expression ex3 = new Expression("-3 + ((3 - 5) / 2)");
        List<ExpressionElement> elements3 = new ArrayList<ExpressionElement>();
        elements3.add(Number.get("-3"));
        elements3.add(Operator.get("+"));
        elements3.add(Bracket.get("("));
        elements3.add(Bracket.get("("));
        elements3.add(Number.get("3"));
        elements3.add(Operator.get("-"));
        elements3.add(Number.get("5"));
        elements3.add(Bracket.get(")"));
        elements3.add(Operator.get("/"));
        elements3.add(Number.get("2"));
        elements3.add(Bracket.get(")"));
        assertEquals(ex3.getExpressionElements(), elements3);
    }
    
    /**
     * Test infix-to-postfix conversion for simple expressions (no parantheses and single digit integers).
     */
    @Test
    public void testBasicExpressions() {
        Expression ex1 = new Expression("3 - 4 / 2");
        List<ExpressionElement> elements1 = new ArrayList<ExpressionElement>();
        elements1.add(Number.get("3"));
        elements1.add(Number.get("4"));
        elements1.add(Number.get("2"));
        elements1.add(Operator.get("/"));
        elements1.add(Operator.get("-"));
        assertEquals(ex1.getPostfix(), elements1);

        Expression ex2 = new Expression("3+4+4-2");
        List<ExpressionElement> elements2 = new ArrayList<ExpressionElement>();
        elements2.add(Number.get("3"));
        elements2.add(Number.get("4"));
        elements2.add(Operator.get("+"));
        elements2.add(Number.get("4"));
        elements2.add(Operator.get("+"));
        elements2.add(Number.get("2"));
        elements2.add(Operator.get("-"));
        assertEquals(ex2.getPostfix(), elements2);

        Expression ex3 = new Expression("7/8 - 4*3");
        List<ExpressionElement> elements3 = new ArrayList<ExpressionElement>();
        elements3.add(Number.get("7"));
        elements3.add(Number.get("8"));
        elements3.add(Operator.get("/"));
        elements3.add(Number.get("4"));
        elements3.add(Number.get("3"));
        elements3.add(Operator.get("*"));
        elements3.add(Operator.get("-"));
        assertEquals(ex3.getPostfix(), elements3);

        Expression ex4 = new Expression("2^3^3");
        List<ExpressionElement> elements4 = new ArrayList<ExpressionElement>();
        elements4.add(Number.get("2"));
        elements4.add(Number.get("3"));
        elements4.add(Number.get("3"));
        elements4.add(Operator.get("^"));
        elements4.add(Operator.get("^"));
        assertEquals(ex4.getPostfix(), elements4);

        Expression ex5 = new Expression("-5");
        List<ExpressionElement> elements5 = new ArrayList<ExpressionElement>();
        elements5.add(Number.get("-5"));
        assertEquals(ex5.getPostfix(), elements5);
    }

    @Test
    public void testParanthesesExpressions() {

        Expression ex1 = new Expression("3 + (2 - 3)");
        List<ExpressionElement> elements1 = new ArrayList<ExpressionElement>();
        elements1.add(Number.get("3"));
        elements1.add(Number.get("2"));
        elements1.add(Number.get("3"));
        elements1.add(Operator.get("-"));
        elements1.add(Operator.get("+"));
        assertEquals(ex1.getPostfix(), elements1);

        Expression ex2 = new Expression("(9 + 5) * 2");
        List<ExpressionElement> elements2 = new ArrayList<ExpressionElement>();
        elements2.add(Number.get("9"));
        elements2.add(Number.get("5"));
        elements2.add(Operator.get("+"));
        elements2.add(Number.get("2"));
        elements2.add(Operator.get("*"));
        assertEquals(ex2.getPostfix(), elements2);

        Expression ex3 = new Expression("3 ^ (4 / 3)");
        List<ExpressionElement> elements3 = new ArrayList<ExpressionElement>();
        elements3.add(Number.get("3"));
        elements3.add(Number.get("4"));
        elements3.add(Number.get("3"));
        elements3.add(Operator.get("/"));
        elements3.add(Operator.get("^"));
        assertEquals(ex3.getPostfix(), elements3);

        Expression ex4 = new Expression("3 * (2 / (4-3))");
        List<ExpressionElement> elements4 = new ArrayList<ExpressionElement>();
        elements4.add(Number.get("3"));
        elements4.add(Number.get("2"));
        elements4.add(Number.get("4"));
        elements4.add(Number.get("3"));
        elements4.add(Operator.get("-"));
        elements4.add(Operator.get("/"));
        elements4.add(Operator.get("*"));
        assertEquals(ex4.getPostfix(), elements4);

        Expression ex5 = new Expression("2^(3^3)^2");
        List<ExpressionElement> elements5 = new ArrayList<ExpressionElement>();
        elements5.add(Number.get("2"));
        elements5.add(Number.get("3"));
        elements5.add(Number.get("3"));
        elements5.add(Operator.get("^"));
        elements5.add(Number.get("2"));
        elements5.add(Operator.get("^"));
        elements5.add(Operator.get("^"));
        assertEquals(ex5.getPostfix(), elements5);

        Expression ex6 = new Expression("(-5)");
        List<ExpressionElement> elements6 = new ArrayList<ExpressionElement>();
        elements6.add(Number.get("-5"));
        assertEquals(ex6.getPostfix(), elements6);
    }

    @Test
    public void testEvaluateExpressions() {
        assertEquals(new Expression("3 - (6 / 2)").evaluate(), 0);
        assertEquals(new Expression("9 + (3 * 8)").evaluate(), 33);
        assertEquals(new Expression("2 + 8/3").evaluate(), 4);
        assertEquals(new Expression("3 - 8 / 2").evaluate(), -1);
    }

 
}