package com.leontepe.expression;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.List;
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
    }
}