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

        String T1 = "";
        for (int i = 0, aux = 0; i < 25; i++) {
            aux = '\u03B1' + i;
            T1 += Character.toString((char) aux) + " ";
        }
        System.out.println(T1);

        String T2 = "";
        for (int i = 0, aux = 0; i < 25; i++) {
            aux = '\u0391' + i;
            T2 += Character.toString((char) aux) + " ";
        }
        System.out.println(T2);

        new Expression("3+cos(4)*ln(2+ln(3))").printSyntaxTree();
    }
}
