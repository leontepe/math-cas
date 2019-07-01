package com.leontepe;

import com.leontepe.expression.Constant;
import com.leontepe.expression.Expression;
import com.leontepe.expression.Operator;
import com.leontepe.expression.Parenthesis;
import com.leontepe.expression.SyntaxTreeConstructor;
import com.leontepe.expression.SyntaxTreeNode;
import com.leontepe.function.TrigonometricFunction;

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
        for(ExpressionElement el : NotationConverter.infixToPostfix(infix3)) {
            System.out.print("[" + el + "]");
        }
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
