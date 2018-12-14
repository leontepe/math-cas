
package com.leontepe.expression;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.leontepe.expression.Number;

public class ElementParsingTest {

    @Test
    public void testExpressionElementSlicing() {
        Expression ex1 = new Expression("-512 * 32");
        List<ExpressionElement> elements1 = new ArrayList<ExpressionElement>();
        elements1.add(Operator.get("-"));
        elements1.add(Number.get("512"));
        elements1.add(Operator.get("*"));
        elements1.add(Number.get("32"));
        assertEquals(ex1.getElements(), elements1);

        Expression ex2 = new Expression("(-3.55 + 2.13) / 5481");
        List<ExpressionElement> elements2 = new ArrayList<ExpressionElement>();
        elements2.add(Paranthesis.get("("));
        elements2.add(Operator.get("-"));
        elements2.add(Number.get("3.55"));
        elements2.add(Operator.get("+"));
        elements2.add(Number.get("2.13"));
        elements2.add(Paranthesis.get(")"));
        elements2.add(Operator.get("/"));
        elements2.add(Number.get("5481"));
        assertEquals(ex2.getElements(), elements2);

        Expression ex3 = new Expression("-3 + ((3 - 5) / 2)");
        List<ExpressionElement> elements3 = new ArrayList<ExpressionElement>();
        elements3.add(Operator.get("-"));
        elements3.add(Number.get("3"));
        elements3.add(Operator.get("+"));
        elements3.add(Paranthesis.get("("));
        elements3.add(Paranthesis.get("("));
        elements3.add(Number.get("3"));
        elements3.add(Operator.get("-"));
        elements3.add(Number.get("5"));
        elements3.add(Paranthesis.get(")"));
        elements3.add(Operator.get("/"));
        elements3.add(Number.get("2"));
        elements3.add(Paranthesis.get(")"));
        assertEquals(ex3.getElements(), elements3);

        Expression ex4 = new Expression("-10 + 32");
        List<ExpressionElement> elements4 = new ArrayList<ExpressionElement>();
        elements4.add(Operator.get("-"));
        elements4.add(Number.get("10"));
        elements4.add(Operator.get("+"));
        elements4.add(Number.get("32"));
        assertEquals(ex4.getElements(), elements4);
    }

    @Test
    public void testImplicitVariableMultiplication() {
        Expression ex1 = new Expression("4x");
        List<ExpressionElement> elements1 = new ArrayList<ExpressionElement>();
        elements1.add(Number.get("4"));
        elements1.add(Operator.get("*"));
        elements1.add(Variable.get("x"));
        assertEquals(ex1.getElements(), elements1);

        Expression ex2 = new Expression("1/4x");
        List<ExpressionElement> elements2 = new ArrayList<ExpressionElement>();
        elements2.add(Number.get("1"));
        elements2.add(Operator.get("/"));
        elements2.add(Number.get("4"));
        elements2.add(Operator.get("*"));
        elements2.add(Variable.get("x"));
        assertEquals(ex2.getElements(), elements2);

        Expression ex3 = new Expression("3/8x^2 - 2x");
        List<ExpressionElement> elements3 = new ArrayList<ExpressionElement>();
        elements3.add(Number.get("3"));
        elements3.add(Operator.get("/"));
        elements3.add(Number.get("8"));
        elements3.add(Operator.get("*"));
        elements3.add(Variable.get("x"));
        elements3.add(Operator.get("^"));
        elements3.add(Number.get("2"));
        elements3.add(Operator.get("-"));
        elements3.add(Number.get("2"));
        elements3.add(Operator.get("*"));
        elements3.add(Variable.get("x"));
        assertEquals(ex3.getElements(), elements3);

        Expression ex4 = new Expression("-2x");
        List<ExpressionElement> elements4 = new ArrayList<ExpressionElement>();
        elements4.add(Operator.get("-"));
        elements4.add(Number.get("2"));
        elements4.add(Operator.get("*"));
        elements4.add(Variable.get("x"));
        assertEquals(ex4.getElements(), elements4);
    }
    
}