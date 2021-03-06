
package com.leontepe.expression;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.leontepe.function.LogarithmicFunction;
import com.leontepe.function.TrigonometricFunction;

import org.junit.Test;

public class InfixToPostfixTest {

    /**
     * Test infix to postfix conversion for simple expressions (no parantheses and single digit integers).
     */
    @Test
    public void testBasicExpressions() {
        //Expression ex1 = new Expression("3 - 4 / 2");
        List<ExpressionElement> infix1 = new ArrayList<ExpressionElement>();
        infix1.add(Number.get("3"));
        infix1.add(Operator.SUBTRACT);
        infix1.add(Number.get("4"));
        infix1.add(Operator.DIVIDE);
        infix1.add(Number.get("2"));
        List<ExpressionElement> postfix1 = new ArrayList<ExpressionElement>();
        postfix1.add(Number.get("3"));
        postfix1.add(Number.get("4"));
        postfix1.add(Number.get("2"));
        postfix1.add(Operator.DIVIDE);
        postfix1.add(Operator.SUBTRACT);
        assertEquals(NotationConverter.infixToPostfix(infix1), postfix1);

        //Expression ex2 = new Expression("3+4+4-2");
        List<ExpressionElement> infix2 = new ArrayList<ExpressionElement>();
        infix2.add(Number.get("3"));
        infix2.add(Operator.ADD);
        infix2.add(Number.get("4"));
        infix2.add(Operator.ADD);
        infix2.add(Number.get("4"));
        infix2.add(Operator.SUBTRACT);
        infix2.add(Number.get("2"));
        List<ExpressionElement> postfix2 = new ArrayList<ExpressionElement>();
        postfix2.add(Number.get("3"));
        postfix2.add(Number.get("4"));
        postfix2.add(Operator.ADD);
        postfix2.add(Number.get("4"));
        postfix2.add(Operator.ADD);
        postfix2.add(Number.get("2"));
        postfix2.add(Operator.SUBTRACT);
        assertEquals(NotationConverter.infixToPostfix(infix2), postfix2);

        //Expression ex3 = new Expression("7/8 - 4*3");
        List<ExpressionElement> infix3 = new ArrayList<ExpressionElement>();
        infix3.add(Number.get("7"));
        infix3.add(Operator.DIVIDE);
        infix3.add(Number.get("8"));
        infix3.add(Operator.SUBTRACT);
        infix3.add(Number.get("4"));
        infix3.add(Operator.MULTIPLY);
        infix3.add(Number.get("3"));
        List<ExpressionElement> postfix3 = new ArrayList<ExpressionElement>();
        postfix3.add(Number.get("7"));
        postfix3.add(Number.get("8"));
        postfix3.add(Operator.DIVIDE);
        postfix3.add(Number.get("4"));
        postfix3.add(Number.get("3"));
        postfix3.add(Operator.MULTIPLY);
        postfix3.add(Operator.SUBTRACT);
        assertEquals(NotationConverter.infixToPostfix(infix3), postfix3);

        //Expression ex4 = new Expression("2^3^3");
        List<ExpressionElement> infix4 = new ArrayList<ExpressionElement>();
        infix4.add(Number.get("2"));
        infix4.add(Operator.EXPONENTIATE);
        infix4.add(Number.get("3"));
        infix4.add(Operator.EXPONENTIATE);
        infix4.add(Number.get("3"));
        List<ExpressionElement> postfix4 = new ArrayList<ExpressionElement>();
        postfix4.add(Number.get("2"));
        postfix4.add(Number.get("3"));
        postfix4.add(Number.get("3"));
        postfix4.add(Operator.EXPONENTIATE);
        postfix4.add(Operator.EXPONENTIATE);
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
        infix1.add(Operator.ADD);
        infix1.add(Parenthesis.LEFT_PARENTHESIS);
        infix1.add(Number.get("2"));
        infix1.add(Operator.SUBTRACT);
        infix1.add(Number.get("3"));
        infix1.add(Parenthesis.RIGHT_PARENTHESIS);
        List<ExpressionElement> postfix1 = new ArrayList<ExpressionElement>();
        postfix1.add(Number.get("3"));
        postfix1.add(Number.get("2"));
        postfix1.add(Number.get("3"));
        postfix1.add(Operator.SUBTRACT);
        postfix1.add(Operator.ADD);
        assertEquals(NotationConverter.infixToPostfix(infix1), postfix1);

        //Expression ex2 = new Expression("(9 + 5) * 2");
        List<ExpressionElement> infix2 = new ArrayList<ExpressionElement>();
        infix2.add(Parenthesis.LEFT_PARENTHESIS);
        infix2.add(Number.get("9"));
        infix2.add(Operator.ADD);
        infix2.add(Number.get("5"));
        infix2.add(Parenthesis.RIGHT_PARENTHESIS);
        infix2.add(Operator.MULTIPLY);
        infix2.add(Number.get("2"));
        List<ExpressionElement> postfix2 = new ArrayList<ExpressionElement>();
        postfix2.add(Number.get("9"));
        postfix2.add(Number.get("5"));
        postfix2.add(Operator.ADD);
        postfix2.add(Number.get("2"));
        postfix2.add(Operator.MULTIPLY);
        assertEquals(NotationConverter.infixToPostfix(infix2), postfix2);

        //Expression ex3 = new Expression("3 ^ (4 / 3)");
        List<ExpressionElement> infix3 = new ArrayList<ExpressionElement>();
        infix3.add(Number.get("3"));
        infix3.add(Operator.EXPONENTIATE);
        infix3.add(Parenthesis.LEFT_PARENTHESIS);
        infix3.add(Number.get("4"));
        infix3.add(Operator.DIVIDE);
        infix3.add(Number.get("3"));
        infix3.add(Parenthesis.RIGHT_PARENTHESIS);
        List<ExpressionElement> postfix3 = new ArrayList<ExpressionElement>();
        postfix3.add(Number.get("3"));
        postfix3.add(Number.get("4"));
        postfix3.add(Number.get("3"));
        postfix3.add(Operator.DIVIDE);
        postfix3.add(Operator.EXPONENTIATE);
        assertEquals(NotationConverter.infixToPostfix(infix3), postfix3);

        // Expression ex4 = new Expression("3 * (2 / (4-3))");
        List<ExpressionElement> infix4 = new ArrayList<ExpressionElement>();
        infix4.add(Number.get("3"));
        infix4.add(Operator.MULTIPLY);
        infix4.add(Parenthesis.LEFT_PARENTHESIS);
        infix4.add(Number.get("2"));
        infix4.add(Operator.DIVIDE);
        infix4.add(Parenthesis.LEFT_PARENTHESIS);
        infix4.add(Number.get("4"));
        infix4.add(Operator.SUBTRACT);
        infix4.add(Number.get("3"));
        infix4.add(Parenthesis.RIGHT_PARENTHESIS);
        infix4.add(Parenthesis.RIGHT_PARENTHESIS);
        List<ExpressionElement> postfix4 = new ArrayList<ExpressionElement>();
        postfix4.add(Number.get("3"));
        postfix4.add(Number.get("2"));
        postfix4.add(Number.get("4"));
        postfix4.add(Number.get("3"));
        postfix4.add(Operator.SUBTRACT);
        postfix4.add(Operator.DIVIDE);
        postfix4.add(Operator.MULTIPLY);
        assertEquals(NotationConverter.infixToPostfix(infix4), postfix4);

        String exString5 = "2^(3^3)^2";
        List<ExpressionElement> infix5 = new ArrayList<ExpressionElement>();
        infix5.add(Number.get("2"));
        infix5.add(Operator.EXPONENTIATE);
        infix5.add(Parenthesis.LEFT_PARENTHESIS);
        infix5.add(Number.get("3"));
        infix5.add(Operator.EXPONENTIATE);
        infix5.add(Number.get("3"));
        infix5.add(Parenthesis.RIGHT_PARENTHESIS);
        infix5.add(Operator.EXPONENTIATE);
        infix5.add(Number.get("2"));
        List<ExpressionElement> actual5 = NotationConverter.infixToPostfix(infix5);
        List<ExpressionElement> expected5 = new ArrayList<ExpressionElement>();
        expected5.add(Number.get("2"));
        expected5.add(Number.get("3"));
        expected5.add(Number.get("3"));
        expected5.add(Operator.EXPONENTIATE);
        expected5.add(Number.get("2"));
        expected5.add(Operator.EXPONENTIATE);
        expected5.add(Operator.EXPONENTIATE);
        assertEquals(expected5, actual5);

        //Expression ex6 = new Expression("(5)");
        List<ExpressionElement> infix6 = new ArrayList<ExpressionElement>();
        infix6.add(Parenthesis.LEFT_PARENTHESIS);
        infix6.add(Number.get("5"));
        infix6.add(Parenthesis.RIGHT_PARENTHESIS);
        List<ExpressionElement> postfix6 = new ArrayList<ExpressionElement>();
        postfix6.add(Number.get("5"));
        assertEquals(NotationConverter.infixToPostfix(infix6), postfix6);
    }

    @Test
    public void testUnaryOperatorExpressions() {

        //Expression ex1 = new Expression("-(3+5)");
        List<ExpressionElement> infix1 = new ArrayList<ExpressionElement>();
        infix1.add(Operator.NEGATE);
        infix1.add(Parenthesis.LEFT_PARENTHESIS);
        infix1.add(Number.get("3"));
        infix1.add(Operator.ADD);
        infix1.add(Number.get("5"));
        infix1.add(Parenthesis.RIGHT_PARENTHESIS);
        List<ExpressionElement> postfix1 = new ArrayList<ExpressionElement>();
        postfix1.add(Number.get("3"));
        postfix1.add(Number.get("5"));
        postfix1.add(Operator.ADD);
        postfix1.add(Operator.NEGATE);
        assertEquals(NotationConverter.infixToPostfix(infix1), postfix1);

        //Expression ex2 = new Expression("-3+5");
        List<ExpressionElement> infix2 = new ArrayList<ExpressionElement>();
        infix2.add(Operator.NEGATE);
        infix2.add(Number.get("3"));
        infix2.add(Operator.ADD);
        infix2.add(Number.get("5"));
        List<ExpressionElement> postfix2 = new ArrayList<ExpressionElement>();
        postfix2.add(Number.get("3"));
        postfix2.add(Operator.NEGATE);
        postfix2.add(Number.get("5"));
        postfix2.add(Operator.ADD);
        assertEquals(NotationConverter.infixToPostfix(infix2), postfix2);

        //Expression ex3 = new Expression("-3^5");
        List<ExpressionElement> infix3 = new ArrayList<ExpressionElement>();
        infix3.add(Operator.NEGATE);
        infix3.add(Number.get("3"));
        infix3.add(Operator.EXPONENTIATE);
        infix3.add(Number.get("5"));
        List<ExpressionElement> postfix3 = new ArrayList<ExpressionElement>();
        postfix3.add(Number.get("3"));
        postfix3.add(Number.get("5"));
        postfix3.add(Operator.EXPONENTIATE);
        postfix3.add(Operator.NEGATE);
        assertEquals(NotationConverter.infixToPostfix(infix3), postfix3);

        //Expression ex4 = new Expression("+3^5");
        List<ExpressionElement> infix4 = new ArrayList<ExpressionElement>();
        infix4.add(Operator.UNARY_PLUS);
        infix4.add(Number.get("3"));
        infix4.add(Operator.EXPONENTIATE);
        infix4.add(Number.get("5"));
        List<ExpressionElement> postfix4 = new ArrayList<ExpressionElement>();
        postfix4.add(Number.get("3"));
        postfix4.add(Number.get("5"));
        postfix4.add(Operator.EXPONENTIATE);
        postfix4.add(Operator.UNARY_PLUS);
        assertEquals(NotationConverter.infixToPostfix(infix4), postfix4);

        //Expression ex5 = new Expression("+(3+5)");
        List<ExpressionElement> infix5 = new ArrayList<ExpressionElement>();
        infix5.add(Operator.UNARY_PLUS);
        infix5.add(Parenthesis.LEFT_PARENTHESIS);
        infix5.add(Number.get("3"));
        infix5.add(Operator.ADD);
        infix5.add(Number.get("5"));
        infix5.add(Parenthesis.RIGHT_PARENTHESIS);
        List<ExpressionElement> postfix5 = new ArrayList<ExpressionElement>();
        postfix5.add(Number.get("3"));
        postfix5.add(Number.get("5"));
        postfix5.add(Operator.ADD);
        postfix5.add(Operator.UNARY_PLUS);
        assertEquals(NotationConverter.infixToPostfix(infix5), postfix5);

        //Expression ex6 = new Expression("+3");
        List<ExpressionElement> infix6 = new ArrayList<ExpressionElement>();
        infix6.add(Operator.UNARY_PLUS);
        infix6.add(Number.get("3"));
        List<ExpressionElement> postfix6 = new ArrayList<ExpressionElement>();
        postfix6.add(Number.get("3"));
        postfix6.add(Operator.UNARY_PLUS);
        assertEquals(NotationConverter.infixToPostfix(infix6), postfix6);

        //Expression ex7 = new Expression("-3");
        List<ExpressionElement> infix7 = new ArrayList<ExpressionElement>();
        infix7.add(Operator.NEGATE);
        infix7.add(Number.get("3"));
        List<ExpressionElement> postfix7 = new ArrayList<ExpressionElement>();
        postfix7.add(Number.get("3"));
        postfix7.add(Operator.NEGATE);
        assertEquals(NotationConverter.infixToPostfix(infix7), postfix7);

        // "3+(-5)"
        List<ExpressionElement> infix8 = new ArrayList<ExpressionElement>();
        infix8.add(Number.get("3"));
        infix8.add(Operator.ADD);
        infix8.add(Parenthesis.LEFT_PARENTHESIS);
        infix8.add(Operator.NEGATE);
        infix8.add(Number.get("5"));
        infix8.add(Parenthesis.RIGHT_PARENTHESIS);
        List<ExpressionElement> postfix8 = new ArrayList<ExpressionElement>();
        postfix8.add(Number.get("3"));
        postfix8.add(Number.get("5"));
        postfix8.add(Operator.NEGATE);
        postfix8.add(Operator.ADD);
        assertEquals(NotationConverter.infixToPostfix(infix8), postfix8);

        // "-3^2"
        List<ExpressionElement> infix9 = new ArrayList<ExpressionElement>();
        infix9.add(Operator.NEGATE);
        infix9.add(new Number(3));
        infix9.add(Operator.EXPONENTIATE);
        infix9.add(new Number(2));
        List<ExpressionElement> postfix9 = new ArrayList<ExpressionElement>();
        postfix9.add(new Number(3));
        postfix9.add(new Number(2));
        postfix9.add(Operator.EXPONENTIATE);
        postfix9.add(Operator.NEGATE);
        assertEquals(NotationConverter.infixToPostfix(infix9), postfix9);

        // "3^-2"
        List<ExpressionElement> infix10 = new ArrayList<ExpressionElement>();
        infix10.add(new Number(3));
        infix10.add(Operator.EXPONENTIATE);
        infix10.add(Operator.NEGATE);
        infix10.add(new Number(2));
        List<ExpressionElement> postfix10 = new ArrayList<ExpressionElement>();
        postfix10.add(new Number(3));
        postfix10.add(new Number(2));
        postfix10.add(Operator.NEGATE);
        postfix10.add(Operator.EXPONENTIATE);
        assertEquals(NotationConverter.infixToPostfix(infix10), postfix10);

        // "-3-2"
        List<ExpressionElement> infix11 = new ArrayList<ExpressionElement>();
        infix11.add(Operator.NEGATE);
        infix11.add(new Number(3));
        infix11.add(Operator.SUBTRACT);
        infix11.add(new Number(2));
        List<ExpressionElement> postfix11 = new ArrayList<ExpressionElement>();
        postfix11.add(new Number(3));
        postfix11.add(Operator.NEGATE);
        postfix11.add(new Number(2));
        postfix11.add(Operator.SUBTRACT);
        assertEquals(NotationConverter.infixToPostfix(infix11), postfix11);

        // "-3/2"
        List<ExpressionElement> infix12 = new ArrayList<ExpressionElement>();
        infix12.add(Operator.NEGATE);
        infix12.add(new Number(3));
        infix12.add(Operator.DIVIDE);
        infix12.add(new Number(2));
        List<ExpressionElement> postfix12 = new ArrayList<ExpressionElement>();
        postfix12.add(new Number(3));
        postfix12.add(Operator.NEGATE);
        postfix12.add(new Number(2));
        postfix12.add(Operator.DIVIDE);
        assertEquals(NotationConverter.infixToPostfix(infix12), postfix12);

        String exString13 = "1+2!";
        List<ExpressionElement> infix13 = new ArrayList<ExpressionElement>();
        infix13.add(new Number(1));
        infix13.add(Operator.ADD);
        infix13.add(new Number(2));
        infix13.add(Operator.FACTORIAL);
        List<ExpressionElement> actual13 = NotationConverter.infixToPostfix(infix13);
        List<ExpressionElement> expected13 = new ArrayList<ExpressionElement>();
        expected13.add(new Number(1));
        expected13.add(new Number(2));
        expected13.add(Operator.FACTORIAL);
        expected13.add(Operator.ADD);
        assertEquals(expected13, actual13);

        String exString14 = "3*(5-2)!-3!";
        List<ExpressionElement> infix14 = new ArrayList<ExpressionElement>();
        infix14.add(new Number(3));
        infix14.add(Operator.MULTIPLY);
        infix14.add(Parenthesis.LEFT_PARENTHESIS);
        infix14.add(new Number(5));
        infix14.add(Operator.SUBTRACT);
        infix14.add(new Number(2));
        infix14.add(Parenthesis.RIGHT_PARENTHESIS);
        infix14.add(Operator.FACTORIAL);
        infix14.add(Operator.SUBTRACT);
        infix14.add(new Number(3));
        infix14.add(Operator.FACTORIAL);
        List<ExpressionElement> actual14 = NotationConverter.infixToPostfix(infix14);
        List<ExpressionElement> expected14 = new ArrayList<ExpressionElement>();
        expected14.add(new Number(3));
        expected14.add(new Number(5));
        expected14.add(new Number(2));
        expected14.add(Operator.SUBTRACT);
        expected14.add(Operator.FACTORIAL);
        expected14.add(Operator.MULTIPLY);
        expected14.add(new Number(3));
        expected14.add(Operator.FACTORIAL);
        expected14.add(Operator.SUBTRACT);
        assertEquals(expected14, actual14);
        
        String exString15 = "-5!+4!*2-(-3!!)";
        List<ExpressionElement> infix15 = new ArrayList<ExpressionElement>();
        infix15.add(Operator.NEGATE);
        infix15.add(new Number(5));
        infix15.add(Operator.FACTORIAL);
        infix15.add(Operator.ADD);
        infix15.add(new Number(4));
        infix15.add(Operator.FACTORIAL);
        infix15.add(Operator.MULTIPLY);
        infix15.add(new Number(2));
        infix15.add(Operator.SUBTRACT);
        infix15.add(Parenthesis.LEFT_PARENTHESIS);
        infix15.add(Operator.NEGATE);
        infix15.add(new Number(3));
        infix15.add(Operator.FACTORIAL);
        infix15.add(Operator.FACTORIAL);
        infix15.add(Parenthesis.RIGHT_PARENTHESIS);
        List<ExpressionElement> actual15 = NotationConverter.infixToPostfix(infix15);
        List<ExpressionElement> expected15 = new ArrayList<ExpressionElement>();
        expected15.add(new Number(5));
        expected15.add(Operator.FACTORIAL);
        expected15.add(Operator.NEGATE);
        expected15.add(new Number(4));
        expected15.add(Operator.FACTORIAL);
        expected15.add(new Number(2));
        expected15.add(Operator.MULTIPLY);
        expected15.add(Operator.ADD);
        expected15.add(new Number(3));
        expected15.add(Operator.FACTORIAL);
        expected15.add(Operator.FACTORIAL);
        expected15.add(Operator.NEGATE);
        expected15.add(Operator.SUBTRACT);
        assertEquals(expected15, actual15);
    }

    @Test
    public void testPredefinedFunctionExpressions() {
        String exString1 = "sin(3)";
        List<ExpressionElement> infix1 = new ArrayList<ExpressionElement>();
        infix1.add(TrigonometricFunction.SINE);
        infix1.add(Parenthesis.LEFT_PARENTHESIS);
        infix1.add(new Number(3));
        infix1.add(Parenthesis.RIGHT_PARENTHESIS);
        List<ExpressionElement> postfix1 = new ArrayList<ExpressionElement>();
        postfix1.add(new Number(3));
        postfix1.add(TrigonometricFunction.SINE);
        assertEquals(NotationConverter.infixToPostfix(infix1), postfix1);

        String exString2 = "3*lg(2+4)";
        List<ExpressionElement> infix2 = new ArrayList<ExpressionElement>();
        infix2.add(new Number(3));
        infix2.add(Operator.MULTIPLY);
        infix2.add(LogarithmicFunction.COMMON_LOGARITHM);
        infix2.add(Parenthesis.LEFT_PARENTHESIS);
        infix2.add(new Number(2));
        infix2.add(Operator.ADD);
        infix2.add(new Number(4));
        infix2.add(Parenthesis.RIGHT_PARENTHESIS);
        List<ExpressionElement> postfix2 = new ArrayList<ExpressionElement>();
        postfix2.add(new Number(3));
        postfix2.add(new Number(2));
        postfix2.add(new Number(4));
        postfix2.add(Operator.ADD);
        postfix2.add(LogarithmicFunction.COMMON_LOGARITHM);
        postfix2.add(Operator.MULTIPLY);
        assertEquals(NotationConverter.infixToPostfix(infix2), postfix2);

        String exString3 = "0.5/cos(-sin(4+(-12)) - 52.4)";
        List<ExpressionElement> infix3 = new ArrayList<ExpressionElement>();
        infix3.add(new Number(0.5));
        infix3.add(Operator.DIVIDE);
        infix3.add(TrigonometricFunction.COSINE);
        infix3.add(Parenthesis.LEFT_PARENTHESIS);
        infix3.add(Operator.NEGATE);
        infix3.add(TrigonometricFunction.SINE);
        infix3.add(Parenthesis.LEFT_PARENTHESIS);
        infix3.add(new Number(4));
        infix3.add(Operator.ADD);
        infix3.add(Parenthesis.LEFT_PARENTHESIS);
        infix3.add(Operator.NEGATE);
        infix3.add(new Number(12));
        infix3.add(Parenthesis.RIGHT_PARENTHESIS);
        infix3.add(Parenthesis.RIGHT_PARENTHESIS);
        infix3.add(Operator.SUBTRACT);
        infix3.add(new Number(52.4));
        infix3.add(Parenthesis.RIGHT_PARENTHESIS);
        List<ExpressionElement> postfix3 = new ArrayList<ExpressionElement>();
        postfix3.add(new Number(0.5));
        postfix3.add(new Number(4));
        postfix3.add(new Number(12));
        postfix3.add(Operator.NEGATE);
        postfix3.add(Operator.ADD);
        postfix3.add(TrigonometricFunction.SINE);
        postfix3.add(Operator.NEGATE);
        postfix3.add(new Number(52.4));
        postfix3.add(Operator.SUBTRACT);
        postfix3.add(TrigonometricFunction.COSINE);
        postfix3.add(Operator.DIVIDE);
        assertEquals(NotationConverter.infixToPostfix(infix3), postfix3);

        String exString4 = "arcsin(ln(lg(3)))";
        List<ExpressionElement> infix4 = new ArrayList<ExpressionElement>();
        infix4.add(TrigonometricFunction.ARCSINE);
        infix4.add(Parenthesis.LEFT_PARENTHESIS);
        infix4.add(LogarithmicFunction.NATURAL_LOGARITHM);
        infix4.add(Parenthesis.LEFT_PARENTHESIS);
        infix4.add(LogarithmicFunction.COMMON_LOGARITHM);
        infix4.add(Parenthesis.LEFT_PARENTHESIS);
        infix4.add(new Number(3));
        infix4.add(Parenthesis.RIGHT_PARENTHESIS);
        infix4.add(Parenthesis.RIGHT_PARENTHESIS);
        infix4.add(Parenthesis.RIGHT_PARENTHESIS);
        List<ExpressionElement> postfix4 = new ArrayList<ExpressionElement>();
        postfix4.add(new Number(3));
        postfix4.add(LogarithmicFunction.COMMON_LOGARITHM);
        postfix4.add(LogarithmicFunction.NATURAL_LOGARITHM);
        postfix4.add(TrigonometricFunction.ARCSINE);
        assertEquals(NotationConverter.infixToPostfix(infix4), postfix4);

        String exString5 = "3+cos(4)*lg(2+ln(3))";
        List<ExpressionElement> infix5 = new ArrayList<ExpressionElement>();
        infix5.add(new Number(3));
        infix5.add(Operator.ADD);
        infix5.add(TrigonometricFunction.COSINE);
        infix5.add(Parenthesis.LEFT_PARENTHESIS);
        infix5.add(new Number(4));
        infix5.add(Parenthesis.RIGHT_PARENTHESIS);
        infix5.add(Operator.MULTIPLY);
        infix5.add(LogarithmicFunction.COMMON_LOGARITHM);
        infix5.add(Parenthesis.LEFT_PARENTHESIS);
        infix5.add(new Number(2));
        infix5.add(Operator.ADD);
        infix5.add(LogarithmicFunction.NATURAL_LOGARITHM);
        infix5.add(Parenthesis.LEFT_PARENTHESIS);
        infix5.add(new Number(3));
        infix5.add(Parenthesis.RIGHT_PARENTHESIS);
        infix5.add(Parenthesis.RIGHT_PARENTHESIS);
        List<ExpressionElement> actual5 = NotationConverter.infixToPostfix(infix5);
        List<ExpressionElement> expected5 = new ArrayList<ExpressionElement>();
        expected5.add(new Number(3));
        expected5.add(new Number(4));
        expected5.add(TrigonometricFunction.COSINE);
        expected5.add(new Number(2));
        expected5.add(new Number(3));
        expected5.add(LogarithmicFunction.NATURAL_LOGARITHM);
        expected5.add(Operator.ADD);
        expected5.add(LogarithmicFunction.COMMON_LOGARITHM);
        expected5.add(Operator.MULTIPLY);
        expected5.add(Operator.ADD);
        assertEquals(expected5, actual5);
    }

    @Test
    public void testConstantExpressions() {
        String exString1 = "3*x-(4*e)/y+2*π";
        List<ExpressionElement> infix1 = new ArrayList<ExpressionElement>();
        infix1.add(new Number(3));
        infix1.add(Operator.MULTIPLY);
        infix1.add(new Variable('x'));
        infix1.add(Operator.SUBTRACT);
        infix1.add(Parenthesis.LEFT_PARENTHESIS);
        infix1.add(new Number(4));
        infix1.add(Operator.MULTIPLY);
        infix1.add(Constant.E);
        infix1.add(Parenthesis.RIGHT_PARENTHESIS);
        infix1.add(Operator.DIVIDE);
        infix1.add(new Variable('y'));
        infix1.add(Operator.ADD);
        infix1.add(new Number(2));
        infix1.add(Operator.MULTIPLY);
        infix1.add(Constant.PI);
        List<ExpressionElement> actual1 = NotationConverter.infixToPostfix(infix1);
        List<ExpressionElement> expected1 = new ArrayList<ExpressionElement>();
        expected1.add(new Number(3));
        expected1.add(new Variable('x'));
        expected1.add(Operator.MULTIPLY);
        expected1.add(new Number(4));
        expected1.add(Constant.E);
        expected1.add(Operator.MULTIPLY);
        expected1.add(new Variable('y'));
        expected1.add(Operator.DIVIDE);
        expected1.add(Operator.SUBTRACT);
        expected1.add(new Number(2));
        expected1.add(Constant.PI);
        expected1.add(Operator.MULTIPLY);
        expected1.add(Operator.ADD);
        assertEquals(expected1, actual1);
    }


}