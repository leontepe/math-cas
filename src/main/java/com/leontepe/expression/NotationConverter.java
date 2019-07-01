package com.leontepe.expression;

import com.leontepe.expression.Operator.BinaryOperator;
import com.leontepe.function.Function;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class NotationConverter {

    /**
     * Converts a token list from infix to postfix notation. Basic idea: shunting
     * yard algorithm.
     */
    public static List<ExpressionElement> infixToPostfix(List<ExpressionElement> infix) {

        // prepare for postfix conversion
        /*
         * List<ExpressionElement> elementsCopy = new
         * ArrayList<ExpressionElement>(elements); int offset = 0; for(int i = 0; i <
         * elementsCopy.size(); i++) { ExpressionElement el = elementsCopy.get(i);
         * if(el.equals(Operator.get("-"))) {
         * if(elementsCopy.get(i+1).equals(Paranthesis.LEFT_PARANTHESIS)) {
         * elements.set(i + offset, new Number(-1)); elements.add(i + offset + 1,
         * Operator.get("*")); offset++; } else if(i == 0 ||
         * elementsCopy.get(i-1).equals(Paranthesis.LEFT_PARANTHESIS)) {
         * elements.add(i-1, new Number(0)); offset++; } } }
         */

        List<ExpressionElement> postfix = new ArrayList<ExpressionElement>();
        Stack<ExpressionElement> operatorStack = new Stack<ExpressionElement>();

        for (ExpressionElement el : infix) {
            if (el instanceof Operator) {
                Operator currentOperator = (Operator) el;

                // Pop operators on stack with higher precedence
                while (mustPopStack(operatorStack, currentOperator)) {
                    postfix.add((Operator) operatorStack.pop());
                }
                operatorStack.push(currentOperator);
            }
            else if (el instanceof Function) {
                Function f = (Function) el;
                operatorStack.push(f);
            }
            else if (el instanceof Parenthesis) {
                Parenthesis par = (Parenthesis) el;
                if (par == Parenthesis.LEFT_PARENTHESIS) {
                    operatorStack.push(Parenthesis.LEFT_PARENTHESIS);
                }
                else if (par == Parenthesis.RIGHT_PARENTHESIS) {
                    while (!operatorStack.isEmpty() && operatorStack.peek() != Parenthesis.LEFT_PARENTHESIS) {
                        postfix.add(((Operator) operatorStack.pop()));
                    }
                    // Pop left parenthesis into oblivion
                    operatorStack.pop();

                    // If a function is at the top of the operator stack, pop it (then it is a
                    // function call)
                    if (!operatorStack.isEmpty() && operatorStack.peek() instanceof Function && !(operatorStack.peek() instanceof Operator)) {
                        Function f = (Function) operatorStack.pop();
                        postfix.add(f);
                    }
                }

            }
            else if (el instanceof Variable || el instanceof Number) {
                postfix.add(el);
            }
        }

        // Pop all remaining operators
        while (!operatorStack.isEmpty()) {
            postfix.add(operatorStack.pop());
        }

        return postfix;
    }

    private static boolean mustPopStack(Stack<ExpressionElement> opStack, Operator currentOp) {
        if (!opStack.isEmpty() && opStack.peek() != Parenthesis.LEFT_PARENTHESIS) {
            Operator peekOp = (Operator) opStack.peek();
            // Pop if precedence is higher
            if (peekOp.getPrecedence() > currentOp.getPrecedence()) {
                return true;
            }
            else if (peekOp.getArity() == 2) {
                BinaryOperator peekOpBinary = (BinaryOperator) peekOp;
                if ((peekOpBinary.getPrecedence() == currentOp.getPrecedence()
                        && peekOpBinary.getAssociativity() == Operator.Associativity.LEFT)) {
                    return true;
                }
            }
        }
        return false;
    }
}