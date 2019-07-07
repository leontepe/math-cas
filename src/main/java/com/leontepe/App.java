package com.leontepe;

import com.leontepe.exception.AmbiguousSyntaxException;
import com.leontepe.exception.InvalidSyntaxException;
import com.leontepe.exception.NotImplementedException;
import com.leontepe.expression.Constant;
import com.leontepe.expression.Equation;
import com.leontepe.expression.Expression;
import com.leontepe.expression.Operator;
import com.leontepe.expression.Parenthesis;
import com.leontepe.expression.SyntaxTreeConstructor;
import com.leontepe.expression.SyntaxTreeNode;
import com.leontepe.function.TrigonometricFunction;

import java.util.List;
import java.io.Console;
import java.util.ArrayList;

import com.leontepe.expression.ExpressionElement;
import com.leontepe.expression.ExpressionTokenizer;
import com.leontepe.expression.NotationConverter;
import com.leontepe.expression.Number;

public class App {

    private static Console console;
    private static MathContext context;

    public static void main(String[] args) throws Exception {

        console = System.console();
        context = new MathContext();

        console.writer().println(
                "New math context initialized. Start typing in math equations with both sides filled in for a definition and only one for a solve statement.");
        String input = "";
        while (true) {
            input = console.readLine();

            if (input.equals("exit")) {
                break;
            }

            try {
                context.parseEquation(input);
            }
            catch (InvalidSyntaxException ex) {
                ex.printStackTrace(console.writer());
            }
            catch (AmbiguousSyntaxException ex) {
                ex.printStackTrace(console.writer());
            }
            catch (NotImplementedException ex) {
                // console.writer().println("Error: Not implemented (" + ex.getMessage() + ")");
                ex.printStackTrace(console.writer());
            }

            String output = context.getOutput();
            if (!output.isEmpty()) {
                console.writer().println(output);
            }
        }

        // String T1 = "";
        // for (int i = 0, aux = 0; i < 25; i++) {
        // aux = '\u03B1' + i;
        // T1 += Character.toString((char) aux) + " ";
        // }
        // System.out.println(T1);

        // String T2 = "";
        // for (int i = 0, aux = 0; i < 25; i++) {
        // aux = '\u0391' + i;
        // T2 += Character.toString((char) aux) + " ";
        // }
        // System.out.println(T2);

        // new Expression("3+cos(4)*ln(2+ln(3))").printSyntaxTree();

    }

    // public static void handleInput(String input) {
    //     input = input.replaceAll("\\s+", "");
    //     if (input.endsWith("=")) {
    //         Expression ex = new Expression(input.substring(0, input.length()-1));
    //         if (ex.isEvaluatable()) {
    //             console.writer().print(ex.evaluate());
    //             console.writer().println();
    //         }
    //     }
    //     else {
    //         String[] expressionStrings = input.split("=");
    //         Expression leftHandSide = new Expression(context, expressionStrings[0]);
    //         Expression rightHandSide = new Expression(context, expressionStrings[1]);
    //         Equation equation = new Equation(leftHandSide, rightHandSide);
    //     }
    // }
}
