package com.leontepe.expression;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leontepe.expression.Operator.Arity;
import com.leontepe.syntaxtree.*;

public class SyntaxTreeConstructionTest {

    @Test
    public void testBasicExpressions() {

        Expression ex1 = new Expression("4+3*2");
        Node<ExpressionElement> actual1 = ex1.getSyntaxTree();
        
        Node<ExpressionElement> root1 = new Node<ExpressionElement>(Operator.ADD);
        {
            Node<ExpressionElement> n0 = root1.addChild(Number.get("4"));
            Node<ExpressionElement> n1 = root1.addChild(Operator.MULTIPLY);
            {
                Node<ExpressionElement> n10 = n1.addChild(Number.get("3"));
                Node<ExpressionElement> n11 = n1.addChild(Number.get("2"));
            }
        }

        assertEquals(root1, actual1);

        Expression ex2 = new Expression("4-2+3-1");
        Node<ExpressionElement> actual2 = ex2.getSyntaxTree();
        
        Node<ExpressionElement> root2 = new Node<ExpressionElement>(Operator.SUBTRACT);
        {
            Node<ExpressionElement> n0 = root2.addChild(Operator.ADD);
            {
                Node<ExpressionElement> n00 = n0.addChild(Operator.SUBTRACT);
                {
                    Node<ExpressionElement> n000 = n00.addChild(Number.get("4"));
                    Node<ExpressionElement> n001 = n00.addChild(Number.get("2"));
                }
                Node<ExpressionElement> n01 = n0.addChild(Number.get("3"));
            }
            Node<ExpressionElement> n1 = root2.addChild(Number.get("1"));
        }
        assertEquals(root2, actual2);

        Expression ex3 = new Expression("4");
        Node<ExpressionElement> actual3 = ex3.getSyntaxTree();
        Node<ExpressionElement> root3 = new Node<ExpressionElement>(Number.get("4"));
        assertEquals(root3, actual3);

        Expression ex4 = new Expression("2^2^3");
        Node<ExpressionElement> actual4 = ex4.getSyntaxTree();
        Node<ExpressionElement> root4 = new Node<ExpressionElement>(Operator.EXPONENTIATE);
        {
            Node<ExpressionElement> n0 = root4.addChild(Number.get("2"));
            Node<ExpressionElement> n1 = root4.addChild(Operator.EXPONENTIATE);
            {
                Node<ExpressionElement> n10 = n1.addChild(Number.get("2"));
                Node<ExpressionElement> n11 = n1.addChild(Number.get("3"));
            }
        }
        assertEquals(root4, actual4);
    }

    @Test
    public void testParanthesisExpressions() {
        Expression ex1 = new Expression("(4+3)*2");
        Node<ExpressionElement> actual1 = ex1.getSyntaxTree();
        Node<ExpressionElement> root1 = new Node<ExpressionElement>(Operator.MULTIPLY);
        {
            Node<ExpressionElement> n0 = root1.addChild(Operator.ADD);
            {
                Node<ExpressionElement> n00 = n0.addChild(Number.get("4"));
                Node<ExpressionElement> n01 = n0.addChild(Number.get("3"));
            }
            Node<ExpressionElement> n1 = root1.addChild(Number.get("2"));
        }
        assertEquals(root1, actual1);

        Expression ex2 = new Expression("3+(4*(6-3))/2");
        Node<ExpressionElement> actual2 = ex2.getSyntaxTree();
        Node<ExpressionElement> root2 = new Node<ExpressionElement>(Operator.ADD);
        {
            Node<ExpressionElement> n0 = root2.addChild(Number.get("3"));
            Node<ExpressionElement> n1 = root2.addChild(Operator.DIVIDE);
            {
                Node<ExpressionElement> n10 = n1.addChild(Operator.MULTIPLY);
                {
                    Node<ExpressionElement> n100 = n10.addChild(Number.get("4"));
                    Node<ExpressionElement> n101 = n10.addChild(Operator.SUBTRACT);
                    {
                        Node<ExpressionElement> n1010 = n101.addChild(Number.get("6"));
                        Node<ExpressionElement> n1011 = n101.addChild(Number.get("3"));
                    }
                }
                Node<ExpressionElement> n11 = n1.addChild(Number.get("2"));
            }
        }
        assertEquals(root2, actual2);
    }

    @Test
    public void testDecimalExpressions() {

        Expression ex1 = new Expression("2.422*1.5");
        Node<ExpressionElement> actual1 = ex1.getSyntaxTree();
        Node<ExpressionElement> root1 = new Node<ExpressionElement>(Operator.MULTIPLY);
        {
            Node<ExpressionElement> n0 = root1.addChild(Number.get("2.422"));
            Node<ExpressionElement> n1 = root1.addChild(Number.get("1.5"));
        }
        assertEquals(root1, actual1);

        Expression ex2 = new Expression("0.0001 * 10000.0");
        Node<ExpressionElement> actual2 = ex2.getSyntaxTree();
        Node<ExpressionElement> root2 = new Node<ExpressionElement>(Operator.MULTIPLY);
        {
            Node<ExpressionElement> n0 = root2.addChild(Number.get("0.0001"));
            Node<ExpressionElement> n1 = root2.addChild(Number.get("10000.0"));
        }
        assertEquals(root2, actual2);

    }

    @Test
    public void testUnaryOperatorExpressions() {
        Expression ex1 = new Expression("-3*12");
        Node<ExpressionElement> actual1 = ex1.getSyntaxTree();
        Node<ExpressionElement> root1 = new Node<ExpressionElement>(Operator.MULTIPLY);
        {
            Node<ExpressionElement> n0 = root1.addChild(Operator.NEGATE);
            {
                Node<ExpressionElement> n00 = n0.addChild(new Number(3));
            }
            Node<ExpressionElement> n1 = root1.addChild(new Number(12));
        }
        assertEquals(root1, actual1);

        Expression ex2 = new Expression("-5.2-0.2");
        Node<ExpressionElement> actual2 = ex2.getSyntaxTree();
        Node<ExpressionElement> expected2 = new Node<ExpressionElement>(Operator.SUBTRACT);
        {
            Node<ExpressionElement> n0 = expected2.addChild(Operator.NEGATE);
            {
                Node<ExpressionElement> n00 = n0.addChild(new Number(5.2));
            }
            Node<ExpressionElement> n1 = expected2.addChild(new Number(0.2));
        }
        assertEquals(expected2, actual2);

        Expression ex3 = new Expression("-9^(1/2)");
        Node<ExpressionElement> actual3 = ex3.getSyntaxTree();
        Node<ExpressionElement> expected3 = new Node<ExpressionElement>(Operator.NEGATE);
        {
            Node<ExpressionElement> n0 = expected3.addChild(Operator.EXPONENTIATE);
            {
                Node<ExpressionElement> n00 = n0.addChild(new Number(9));
                Node<ExpressionElement> n01 = n0.addChild(Operator.DIVIDE);
                {
                    Node<ExpressionElement> n010 = n01.addChild(new Number(1));
                    Node<ExpressionElement> n011 = n01.addChild(new Number(2));
                }
            }
        }
        assertEquals(expected3, actual3);

        Expression ex4 = new Expression("5+-3");
        Node<ExpressionElement> actual4 = ex4.getSyntaxTree();
        Node<ExpressionElement> expected4 = new Node<ExpressionElement>(Operator.ADD);
        {
            Node<ExpressionElement> n0 = expected4.addChild(new Number(5));
            Node<ExpressionElement> n1 = expected4.addChild(Operator.NEGATE);
            {
                Node<ExpressionElement> n10 = n1.addChild(new Number(3));
            }
        }
        assertEquals(expected4, actual4);

        Expression ex5 = new Expression("10/-5");
        Node<ExpressionElement> actual5 = ex5.getSyntaxTree();
        Node<ExpressionElement> expected5 = new Node<ExpressionElement>(Operator.DIVIDE);
        {
            Node<ExpressionElement> n0 = expected5.addChild(new Number(10));
            Node<ExpressionElement> n1 = expected5.addChild(Operator.NEGATE);
            {
                Node<ExpressionElement> n10 = n1.addChild(new Number(5));
            }
        }
        assertEquals(expected5, actual5);

        Expression ex6 = new Expression("-(3+4)*(-5)");
        Node<ExpressionElement> actual6 = ex6.getSyntaxTree();
        Node<ExpressionElement> expected6 = new Node<ExpressionElement>(Operator.MULTIPLY);
        {
            Node<ExpressionElement> n0 = expected6.addChild(Operator.NEGATE);
            {
                Node<ExpressionElement> n00 = n0.addChild(Operator.ADD);
                {
                    Node<ExpressionElement> n000 = n00.addChild(new Number(3));
                    Node<ExpressionElement> n001 = n00.addChild(new Number(4));
                }
            }
            Node<ExpressionElement> n1 = expected6.addChild(Operator.NEGATE);
            {
                Node<ExpressionElement> n10 = n1.addChild(new Number(5));
            }
        }
        assertEquals(expected6, actual6);

        Expression ex7 = new Expression("3---5");
        Node<ExpressionElement> actual7 = ex7.getSyntaxTree();
        Node<ExpressionElement> expected7 = new Node<ExpressionElement>(Operator.SUBTRACT);
        {
            Node<ExpressionElement> n0 = expected7.addChild(new Number(3));
            Node<ExpressionElement> n1 = expected7.addChild(Operator.NEGATE);
            {
                Node<ExpressionElement> n10 = n1.addChild(Operator.NEGATE);
                {
                    Node<ExpressionElement> n100 = n10.addChild(new Number(5));
                }
            }
        }
        assertEquals(expected7, actual7);

    }
}