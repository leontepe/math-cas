package com.leontepe.expression;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.List;
import java.time.chrono.Era;
import java.util.ArrayList;

import com.leontepe.syntaxtree.*;

public class SyntaxTreeConstructionTest {

    @Test
    public void testBasicExpressions() {

        Expression ex1 = new Expression("4+3*2");
        Node<ExpressionElement> actual1 = ex1.getSyntaxTree();
        Node<ExpressionElement> root1 = new Node<ExpressionElement>(Operator.get("+"));
        {
            Node<ExpressionElement> n0 = root1.addChild(Number.get("4"));
            Node<ExpressionElement> n1 = root1.addChild(Operator.get("*"));
            {
                Node<ExpressionElement> n10 = n1.addChild(Number.get("3"));
                Node<ExpressionElement> n11 = n1.addChild(Number.get("2"));
            }
        }
        assertEquals(root1, actual1);

        Expression ex2 = new Expression("4-2+3-1");
        Node<ExpressionElement> actual2 = ex2.getSyntaxTree();
        
        Node<ExpressionElement> root2 = new Node<ExpressionElement>(Operator.get("-"));
        {
            Node<ExpressionElement> n0 = root2.addChild(Operator.get("+"));
            {
                Node<ExpressionElement> n00 = n0.addChild(Operator.get("-"));
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
        Node<ExpressionElement> root4 = new Node<ExpressionElement>(Operator.get("^"));
        {
            Node<ExpressionElement> n0 = root4.addChild(Number.get("2"));
            Node<ExpressionElement> n1 = root4.addChild(Operator.get("^"));
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
        Node<ExpressionElement> root1 = new Node<ExpressionElement>(Operator.get("*"));
        {
            Node<ExpressionElement> n0 = root1.addChild(Operator.get("+"));
            {
                Node<ExpressionElement> n00 = n0.addChild(Number.get("4"));
                Node<ExpressionElement> n01 = n0.addChild(Number.get("3"));
            }
            Node<ExpressionElement> n1 = root1.addChild(Number.get("2"));
        }
        assertEquals(root1, actual1);

        Expression ex2 = new Expression("3+(4*(6-3))/2");
        Node<ExpressionElement> actual2 = ex2.getSyntaxTree();
        Node<ExpressionElement> root2 = new Node<ExpressionElement>(Operator.get("+"));
        {
            Node<ExpressionElement> n0 = root2.addChild(Number.get("3"));
            Node<ExpressionElement> n1 = root2.addChild(Operator.get("/"));
            {
                Node<ExpressionElement> n10 = n1.addChild(Operator.get("*"));
                {
                    Node<ExpressionElement> n100 = n10.addChild(Number.get("4"));
                    Node<ExpressionElement> n101 = n10.addChild(Operator.get("-"));
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
        Node<ExpressionElement> root1 = new Node<ExpressionElement>(Operator.get("*"));
        {
            Node<ExpressionElement> n0 = root1.addChild(Number.get("2.422"));
            Node<ExpressionElement> n1 = root1.addChild(Number.get("1.5"));
        }
        assertEquals(root1, actual1);

        Expression ex2 = new Expression("0.0001 * 10000.0");
        Node<ExpressionElement> actual2 = ex2.getSyntaxTree();
        Node<ExpressionElement> root2 = new Node<ExpressionElement>(Operator.get("*"));
        {
            Node<ExpressionElement> n0 = root2.addChild(Number.get("0.0001"));
            Node<ExpressionElement> n1 = root2.addChild(Number.get("10000.0"));
        }
        assertEquals(root2, actual2);

    }
}