
package com.leontepe.expression;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;
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
    }

    @Test
    public void testFunctionExpressions() {
        String exString1 = "sin(12)";
        String exString2 = "cos(3)";
        String exString3 = "-tan(-5)";
        
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
        // it should in principle be read anyway, as the expression as such is valid and syntax tree construction
        // would be possible from the original expression string, the information just gets lost by converting it to
        // postfix, and this shouldn't happen, so find a workaround
    }
}