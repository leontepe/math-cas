
package com.leontepe.expression;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.leontepe.expression.Number;

public class InfixToPostfixTest {

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
        elements5.add(Number.get("0"));
        elements5.add(Number.get("5"));
        elements5.add(Operator.get("-"));
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
        elements6.add(Number.get("0"));
        elements6.add(Number.get("5"));
        elements6.add(Operator.get("-"));
        assertEquals(ex6.getPostfix(), elements6);
    }

    @Test
    public void testUnaryOperatorExpressions() {
        Expression ex1 = new Expression("-(3+5)");
        List<ExpressionElement> elements1 = new ArrayList<ExpressionElement>();
        elements1.add(Number.get("0"));
        elements1.add(Number.get("3"));
        elements1.add(Number.get("5"));
        elements1.add(Operator.get("+"));
        elements1.add(Operator.get("-"));
        assertEquals(ex1.getPostfix(), elements1);

        Expression ex2 = new Expression("-3+5");
        List<ExpressionElement> elements2 = new ArrayList<ExpressionElement>();
        elements2.add(Number.get("0"));
        elements2.add(Number.get("3"));
        elements2.add(Operator.get("-"));
        elements2.add(Number.get("5"));
        elements2.add(Operator.get("+"));
        assertEquals(ex2.getPostfix(), elements2);

        Expression ex3 = new Expression("-3^5");
        List<ExpressionElement> elements3 = new ArrayList<ExpressionElement>();
        elements3.add(Number.get("0"));
        elements3.add(Number.get("3"));
        elements3.add(Number.get("5"));
        elements3.add(Operator.get("^"));
        elements3.add(Operator.get("-"));
        assertEquals(ex3.getPostfix(), elements3);
    }

}