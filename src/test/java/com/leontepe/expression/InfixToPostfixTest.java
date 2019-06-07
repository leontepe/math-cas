
package com.leontepe.expression;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.leontepe.NotationConverter;
import com.leontepe.expression.Number;

public class InfixToPostfixTest {

    /**
     * Test infix to postfix conversion for simple expressions (no parantheses and single digit integers).
     */
    @Test
    public void testBasicExpressions() {
        //Expression ex1 = new Expression("3 - 4 / 2");
        List<ExpressionElement> infix1 = new ArrayList<ExpressionElement>();
        infix1.add(Number.get("3"));
        infix1.add(Operator.get("-"));
        infix1.add(Number.get("4"));
        infix1.add(Operator.get("/"));
        infix1.add(Number.get("2"));
        List<ExpressionElement> postfix1 = new ArrayList<ExpressionElement>();
        postfix1.add(Number.get("3"));
        postfix1.add(Number.get("4"));
        postfix1.add(Number.get("2"));
        postfix1.add(Operator.get("/"));
        postfix1.add(Operator.get("-"));
        assertEquals(NotationConverter.infixToPostfix(infix1), postfix1);

        //Expression ex2 = new Expression("3+4+4-2");
        List<ExpressionElement> infix2 = new ArrayList<ExpressionElement>();
        infix2.add(Number.get("3"));
        infix2.add(Operator.get("+"));
        infix2.add(Number.get("4"));
        infix2.add(Operator.get("+"));
        infix2.add(Number.get("4"));
        infix2.add(Operator.get("-"));
        infix2.add(Number.get("2"));
        List<ExpressionElement> postfix2 = new ArrayList<ExpressionElement>();
        postfix2.add(Number.get("3"));
        postfix2.add(Number.get("4"));
        postfix2.add(Operator.get("+"));
        postfix2.add(Number.get("4"));
        postfix2.add(Operator.get("+"));
        postfix2.add(Number.get("2"));
        postfix2.add(Operator.get("-"));
        assertEquals(NotationConverter.infixToPostfix(infix2), postfix2);

        //Expression ex3 = new Expression("7/8 - 4*3");
        List<ExpressionElement> infix3 = new ArrayList<ExpressionElement>();
        infix3.add(Number.get("7"));
        infix3.add(Operator.get("/"));
        infix3.add(Number.get("8"));
        infix3.add(Operator.get("-"));
        infix3.add(Number.get("4"));
        infix3.add(Operator.get("*"));
        infix3.add(Number.get("3"));
        List<ExpressionElement> postfix3 = new ArrayList<ExpressionElement>();
        postfix3.add(Number.get("7"));
        postfix3.add(Number.get("8"));
        postfix3.add(Operator.get("/"));
        postfix3.add(Number.get("4"));
        postfix3.add(Number.get("3"));
        postfix3.add(Operator.get("*"));
        postfix3.add(Operator.get("-"));
        assertEquals(NotationConverter.infixToPostfix(infix3), postfix3);

        //Expression ex4 = new Expression("2^3^3");
        List<ExpressionElement> infix4 = new ArrayList<ExpressionElement>();
        infix4.add(Number.get("2"));
        infix4.add(Operator.get("^"));
        infix4.add(Number.get("3"));
        infix4.add(Operator.get("^"));
        infix4.add(Number.get("3"));
        List<ExpressionElement> postfix4 = new ArrayList<ExpressionElement>();
        postfix4.add(Number.get("2"));
        postfix4.add(Number.get("3"));
        postfix4.add(Number.get("3"));
        postfix4.add(Operator.get("^"));
        postfix4.add(Operator.get("^"));
        assertEquals(NotationConverter.infixToPostfix(infix4), postfix4);

        // Expression ex5 = new Expression("5");
        List<ExpressionElement> infix5 = new ArrayList<ExpressionElement>();
        infix5.add(Number.get("5"));
        List<ExpressionElement> postfix5 = new ArrayList<ExpressionElement>();
        postfix5.add(Number.get("5"));
        assertEquals(NotationConverter.infixToPostfix(infix5), postfix5);
    }

    @Test
    public void testParanthesisExpressions() {

        //Expression ex1 = new Expression("3 + (2 - 3)");
        List<ExpressionElement> infix1 = new ArrayList<ExpressionElement>();
        infix1.add(Number.get("3"));
        infix1.add(Operator.get("+"));
        infix1.add(Paranthesis.get("("));
        infix1.add(Number.get("2"));
        infix1.add(Operator.get("-"));
        infix1.add(Number.get("3"));
        infix1.add(Paranthesis.get(")"));
        List<ExpressionElement> postfix1 = new ArrayList<ExpressionElement>();
        postfix1.add(Number.get("3"));
        postfix1.add(Number.get("2"));
        postfix1.add(Number.get("3"));
        postfix1.add(Operator.get("-"));
        postfix1.add(Operator.get("+"));
        assertEquals(NotationConverter.infixToPostfix(infix1), postfix1);

        //Expression ex2 = new Expression("(9 + 5) * 2");
        List<ExpressionElement> infix2 = new ArrayList<ExpressionElement>();
        infix2.add(Paranthesis.get("("));
        infix2.add(Number.get("9"));
        infix2.add(Operator.get("+"));
        infix2.add(Number.get("5"));
        infix2.add(Paranthesis.get(")"));
        infix2.add(Operator.get("*"));
        infix2.add(Number.get("2"));
        List<ExpressionElement> postfix2 = new ArrayList<ExpressionElement>();
        postfix2.add(Number.get("9"));
        postfix2.add(Number.get("5"));
        postfix2.add(Operator.get("+"));
        postfix2.add(Number.get("2"));
        postfix2.add(Operator.get("*"));
        assertEquals(NotationConverter.infixToPostfix(infix2), postfix2);

        //Expression ex3 = new Expression("3 ^ (4 / 3)");
        List<ExpressionElement> infix3 = new ArrayList<ExpressionElement>();
        infix3.add(Number.get("3"));
        infix3.add(Operator.get("^"));
        infix3.add(Paranthesis.get("("));
        infix3.add(Number.get("4"));
        infix3.add(Operator.get("/"));
        infix3.add(Number.get("3"));
        infix3.add(Paranthesis.get(")"));
        List<ExpressionElement> postfix3 = new ArrayList<ExpressionElement>();
        postfix3.add(Number.get("3"));
        postfix3.add(Number.get("4"));
        postfix3.add(Number.get("3"));
        postfix3.add(Operator.get("/"));
        postfix3.add(Operator.get("^"));
        assertEquals(NotationConverter.infixToPostfix(infix3), postfix3);

        // Expression ex4 = new Expression("3 * (2 / (4-3))");
        List<ExpressionElement> infix4 = new ArrayList<ExpressionElement>();
        infix4.add(Number.get("3"));
        infix4.add(Operator.get("*"));
        infix4.add(Paranthesis.get("("));
        infix4.add(Number.get("2"));
        infix4.add(Operator.get("/"));
        infix4.add(Paranthesis.get("("));
        infix4.add(Number.get("4"));
        infix4.add(Operator.get("-"));
        infix4.add(Number.get("3"));
        infix4.add(Paranthesis.get(")"));
        infix4.add(Paranthesis.get(")"));
        List<ExpressionElement> postfix4 = new ArrayList<ExpressionElement>();
        postfix4.add(Number.get("3"));
        postfix4.add(Number.get("2"));
        postfix4.add(Number.get("4"));
        postfix4.add(Number.get("3"));
        postfix4.add(Operator.get("-"));
        postfix4.add(Operator.get("/"));
        postfix4.add(Operator.get("*"));
        assertEquals(NotationConverter.infixToPostfix(infix4), postfix4);

        //Expression ex5 = new Expression("2^(3^3)^2");
        List<ExpressionElement> infix5 = new ArrayList<ExpressionElement>();
        infix5.add(Number.get("2"));
        infix5.add(Operator.get("^"));
        infix5.add(Paranthesis.get("("));
        infix5.add(Number.get("3"));
        infix5.add(Operator.get("^"));
        infix5.add(Number.get("3"));
        infix5.add(Paranthesis.get(")"));
        infix5.add(Operator.get("^"));
        infix5.add(Number.get("2"));
        List<ExpressionElement> postfix5 = new ArrayList<ExpressionElement>();
        postfix5.add(Number.get("2"));
        postfix5.add(Number.get("3"));
        postfix5.add(Number.get("3"));
        postfix5.add(Operator.get("^"));
        postfix5.add(Number.get("2"));
        postfix5.add(Operator.get("^"));
        postfix5.add(Operator.get("^"));
        assertEquals(NotationConverter.infixToPostfix(infix5), postfix5);

        //Expression ex6 = new Expression("(-5)");
        List<ExpressionElement> infix6 = new ArrayList<ExpressionElement>();
        infix6.add(Paranthesis.get("("));
        infix6.add(Operator.get("-"));
        infix6.add(Number.get("5"));
        infix6.add(Paranthesis.get(")"));
        List<ExpressionElement> postfix6 = new ArrayList<ExpressionElement>();
        postfix6.add(Number.get("5"));
        postfix6.add(Operator.get("-"));
        assertEquals(NotationConverter.infixToPostfix(infix6), postfix6);
    }

    @Test
    public void testUnaryOperatorExpressions() {

        //Expression ex1 = new Expression("-(3+5)");
        List<ExpressionElement> infix1 = new ArrayList<ExpressionElement>();
        infix1.add(Operator.get("-"));
        infix1.add(Paranthesis.get("("));
        infix1.add(Number.get("3"));
        infix1.add(Operator.get("+"));
        infix1.add(Number.get("5"));
        infix1.add(Paranthesis.get(")"));
        List<ExpressionElement> postfix1 = new ArrayList<ExpressionElement>();
        postfix1.add(Number.get("3"));
        postfix1.add(Number.get("5"));
        postfix1.add(Operator.get("+"));
        postfix1.add(Operator.get("-"));
        assertEquals(NotationConverter.infixToPostfix(infix1), postfix1);

        //Expression ex2 = new Expression("-3+5");
        List<ExpressionElement> infix2 = new ArrayList<ExpressionElement>();
        infix2.add(Operator.get("-"));
        infix2.add(Number.get("3"));
        infix2.add(Operator.get("+"));
        infix2.add(Number.get("5"));
        List<ExpressionElement> postfix2 = new ArrayList<ExpressionElement>();
        postfix2.add(Number.get("3"));
        postfix2.add(Operator.get("-"));
        postfix2.add(Number.get("5"));
        postfix2.add(Operator.get("+"));
        assertEquals(NotationConverter.infixToPostfix(infix2), postfix2);

        //Expression ex3 = new Expression("-3^5");
        List<ExpressionElement> infix3 = new ArrayList<ExpressionElement>();
        infix3.add(Operator.get("-"));
        infix3.add(Number.get("3"));
        infix3.add(Operator.get("^"));
        infix3.add(Number.get("5"));
        List<ExpressionElement> postfix3 = new ArrayList<ExpressionElement>();
        postfix3.add(Number.get("3"));
        postfix3.add(Number.get("5"));
        postfix3.add(Operator.get("^"));
        postfix3.add(Operator.get("-"));
        assertEquals(NotationConverter.infixToPostfix(infix3), postfix3);

        //Expression ex4 = new Expression("+3^5");
        List<ExpressionElement> infix4 = new ArrayList<ExpressionElement>();
        infix4.add(Operator.get("+"));
        infix4.add(Number.get("3"));
        infix4.add(Operator.get("^"));
        infix4.add(Number.get("5"));
        List<ExpressionElement> postfix4 = new ArrayList<ExpressionElement>();
        postfix4.add(Number.get("3"));
        postfix4.add(Number.get("5"));
        postfix4.add(Operator.get("^"));
        postfix4.add(Operator.get("+"));
        assertEquals(NotationConverter.infixToPostfix(infix4), postfix4);

        //Expression ex5 = new Expression("+(3+5)");
        List<ExpressionElement> infix5 = new ArrayList<ExpressionElement>();
        infix5.add(Operator.get("+"));
        infix5.add(Paranthesis.get("("));
        infix5.add(Number.get("3"));
        infix5.add(Operator.get("+"));
        infix5.add(Number.get("5"));
        infix5.add(Paranthesis.get(")"));
        List<ExpressionElement> postfix5 = new ArrayList<ExpressionElement>();
        postfix5.add(Number.get("3"));
        postfix5.add(Number.get("5"));
        postfix5.add(Operator.get("+"));
        postfix5.add(Operator.get("+"));
        assertEquals(NotationConverter.infixToPostfix(infix5), postfix5);

        //Expression ex6 = new Expression("+3");
        List<ExpressionElement> infix6 = new ArrayList<ExpressionElement>();
        infix6.add(Operator.get("+"));
        infix6.add(Number.get("3"));
        List<ExpressionElement> postfix6 = new ArrayList<ExpressionElement>();
        postfix6.add(Number.get("3"));
        postfix6.add(Operator.get("+"));
        assertEquals(NotationConverter.infixToPostfix(infix6), postfix6);

        //Expression ex7 = new Expression("-3");
        List<ExpressionElement> infix7 = new ArrayList<ExpressionElement>();
        infix7.add(Operator.get("-"));
        infix7.add(Number.get("3"));
        List<ExpressionElement> postfix7 = new ArrayList<ExpressionElement>();
        postfix7.add(Number.get("3"));
        postfix7.add(Operator.get("-"));
        assertEquals(NotationConverter.infixToPostfix(infix7), postfix7);


        
        // [!] Does this way of handling unary operators always work?

        //Expression ex8 = new Expression("3+(-5)");
        List<ExpressionElement> infix8 = new ArrayList<ExpressionElement>();
        infix8.add(Number.get("3"));
        infix8.add(Operator.get("+"));
        infix8.add(Paranthesis.get("("));
        infix8.add(Operator.get("-"));
        infix8.add(Number.get("5"));
        infix8.add(Paranthesis.get(")"));
        List<ExpressionElement> postfix8 = new ArrayList<ExpressionElement>();
        postfix8.add(Number.get("3"));
        postfix8.add(Number.get("5"));
        postfix8.add(Operator.get("-"));
        postfix8.add(Operator.get("+"));
        assertEquals(NotationConverter.infixToPostfix(infix8), postfix8);
    }

}