
package com.leontepe;

import java.util.*;

import com.leontepe.Operator.Associativity;

public class Expression {
    
    private String expressionString;

    public Expression(String expressionString) {
        this.expressionString = expressionString;
    }

    public String getInfix() { return this.expressionString; }
    public String getPostfix() { return infixToPostfix(this.expressionString); }

    public int evaluate() {
        Stack<Integer> valueStack = new Stack<Integer>();
        for(char c : getPostfix().toCharArray()) {
            if(Character.isDigit(c)) {
                valueStack.push(Character.getNumericValue(c));
            }
            else if(Operator.isOperator(c)) {
                int op2 = valueStack.pop();
                int op1 = valueStack.pop();
                valueStack.push(Operator.get(c).operate(op1, op2));
            }
            else {
                System.out.println("Unexpected");
            }
        }
        return valueStack.firstElement();
    }

    // public double evaluate() {
    //     // remove whitespace
    //     String ex = expressionString.replaceAll("\\s+", "");
    // }

    /**
     * Shunting-yard algorithm.
     */
    public static String infixToPostfix(String infix) {
        // Remove whitespace.
        infix = infix.replaceAll("\\s+", "");
        
        String postfix = "";
        Stack<Operator> operatorStack = new Stack<Operator>();

        for(char c : infix.toCharArray()) {
            if(Operator.isOperator(c)) {
                Operator currentOperator = Operator.get(c);
                while(
                    !operatorStack.isEmpty() && operatorStack.peek() != Operator.LEFT_BRACKET &&
                    (operatorStack.peek().getPrecedence() > currentOperator.getPrecedence() ||
                    (operatorStack.peek().getPrecedence() == currentOperator.getPrecedence()) &&
                        operatorStack.peek().getAssociativity() == Associativity.LEFT))
                {
                    postfix += operatorStack.pop().getCharacter();
                }
                operatorStack.push(currentOperator);
            }
            else if(Operator.isLeftBracket(c)) {
                operatorStack.push(Operator.LEFT_BRACKET);
            }
            else if(Operator.isRightBracket(c)) {
                while(!operatorStack.isEmpty() &&
                    operatorStack.peek() != Operator.LEFT_BRACKET) {
                    postfix += operatorStack.pop().getCharacter();
                }
                // Pop left bracket from the stack.
                operatorStack.pop().getCharacter();
            }
            else if(Character.isDigit(c)) {
                postfix += c;
            }
            else {
                System.out.println("Infix expression is invalid. Cannot parse to postfix.");
            }
        }
        while(!operatorStack.isEmpty()) {
            postfix += operatorStack.pop().getCharacter();
        }

        return postfix;
    }

}