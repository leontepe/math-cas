package com.leontepe.expression;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.leontepe.function.LogarithmicFunction;
import com.leontepe.function.TrigonometricFunction;

import org.junit.Test;

public class SyntaxTreeConstructionTest {

    @Test
    public void testBasicExpressions() {

        String exString1 = "4+3*2";
        List<ExpressionElement> postfix1 = new ArrayList<ExpressionElement>();
        postfix1.add(new Number(4));
        postfix1.add(new Number(3));
        postfix1.add(new Number(2));
        postfix1.add(Operator.MULTIPLY);
        postfix1.add(Operator.ADD);
        SyntaxTreeNode actual1 = SyntaxTreeConstructor.construct(postfix1);
        SyntaxTreeNode expected1 = new SyntaxTreeNode(Operator.ADD);
        {
            SyntaxTreeNode n0 = expected1.addChild(new Number(4));
            SyntaxTreeNode n1 = expected1.addChild(Operator.MULTIPLY);
            {
                SyntaxTreeNode n10 = n1.addChild(new Number(3));
                SyntaxTreeNode n11 = n1.addChild(new Number(2));
            }
        }
        assertEquals(expected1, actual1);

        String exString2 = "4-2+3-1";
        List<ExpressionElement> postfix2 = new ArrayList<ExpressionElement>();
        postfix2.add(new Number(4));
        postfix2.add(new Number(2));
        postfix2.add(Operator.SUBTRACT);
        postfix2.add(new Number(3));
        postfix2.add(Operator.ADD);
        postfix2.add(new Number(1));
        postfix2.add(Operator.SUBTRACT);
        SyntaxTreeNode actual2 = SyntaxTreeConstructor.construct(postfix2);
        SyntaxTreeNode expected2 = new SyntaxTreeNode(Operator.SUBTRACT);
        {
            SyntaxTreeNode n0 = expected2.addChild(Operator.ADD);
            {
                SyntaxTreeNode n00 = n0.addChild(Operator.SUBTRACT);
                {
                    SyntaxTreeNode n000 = n00.addChild(new Number(4));
                    SyntaxTreeNode n001 = n00.addChild(new Number(2));
                }
                SyntaxTreeNode n01 = n0.addChild(new Number(3));
            }
            SyntaxTreeNode n1 = expected2.addChild(new Number(1));
        }
        assertEquals(expected2, actual2);

        String exString3 = "4";
        List<ExpressionElement> postfix3 = new ArrayList<ExpressionElement>();
        postfix3.add(new Number(4));
        SyntaxTreeNode actual3 = SyntaxTreeConstructor.construct(postfix3);
        SyntaxTreeNode expected3 = new SyntaxTreeNode(new Number(4));
        assertEquals(expected3, actual3);

        String exString4 = "2^2^3";
        List<ExpressionElement> postfix4 = new ArrayList<ExpressionElement>();
        postfix4.add(new Number(2));
        postfix4.add(new Number(2));
        postfix4.add(new Number(3));
        postfix4.add(Operator.EXPONENTIATE);
        postfix4.add(Operator.EXPONENTIATE);
        SyntaxTreeNode actual4 = SyntaxTreeConstructor.construct(postfix4);
        SyntaxTreeNode expected4 = new SyntaxTreeNode(Operator.EXPONENTIATE);
        {
            SyntaxTreeNode n0 = expected4.addChild(new Number(2));
            SyntaxTreeNode n1 = expected4.addChild(Operator.EXPONENTIATE);
            {
                SyntaxTreeNode n10 = n1.addChild(new Number(2));
                SyntaxTreeNode n11 = n1.addChild(new Number(3));
            }
        }
        assertEquals(expected4, actual4);
    }

    @Test
    public void testParanthesisExpressions() {
        String exString1 = "(4+3)*2";
        List<ExpressionElement> postfix1 = new ArrayList<ExpressionElement>();
        postfix1.add(new Number(4));
        postfix1.add(new Number(3));
        postfix1.add(Operator.ADD);
        postfix1.add(new Number(2));
        postfix1.add(Operator.MULTIPLY);
        SyntaxTreeNode actual1 = SyntaxTreeConstructor.construct(postfix1);
        SyntaxTreeNode expected1 = new SyntaxTreeNode(Operator.MULTIPLY);
        {
            SyntaxTreeNode n0 = expected1.addChild(Operator.ADD);
            {
                SyntaxTreeNode n00 = n0.addChild(new Number(4));
                SyntaxTreeNode n01 = n0.addChild(new Number(3));
            }
            SyntaxTreeNode n1 = expected1.addChild(new Number(2));
        }
        assertEquals(expected1, actual1);

        String exString2 = "3+(4*(6-3))/2";
        List<ExpressionElement> postfix2 = new ArrayList<ExpressionElement>();
        postfix2.add(new Number(3));
        postfix2.add(new Number(4));
        postfix2.add(new Number(6));
        postfix2.add(new Number(3));
        postfix2.add(Operator.SUBTRACT);
        postfix2.add(Operator.MULTIPLY);
        postfix2.add(new Number(2));
        postfix2.add(Operator.DIVIDE);
        postfix2.add(Operator.ADD);
        SyntaxTreeNode actual2 = SyntaxTreeConstructor.construct(postfix2);
        SyntaxTreeNode expected2 = new SyntaxTreeNode(Operator.ADD);
        {
            SyntaxTreeNode n0 = expected2.addChild(new Number(3));
            SyntaxTreeNode n1 = expected2.addChild(Operator.DIVIDE);
            {
                SyntaxTreeNode n10 = n1.addChild(Operator.MULTIPLY);
                {
                    SyntaxTreeNode n100 = n10.addChild(new Number(4));
                    SyntaxTreeNode n101 = n10.addChild(Operator.SUBTRACT);
                    {
                        SyntaxTreeNode n1010 = n101.addChild(new Number(6));
                        SyntaxTreeNode n1011 = n101.addChild(new Number(3));
                    }
                }
                SyntaxTreeNode n11 = n1.addChild(new Number(2));
            }
        }
        assertEquals(expected2, actual2);
    }

    @Test
    public void testUnaryOperatorExpressions() {
        String exString1 = "-3*12";
        List<ExpressionElement> postfix1 = new ArrayList<ExpressionElement>();
        postfix1.add(new Number(3));
        postfix1.add(Operator.NEGATE);
        postfix1.add(new Number(12));
        postfix1.add(Operator.MULTIPLY);
        SyntaxTreeNode actual1 = SyntaxTreeConstructor.construct(postfix1);
        SyntaxTreeNode expected1 = new SyntaxTreeNode(Operator.MULTIPLY);
        {
            SyntaxTreeNode n0 = expected1.addChild(Operator.NEGATE);
            {
                SyntaxTreeNode n00 = n0.addChild(new Number(3));
            }
            SyntaxTreeNode n1 = expected1.addChild(new Number(12));
        }
        assertEquals(expected1, actual1);

        String exString2 = "-5.2-0.3";
        List<ExpressionElement> postfix2 = new ArrayList<ExpressionElement>();
        postfix2.add(new Number(5.2));
        postfix2.add(Operator.NEGATE);
        postfix2.add(new Number(0.3));
        postfix2.add(Operator.SUBTRACT);
        SyntaxTreeNode actual2 = SyntaxTreeConstructor.construct(postfix2);
        SyntaxTreeNode expected2 = new SyntaxTreeNode(Operator.SUBTRACT);
        {
            SyntaxTreeNode n0 = expected2.addChild(Operator.NEGATE);
            {
                SyntaxTreeNode n00 = n0.addChild(new Number(5.2));
            }
            SyntaxTreeNode n1 = expected2.addChild(new Number(0.3));
        }
        assertEquals(expected2, actual2);

        String exString3 = "-9^(1/2)";
        List<ExpressionElement> postfix3 = new ArrayList<ExpressionElement>();
        postfix3.add(new Number(9));
        postfix3.add(new Number(1));
        postfix3.add(new Number(2));
        postfix3.add(Operator.DIVIDE);
        postfix3.add(Operator.EXPONENTIATE);
        postfix3.add(Operator.NEGATE);
        SyntaxTreeNode actual3 = SyntaxTreeConstructor.construct(postfix3);
        SyntaxTreeNode expected3 = new SyntaxTreeNode(Operator.NEGATE);
        {
            SyntaxTreeNode n0 = expected3.addChild(Operator.EXPONENTIATE);
            {
                SyntaxTreeNode n00 = n0.addChild(new Number(9));
                SyntaxTreeNode n01 = n0.addChild(Operator.DIVIDE);
                {
                    SyntaxTreeNode n010 = n01.addChild(new Number(1));
                    SyntaxTreeNode n011 = n01.addChild(new Number(2));
                }
            }
        }
        assertEquals(expected3, actual3);

        String exString4 = "5+-3";
        List<ExpressionElement> postfix4 = new ArrayList<ExpressionElement>();
        postfix4.add(new Number(5));
        postfix4.add(new Number(3));
        postfix4.add(Operator.NEGATE);
        postfix4.add(Operator.ADD);
        SyntaxTreeNode actual4 = SyntaxTreeConstructor.construct(postfix4);
        SyntaxTreeNode expected4 = new SyntaxTreeNode(Operator.ADD);
        {
            SyntaxTreeNode n0 = expected4.addChild(new Number(5));
            SyntaxTreeNode n1 = expected4.addChild(Operator.NEGATE);
            {
                SyntaxTreeNode n10 = n1.addChild(new Number(3));
            }
        }
        assertEquals(expected4, actual4);

        String exString5 = "10/-5";
        List<ExpressionElement> postfix5 = new ArrayList<ExpressionElement>();
        postfix5.add(new Number(10));
        postfix5.add(new Number(5));
        postfix5.add(Operator.NEGATE);
        postfix5.add(Operator.DIVIDE);
        SyntaxTreeNode actual5 = SyntaxTreeConstructor.construct(postfix5);
        SyntaxTreeNode expected5 = new SyntaxTreeNode(Operator.DIVIDE);
        {
            SyntaxTreeNode n0 = expected5.addChild(new Number(10));
            SyntaxTreeNode n1 = expected5.addChild(Operator.NEGATE);
            {
                SyntaxTreeNode n10 = n1.addChild(new Number(5));
            }
        }
        assertEquals(expected5, actual5);

        String exString6 = "-(3+4)*(-5)";
        List<ExpressionElement> postfix6 = new ArrayList<ExpressionElement>();
        postfix6.add(new Number(3));
        postfix6.add(new Number(4));
        postfix6.add(Operator.ADD);
        postfix6.add(new Number(5));
        postfix6.add(Operator.NEGATE);
        postfix6.add(Operator.MULTIPLY);
        postfix6.add(Operator.NEGATE);
        SyntaxTreeNode actual6 = SyntaxTreeConstructor.construct(postfix6);

        SyntaxTreeNode expected6 = new SyntaxTreeNode(Operator.NEGATE);
        {
            SyntaxTreeNode n0 = expected6.addChild(Operator.MULTIPLY);
            {
                SyntaxTreeNode n00 = n0.addChild(Operator.ADD);
                {
                    SyntaxTreeNode n000 = n00.addChild(new Number(3));
                    SyntaxTreeNode n001 = n00.addChild(new Number(4));
                }
                SyntaxTreeNode n01 = n0.addChild(Operator.NEGATE);
                {
                    SyntaxTreeNode n010 = n01.addChild(new Number(5));
                }
            }
        }
        assertEquals(expected6, actual6);

        String exString7 = "2!+4";
        List<ExpressionElement> postfix7 = new ArrayList<ExpressionElement>();
        postfix7.add(new Number(2));
        postfix7.add(Operator.FACTORIAL);
        postfix7.add(new Number(4));
        postfix7.add(Operator.ADD);
        SyntaxTreeNode actual7 = SyntaxTreeConstructor.construct(postfix7);
        SyntaxTreeNode expected7 = new SyntaxTreeNode(Operator.ADD);
        {
            SyntaxTreeNode n0 = expected7.addChild(Operator.FACTORIAL);
            {
                SyntaxTreeNode n00 = n0.addChild(new Number(2));
            }
            SyntaxTreeNode n1 = expected7.addChild(new Number(4));
        }
        assertEquals(expected7, actual7);

        String exString14 = "3*(5-2)!-3!";
        List<ExpressionElement> postfix8 = new ArrayList<ExpressionElement>();
        postfix8.add(new Number(3));
        postfix8.add(new Number(5));
        postfix8.add(new Number(2));
        postfix8.add(Operator.SUBTRACT);
        postfix8.add(Operator.FACTORIAL);
        postfix8.add(Operator.MULTIPLY);
        postfix8.add(new Number(3));
        postfix8.add(Operator.FACTORIAL);
        postfix8.add(Operator.SUBTRACT);
        SyntaxTreeNode actual8 = SyntaxTreeConstructor.construct(postfix8);
        SyntaxTreeNode expected8 = new SyntaxTreeNode(Operator.SUBTRACT);
        {
            SyntaxTreeNode n0 = expected8.addChild(Operator.MULTIPLY);
            {
                SyntaxTreeNode n00 = n0.addChild(new Number(3));
                SyntaxTreeNode n01 = n0.addChild(Operator.FACTORIAL);
                {
                    SyntaxTreeNode n010 = n01.addChild(Operator.SUBTRACT);
                    {
                        SyntaxTreeNode n0100 = n010.addChild(new Number(5));
                        SyntaxTreeNode n0101 = n010.addChild(new Number(2));
                    }
                }
            }
            SyntaxTreeNode n1 = expected8.addChild(Operator.FACTORIAL);
            {
                SyntaxTreeNode n10 = n1.addChild(new Number(3));
            }
        }
        assertEquals(expected8, actual8);

        String exString15 = "-5!+4!*2-(-3!!)";
        List<ExpressionElement> postfix9 = new ArrayList<ExpressionElement>();
        postfix9.add(new Number(5));
        postfix9.add(Operator.FACTORIAL);
        postfix9.add(Operator.NEGATE);
        postfix9.add(new Number(4));
        postfix9.add(Operator.FACTORIAL);
        postfix9.add(new Number(2));
        postfix9.add(Operator.MULTIPLY);
        postfix9.add(Operator.ADD);
        postfix9.add(new Number(3));
        postfix9.add(Operator.FACTORIAL);
        postfix9.add(Operator.FACTORIAL);
        postfix9.add(Operator.NEGATE);
        postfix9.add(Operator.SUBTRACT);
        SyntaxTreeNode actual9 = SyntaxTreeConstructor.construct(postfix9);
        SyntaxTreeNode expected9 = new SyntaxTreeNode(Operator.SUBTRACT);
        {
            SyntaxTreeNode n0 = expected9.addChild(Operator.ADD);
            {
                SyntaxTreeNode n00 = n0.addChild(Operator.NEGATE);
                {
                    SyntaxTreeNode n000 = n00.addChild(Operator.FACTORIAL);
                    {
                        SyntaxTreeNode n0000 = n000.addChild(new Number(5));
                    }
                }
                SyntaxTreeNode n01 = n0.addChild(Operator.MULTIPLY);
                {
                    SyntaxTreeNode n010 = n01.addChild(Operator.FACTORIAL);
                    {
                        SyntaxTreeNode n0100 = n010.addChild(new Number(4));
                    }
                    SyntaxTreeNode n011 = n01.addChild(new Number(2));
                }
            }
            SyntaxTreeNode n1 = expected9.addChild(Operator.NEGATE);
            {
                SyntaxTreeNode n10 = n1.addChild(Operator.FACTORIAL);
                {
                    SyntaxTreeNode n100 = n10.addChild(Operator.FACTORIAL);
                    {
                        SyntaxTreeNode n1000 = n100.addChild(new Number(3));
                    }
                }
            }
        }
        assertEquals(expected9, actual9);
    }

    @Test
    public void testPredefinedFunctionExpressions() {
        String exString1 = "3+cos(4)*lg(2+ln(3))";
        List<ExpressionElement> postfix1 = new ArrayList<ExpressionElement>();
        postfix1.add(new Number(3));
        postfix1.add(new Number(4));
        postfix1.add(TrigonometricFunction.COSINE);
        postfix1.add(new Number(2));
        postfix1.add(new Number(3));
        postfix1.add(LogarithmicFunction.NATURAL_LOGARITHM);
        postfix1.add(Operator.ADD);
        postfix1.add(LogarithmicFunction.COMMON_LOGARITHM);
        postfix1.add(Operator.MULTIPLY);
        postfix1.add(Operator.ADD);
        SyntaxTreeNode actual1 = SyntaxTreeConstructor.construct(postfix1);
        SyntaxTreeNode expected1 = new SyntaxTreeNode(Operator.ADD);
        {
            SyntaxTreeNode n0 = expected1.addChild(new Number(3));
            SyntaxTreeNode n1 = expected1.addChild(Operator.MULTIPLY);
            {
                SyntaxTreeNode n10 = n1.addChild(TrigonometricFunction.COSINE);
                {
                    SyntaxTreeNode n100 = n10.addChild(new Number(4));
                }
                SyntaxTreeNode n11 = n1.addChild(LogarithmicFunction.COMMON_LOGARITHM);
                {
                    SyntaxTreeNode n110 = n11.addChild(Operator.ADD);
                    {
                        SyntaxTreeNode n1100 = n110.addChild(new Number(2));
                        SyntaxTreeNode n1101 = n110.addChild(LogarithmicFunction.NATURAL_LOGARITHM);
                        {
                            SyntaxTreeNode n11010 = n1101.addChild(new Number(3));
                        }
                    }
                }
            }
        }
        assertEquals(expected1, actual1);
    }

    @Test
    public void testConstantExpressions() {
        String exString1 = "3*x-(4*e)/y+2*π";
        List<ExpressionElement> postfix1 = new ArrayList<ExpressionElement>();
        postfix1.add(new Number(3));
        postfix1.add(new Variable('x'));
        postfix1.add(Operator.MULTIPLY);
        postfix1.add(new Number(4));
        postfix1.add(Constant.E);
        postfix1.add(Operator.MULTIPLY);
        postfix1.add(new Variable('y'));
        postfix1.add(Operator.DIVIDE);
        postfix1.add(Operator.SUBTRACT);
        postfix1.add(new Number(2));
        postfix1.add(Constant.PI);
        postfix1.add(Operator.MULTIPLY);
        postfix1.add(Operator.ADD);
        SyntaxTreeNode actual1 = SyntaxTreeConstructor.construct(postfix1);
        SyntaxTreeNode expected1 = new SyntaxTreeNode(Operator.ADD);
        {
            SyntaxTreeNode n0 = expected1.addChild(Operator.SUBTRACT);
            {
                SyntaxTreeNode n00 = n0.addChild(Operator.MULTIPLY);
                {
                    SyntaxTreeNode n000 = n00.addChild(new Number(3));
                    SyntaxTreeNode n001 = n00.addChild(new Variable('x'));
                }
                SyntaxTreeNode n01 = n0.addChild(Operator.DIVIDE);
                {
                    SyntaxTreeNode n010 = n01.addChild(Operator.MULTIPLY);
                    {
                        SyntaxTreeNode n0100 = n010.addChild(new Number(4));
                        SyntaxTreeNode n0101 = n010.addChild(Constant.E);
                    }
                    SyntaxTreeNode n011 = n01.addChild(new Variable('y'));
                }
            }
            SyntaxTreeNode n1 = expected1.addChild(Operator.MULTIPLY);
            {
                SyntaxTreeNode n10 = n1.addChild(new Number(2));
                SyntaxTreeNode n11 = n1.addChild(Constant.PI);
            }
        }
        assertEquals(expected1, actual1);
    }
}