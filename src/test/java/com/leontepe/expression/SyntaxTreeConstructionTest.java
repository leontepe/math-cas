package com.leontepe.expression;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leontepe.expression.Operator.Arity;

public class SyntaxTreeConstructionTest {

    @Test
    public void testBasicExpressions() {

        Expression ex1 = new Expression("4+3*2");
        SyntaxTreeNode actual1 = ex1.getSyntaxTree();
        
        SyntaxTreeNode root1 = new SyntaxTreeNode(Operator.ADD);
        {
            SyntaxTreeNode n0 = root1.addChild(Number.get("4"));
            SyntaxTreeNode n1 = root1.addChild(Operator.MULTIPLY);
            {
                SyntaxTreeNode n10 = n1.addChild(Number.get("3"));
                SyntaxTreeNode n11 = n1.addChild(Number.get("2"));
            }
        }

        assertEquals(root1, actual1);

        Expression ex2 = new Expression("4-2+3-1");
        SyntaxTreeNode actual2 = ex2.getSyntaxTree();
        
        SyntaxTreeNode root2 = new SyntaxTreeNode(Operator.SUBTRACT);
        {
            SyntaxTreeNode n0 = root2.addChild(Operator.ADD);
            {
                SyntaxTreeNode n00 = n0.addChild(Operator.SUBTRACT);
                {
                    SyntaxTreeNode n000 = n00.addChild(Number.get("4"));
                    SyntaxTreeNode n001 = n00.addChild(Number.get("2"));
                }
                SyntaxTreeNode n01 = n0.addChild(Number.get("3"));
            }
            SyntaxTreeNode n1 = root2.addChild(Number.get("1"));
        }
        assertEquals(root2, actual2);

        Expression ex3 = new Expression("4");
        SyntaxTreeNode actual3 = ex3.getSyntaxTree();
        SyntaxTreeNode root3 = new SyntaxTreeNode(Number.get("4"));
        assertEquals(root3, actual3);

        Expression ex4 = new Expression("2^2^3");
        SyntaxTreeNode actual4 = ex4.getSyntaxTree();
        SyntaxTreeNode root4 = new SyntaxTreeNode(Operator.EXPONENTIATE);
        {
            SyntaxTreeNode n0 = root4.addChild(Number.get("2"));
            SyntaxTreeNode n1 = root4.addChild(Operator.EXPONENTIATE);
            {
                SyntaxTreeNode n10 = n1.addChild(Number.get("2"));
                SyntaxTreeNode n11 = n1.addChild(Number.get("3"));
            }
        }
        assertEquals(root4, actual4);
    }

    @Test
    public void testParanthesisExpressions() {
        Expression ex1 = new Expression("(4+3)*2");
        SyntaxTreeNode actual1 = ex1.getSyntaxTree();
        SyntaxTreeNode root1 = new SyntaxTreeNode(Operator.MULTIPLY);
        {
            SyntaxTreeNode n0 = root1.addChild(Operator.ADD);
            {
                SyntaxTreeNode n00 = n0.addChild(Number.get("4"));
                SyntaxTreeNode n01 = n0.addChild(Number.get("3"));
            }
            SyntaxTreeNode n1 = root1.addChild(Number.get("2"));
        }
        assertEquals(root1, actual1);

        Expression ex2 = new Expression("3+(4*(6-3))/2");
        SyntaxTreeNode actual2 = ex2.getSyntaxTree();
        SyntaxTreeNode root2 = new SyntaxTreeNode(Operator.ADD);
        {
            SyntaxTreeNode n0 = root2.addChild(Number.get("3"));
            SyntaxTreeNode n1 = root2.addChild(Operator.DIVIDE);
            {
                SyntaxTreeNode n10 = n1.addChild(Operator.MULTIPLY);
                {
                    SyntaxTreeNode n100 = n10.addChild(Number.get("4"));
                    SyntaxTreeNode n101 = n10.addChild(Operator.SUBTRACT);
                    {
                        SyntaxTreeNode n1010 = n101.addChild(Number.get("6"));
                        SyntaxTreeNode n1011 = n101.addChild(Number.get("3"));
                    }
                }
                SyntaxTreeNode n11 = n1.addChild(Number.get("2"));
            }
        }
        assertEquals(root2, actual2);
    }

    @Test
    public void testDecimalExpressions() {

        Expression ex1 = new Expression("2.422*1.5");
        SyntaxTreeNode actual1 = ex1.getSyntaxTree();
        SyntaxTreeNode root1 = new SyntaxTreeNode(Operator.MULTIPLY);
        {
            SyntaxTreeNode n0 = root1.addChild(Number.get("2.422"));
            SyntaxTreeNode n1 = root1.addChild(Number.get("1.5"));
        }
        assertEquals(root1, actual1);

        Expression ex2 = new Expression("0.0001 * 10000.0");
        SyntaxTreeNode actual2 = ex2.getSyntaxTree();
        SyntaxTreeNode root2 = new SyntaxTreeNode(Operator.MULTIPLY);
        {
            SyntaxTreeNode n0 = root2.addChild(Number.get("0.0001"));
            SyntaxTreeNode n1 = root2.addChild(Number.get("10000.0"));
        }
        assertEquals(root2, actual2);

    }

    @Test
    public void testUnaryOperatorExpressions() {
        Expression ex1 = new Expression("-3*12");
        SyntaxTreeNode actual1 = ex1.getSyntaxTree();
        SyntaxTreeNode root1 = new SyntaxTreeNode(Operator.MULTIPLY);
        {
            SyntaxTreeNode n0 = root1.addChild(Operator.NEGATE);
            {
                SyntaxTreeNode n00 = n0.addChild(new Number(3));
            }
            SyntaxTreeNode n1 = root1.addChild(new Number(12));
        }
        assertEquals(root1, actual1);

        Expression ex2 = new Expression("-5.2-0.2");
        SyntaxTreeNode actual2 = ex2.getSyntaxTree();
        SyntaxTreeNode expected2 = new SyntaxTreeNode(Operator.SUBTRACT);
        {
            SyntaxTreeNode n0 = expected2.addChild(Operator.NEGATE);
            {
                SyntaxTreeNode n00 = n0.addChild(new Number(5.2));
            }
            SyntaxTreeNode n1 = expected2.addChild(new Number(0.2));
        }
        assertEquals(expected2, actual2);

        Expression ex3 = new Expression("-9^(1/2)");
        SyntaxTreeNode actual3 = ex3.getSyntaxTree();
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

        Expression ex4 = new Expression("5+-3");
        SyntaxTreeNode actual4 = ex4.getSyntaxTree();
        SyntaxTreeNode expected4 = new SyntaxTreeNode(Operator.ADD);
        {
            SyntaxTreeNode n0 = expected4.addChild(new Number(5));
            SyntaxTreeNode n1 = expected4.addChild(Operator.NEGATE);
            {
                SyntaxTreeNode n10 = n1.addChild(new Number(3));
            }
        }
        assertEquals(expected4, actual4);

        Expression ex5 = new Expression("10/-5");
        SyntaxTreeNode actual5 = ex5.getSyntaxTree();
        SyntaxTreeNode expected5 = new SyntaxTreeNode(Operator.DIVIDE);
        {
            SyntaxTreeNode n0 = expected5.addChild(new Number(10));
            SyntaxTreeNode n1 = expected5.addChild(Operator.NEGATE);
            {
                SyntaxTreeNode n10 = n1.addChild(new Number(5));
            }
        }
        assertEquals(expected5, actual5);

        Expression ex6 = new Expression("-(3+4)*(-5)");
        SyntaxTreeNode actual6 = ex6.getSyntaxTree();
        SyntaxTreeNode expected6 = new SyntaxTreeNode(Operator.MULTIPLY);
        {
            SyntaxTreeNode n0 = expected6.addChild(Operator.NEGATE);
            {
                SyntaxTreeNode n00 = n0.addChild(Operator.ADD);
                {
                    SyntaxTreeNode n000 = n00.addChild(new Number(3));
                    SyntaxTreeNode n001 = n00.addChild(new Number(4));
                }
            }
            SyntaxTreeNode n1 = expected6.addChild(Operator.NEGATE);
            {
                SyntaxTreeNode n10 = n1.addChild(new Number(5));
            }
        }
        assertEquals(expected6, actual6);

        Expression ex7 = new Expression("3---5");
        SyntaxTreeNode actual7 = ex7.getSyntaxTree();
        SyntaxTreeNode expected7 = new SyntaxTreeNode(Operator.SUBTRACT);
        {
            SyntaxTreeNode n0 = expected7.addChild(new Number(3));
            SyntaxTreeNode n1 = expected7.addChild(Operator.NEGATE);
            {
                SyntaxTreeNode n10 = n1.addChild(Operator.NEGATE);
                {
                    SyntaxTreeNode n100 = n10.addChild(new Number(5));
                }
            }
        }
        assertEquals(expected7, actual7);

    }
}