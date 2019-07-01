
package com.leontepe.expression;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;

import com.leontepe.function.LogarithmicFunction;
import com.leontepe.function.TrigonometricFunction;

import java.util.ArrayList;

public class TokenizationTest {

    @Test
    public void testBasicExpressions() {
        String exString1 = "12-5*18";
        List<ExpressionElement> actual1 = ExpressionTokenizer.tokenize(exString1);
        List<ExpressionElement> expected1 = new ArrayList<ExpressionElement>();
        expected1.add(new Number(12));
        expected1.add(Operator.SUBTRACT);
        expected1.add(new Number(5));
        expected1.add(Operator.MULTIPLY);
        expected1.add(new Number(18));
        assertEquals(expected1, actual1);

        String exString2 = "124/200^3";
        List<ExpressionElement> actual2 = ExpressionTokenizer.tokenize(exString2);
        List<ExpressionElement> expected2 = new ArrayList<ExpressionElement>();
        expected2.add(new Number(124));
        expected2.add(Operator.DIVIDE);
        expected2.add(new Number(200));
        expected2.add(Operator.EXPONENTIATE);
        expected2.add(new Number(3));
        assertEquals(expected2, actual2);

        String exString3 = "5";
        List<ExpressionElement> actual3 = ExpressionTokenizer.tokenize(exString3);
        List<ExpressionElement> expected3 = new ArrayList<ExpressionElement>();
        expected3.add(new Number(5));
        assertEquals(expected3, actual3);

        String exString4 = "1200";
        List<ExpressionElement> actual4 = ExpressionTokenizer.tokenize(exString4);
        List<ExpressionElement> expected4 = new ArrayList<ExpressionElement>();
        expected4.add(new Number(1200));
        assertEquals(expected4, actual4);
    }

    @Test
    public void testParenthesisExpressions() {
        String exString1 = "4+(3/2)^2";
        List<ExpressionElement> actual1 = ExpressionTokenizer.tokenize(exString1);
        List<ExpressionElement> expected1 = new ArrayList<ExpressionElement>();
        expected1.add(new Number(4));
        expected1.add(Operator.ADD);
        expected1.add(Parenthesis.LEFT_PARENTHESIS);
        expected1.add(new Number(3));
        expected1.add(Operator.DIVIDE);
        expected1.add(new Number(2));
        expected1.add(Parenthesis.RIGHT_PARENTHESIS);
        expected1.add(Operator.EXPONENTIATE);
        expected1.add(new Number(2));
        assertEquals(expected1, actual1);
    }

    @Test
    public void testDecimalExpressions() {
        String exString1 = "12.3-5.0*.18";
        List<ExpressionElement> actual1 = ExpressionTokenizer.tokenize(exString1);
        List<ExpressionElement> expected1 = new ArrayList<ExpressionElement>();
        expected1.add(new Number(12.3));
        expected1.add(Operator.SUBTRACT);
        expected1.add(new Number(5.0));
        expected1.add(Operator.MULTIPLY);
        expected1.add(new Number(.18));
        assertEquals(expected1, actual1);

        String exString2 = "1.24/2.00^3.22222222";
        List<ExpressionElement> actual2 = ExpressionTokenizer.tokenize(exString2);
        List<ExpressionElement> expected2 = new ArrayList<ExpressionElement>();
        expected2.add(new Number(1.24));
        expected2.add(Operator.DIVIDE);
        expected2.add(new Number(2.00));
        expected2.add(Operator.EXPONENTIATE);
        expected2.add(new Number(3.22222222));
        assertEquals(expected2, actual2);
    }

    @Test
    public void testVariableExpressions() {
        String exString1 = "4+x*2-y";
        List<ExpressionElement> actual1 = ExpressionTokenizer.tokenize(exString1);
        List<ExpressionElement> expected1 = new ArrayList<ExpressionElement>();
        expected1.add(new Number(4));
        expected1.add(Operator.ADD);
        expected1.add(new Variable('x'));
        expected1.add(Operator.MULTIPLY);
        expected1.add(new Number(2));
        expected1.add(Operator.SUBTRACT);
        expected1.add(new Variable('y'));
        assertEquals(expected1, actual1);

        String exString2 = "-x^y";
        List<ExpressionElement> actual2 = ExpressionTokenizer.tokenize(exString2);
        List<ExpressionElement> expected2 = new ArrayList<ExpressionElement>();
        expected2.add(Operator.NEGATE);
        expected2.add(new Variable('x'));
        expected2.add(Operator.EXPONENTIATE);
        expected2.add(new Variable('y'));
        assertEquals(expected2, actual2);
    }

    @Test
    public void testImplicitMultiplication() {

    }

    @Test
    public void testUnaryOperatorExpressions() {
        String exString1 = "-5+3-(+2*3)";
        List<ExpressionElement> actual1 = ExpressionTokenizer.tokenize(exString1);
        List<ExpressionElement> expected1 = new ArrayList<ExpressionElement>();
        expected1.add(Operator.NEGATE);
        expected1.add(new Number(5));
        expected1.add(Operator.ADD);
        expected1.add(new Number(3));
        expected1.add(Operator.SUBTRACT);
        expected1.add(Parenthesis.LEFT_PARENTHESIS);
        expected1.add(Operator.UNARY_PLUS);
        expected1.add(new Number(2));
        expected1.add(Operator.MULTIPLY);
        expected1.add(new Number(3));
        expected1.add(Parenthesis.RIGHT_PARENTHESIS);
        assertEquals(expected1, actual1);

        String exString2 = "-2^3";
        List<ExpressionElement> actual2 = ExpressionTokenizer.tokenize(exString2);
        List<ExpressionElement> expected2 = new ArrayList<ExpressionElement>();
        expected2.add(Operator.NEGATE);
        expected2.add(new Number(2));
        expected2.add(Operator.EXPONENTIATE);
        expected2.add(new Number(3));
        assertEquals(expected2, actual2);

        String exString3 = "2^-3";
        List<ExpressionElement> actual3 = ExpressionTokenizer.tokenize(exString3);
        List<ExpressionElement> expected3 = new ArrayList<ExpressionElement>();
        expected3.add(new Number(2));
        expected3.add(Operator.EXPONENTIATE);
        expected3.add(Operator.NEGATE);
        expected3.add(new Number(3));
        assertEquals(expected3, actual3);

        String exString4 = "4!+3";
        List<ExpressionElement> actual4 = ExpressionTokenizer.tokenize(exString4);
        List<ExpressionElement> expected4 = new ArrayList<ExpressionElement>();
        expected4.add(new Number(4));
        expected4.add(Operator.FACTORIAL);
        expected4.add(Operator.ADD);
        expected4.add(new Number(3));
        assertEquals(expected4, actual4);

        String exString5 = "3*(5-2)!-3!";
        List<ExpressionElement> actual5 = ExpressionTokenizer.tokenize(exString5);
        List<ExpressionElement> expected5 = new ArrayList<ExpressionElement>();
        expected5.add(new Number(3));
        expected5.add(Operator.MULTIPLY);
        expected5.add(Parenthesis.LEFT_PARENTHESIS);
        expected5.add(new Number(5));
        expected5.add(Operator.SUBTRACT);
        expected5.add(new Number(2));
        expected5.add(Parenthesis.RIGHT_PARENTHESIS);
        expected5.add(Operator.FACTORIAL);
        expected5.add(Operator.SUBTRACT);
        expected5.add(new Number(3));
        expected5.add(Operator.FACTORIAL);
        assertEquals(expected5, actual5);

        String exString6 = "-5!+4!*2-(-3!!)";
        List<ExpressionElement> actual6 = ExpressionTokenizer.tokenize(exString6);
        List<ExpressionElement> expected6 = new ArrayList<ExpressionElement>();
        expected6.add(Operator.NEGATE);
        expected6.add(new Number(5));
        expected6.add(Operator.FACTORIAL);
        expected6.add(Operator.ADD);
        expected6.add(new Number(4));
        expected6.add(Operator.FACTORIAL);
        expected6.add(Operator.MULTIPLY);
        expected6.add(new Number(2));
        expected6.add(Operator.SUBTRACT);
        expected6.add(Parenthesis.LEFT_PARENTHESIS);
        expected6.add(Operator.NEGATE);
        expected6.add(new Number(3));
        expected6.add(Operator.FACTORIAL);
        expected6.add(Operator.FACTORIAL);
        expected6.add(Parenthesis.RIGHT_PARENTHESIS);
        assertEquals(expected6, actual6);
    }

    @Test
    public void testPredefinedFunctionExpressions() {
        String exString1 = "sin(12)";
        List<ExpressionElement> actual1 = ExpressionTokenizer.tokenize(exString1);
        List<ExpressionElement> expected1 = new ArrayList<ExpressionElement>();
        expected1.add(TrigonometricFunction.SINE);
        expected1.add(Parenthesis.LEFT_PARENTHESIS);
        expected1.add(new Number(12));
        expected1.add(Parenthesis.RIGHT_PARENTHESIS);
        assertEquals(expected1, actual1);

        String exString2 = "-cos(-3)+4";
        List<ExpressionElement> actual2 = ExpressionTokenizer.tokenize(exString2);
        List<ExpressionElement> expected2 = new ArrayList<ExpressionElement>();
        expected2.add(Operator.NEGATE);
        expected2.add(TrigonometricFunction.COSINE);
        expected2.add(Parenthesis.LEFT_PARENTHESIS);
        expected2.add(Operator.NEGATE);
        expected2.add(new Number(3));
        expected2.add(Parenthesis.RIGHT_PARENTHESIS);
        expected2.add(Operator.ADD);
        expected2.add(new Number(4));
        assertEquals(expected2, actual2);

        String exString3 = "tan(-sin(-5)+(-12/3)+ln(1))";
        List<ExpressionElement> actual3 = ExpressionTokenizer.tokenize(exString3);
        List<ExpressionElement> expected3 = new ArrayList<ExpressionElement>();
        expected3.add(TrigonometricFunction.TANGENT);
        expected3.add(Parenthesis.LEFT_PARENTHESIS);
        expected3.add(Operator.NEGATE);
        expected3.add(TrigonometricFunction.SINE);
        expected3.add(Parenthesis.LEFT_PARENTHESIS);
        expected3.add(Operator.NEGATE);
        expected3.add(new Number(5));
        expected3.add(Parenthesis.RIGHT_PARENTHESIS);
        expected3.add(Operator.ADD);
        expected3.add(Parenthesis.LEFT_PARENTHESIS);
        expected3.add(Operator.NEGATE);
        expected3.add(new Number(12));
        expected3.add(Operator.DIVIDE);
        expected3.add(new Number(3));
        expected3.add(Parenthesis.RIGHT_PARENTHESIS);
        expected3.add(Operator.ADD);
        expected3.add(LogarithmicFunction.NATURAL_LOGARITHM);
        expected3.add(Parenthesis.LEFT_PARENTHESIS);
        expected3.add(new Number(1));
        expected3.add(Parenthesis.RIGHT_PARENTHESIS);
        expected3.add(Parenthesis.RIGHT_PARENTHESIS);
        assertEquals(expected3, actual3);

        String exString4 = "ln(-lg(3) + lg(200))";
        List<ExpressionElement> actual4 = ExpressionTokenizer.tokenize(exString4);
        List<ExpressionElement> expected4 = new ArrayList<ExpressionElement>();
        expected4.add(LogarithmicFunction.NATURAL_LOGARITHM);
        expected4.add(Parenthesis.LEFT_PARENTHESIS);
        expected4.add(Operator.NEGATE);
        expected4.add(LogarithmicFunction.COMMON_LOGARITHM);
        expected4.add(Parenthesis.LEFT_PARENTHESIS);
        expected4.add(new Number(3));
        expected4.add(Parenthesis.RIGHT_PARENTHESIS);
        expected4.add(Operator.ADD);
        expected4.add(LogarithmicFunction.COMMON_LOGARITHM);
        expected4.add(Parenthesis.LEFT_PARENTHESIS);
        expected4.add(new Number(200));
        expected4.add(Parenthesis.RIGHT_PARENTHESIS);
        expected4.add(Parenthesis.RIGHT_PARENTHESIS);
        assertEquals(expected4, actual4);

        // This should be valid:
        // ex1 = f(3, 2) + 15 * 3
        // f(x, y) = 2x + 3y - 4
        // ex1.evaluate()

        // Function or variable?
        // a(x) = x^2
        // a(3a) = (3a)^2 OR = a*(3a) = 3a^2
        // 4a^3 + 2a^2 + a
        // => a(3x^2+4x^2)
        // =>

        // f(x, y) = 2x+3y-4 ===> f as Function in Math-Context
        // "f(3,2)+15*3"
        // [3] [2] [f] [15] [3] [*] [+]
        // you need to know how many operands a function has in advance
        // it should in principle be read anyway, as the expression as such is valid and
        // syntax tree construction
        // would be possible from the original expression string, the information just
        // gets lost by converting it to
        // postfix, and this shouldn't happen, so find a workaround
    }

    @Test
    public void testConstantExpressions() {
        String exString1 = "3*e+e";
        List<ExpressionElement> actual1 = ExpressionTokenizer.tokenize(exString1);
        List<ExpressionElement> expected1 = new ArrayList<ExpressionElement>();
        expected1.add(new Number(3));
        expected1.add(Operator.MULTIPLY);
        expected1.add(Constant.E);
        expected1.add(Operator.ADD);
        expected1.add(Constant.E);
        assertEquals(expected1, actual1);

        String exString2 = "e*(3-4*e)";
        List<ExpressionElement> actual2 = ExpressionTokenizer.tokenize(exString2);
        List<ExpressionElement> expected2 = new ArrayList<ExpressionElement>();
        expected2.add(Constant.E);
        expected2.add(Operator.MULTIPLY);
        expected2.add(Parenthesis.LEFT_PARENTHESIS);
        expected2.add(new Number(3));
        expected2.add(Operator.SUBTRACT);
        expected2.add(new Number(4));
        expected2.add(Operator.MULTIPLY);
        expected2.add(Constant.E);
        expected2.add(Parenthesis.RIGHT_PARENTHESIS);
        assertEquals(expected2, actual2);

        String exString3 = "-11^e-0.5*e";
        List<ExpressionElement> actual3 = ExpressionTokenizer.tokenize(exString3);
        List<ExpressionElement> expected3 = new ArrayList<ExpressionElement>();
        expected3.add(Operator.NEGATE);
        expected3.add(new Number(11));
        expected3.add(Operator.EXPONENTIATE);
        expected3.add(Constant.E);
        expected3.add(Operator.SUBTRACT);
        expected3.add(new Number(0.5));
        expected3.add(Operator.MULTIPLY);
        expected3.add(Constant.E);
        assertEquals(expected3, actual3);
    }

    @Test
    public void testSeparatorExpressions() {
        // String exString1 = "4*a(3, 4)-2";
        // List<ExpressionElement> actual1 = ExpressionTokenizer.tokenize(exString1);
        // List<ExpressionElement> expected1 = new ArrayList<ExpressionElement>();
        // expected1.add(new Number(3));
        // expected1.add(Operator.MULTIPLY);
        // expected1.add(Constant.E);
        // expected1.add(Operator.ADD);
        // expected1.add(Constant.E);
        // assertEquals(expected1, actual1);
    }
}