package com.leontepe;

import com.leontepe.expression.Expression;
import com.leontepe.expression.Operator;
import com.leontepe.expression.SyntaxTreeConstructor;
import com.leontepe.expression.SyntaxTreeNode;

import java.util.List;
import java.util.ArrayList;

import com.leontepe.expression.ExpressionElement;
import com.leontepe.expression.ExpressionTokenizer;
import com.leontepe.expression.NotationConverter;
import com.leontepe.expression.Number;

public class App {
    public static void main(String[] args) {
        // new Expression("4+3*2").getSyntaxTree().print();
        // new Expression("4-2+3-1").getSyntaxTree().print();

        new Expression("0.005-((-12+13)^12*3.005)").getSyntaxTree().print();

        // System.out.println("2+3*(2-4)");
        // List<ExpressionElement> postfix1 =
        // NotationConverter.infixToPostfix(ExpressionTokenizer.tokenize("2+3*(2-4)"));
        // System.out.println();
        // for(ExpressionElement el : postfix1) {
        // System.out.print("[" + el.toString() + "]");
        // }
        // System.out.println();

        // System.out.println();
        // System.out.println();

        // System.out.println("0.005-((-12+13)^12*3.005)");

        // List<ExpressionElement> els = Expression.deconstruct(new Expression("(3+4)*2").getSyntaxTree());
        // for(ExpressionElement el : els) {
        //     System.out.print("[" + el.toString() + "]");
        // }

        String expected4 = "-(-5+3)/2*12+3^(-3*4)";
        String actual4 = new Expression(expected4).toString();
        System.out.println(actual4);
        // List<ExpressionElement> postfix =
        // NotationConverter.infixToPostfix(ExpressionTokenizer.tokenize("0.005-((-12+13)^12*3.005)"));
        // System.out.println();
        // for(ExpressionElement el : postfix) {
        // System.out.print("[" + el.toString() + "]");
        // }
        // System.out.println();

        // SyntaxTreeNode node = new SyntaxTreeNode(Operator.MULTIPLY);
        // {
        //     SyntaxTreeNode n0 = node.addChild(new Number(2));
        //     SyntaxTreeNode n1 = node.addChild(new Number(3));
        //     SyntaxTreeNode n2 = node.addChild(new Number(4));
        //     SyntaxTreeNode n3 = node.addChild(new Number(5));
        // }

        // node.print();
        // System.out.println("AFTER");
        // SyntaxTreeConstructor.expand(node).print();
        // node.print();
    }
}
