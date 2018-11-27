
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

    // public double evaluate() {
    //     // remove whitespace
    //     String ex = expressionString.replaceAll("\\s+", "");
    // }

    /**
     * Shunting-yard algorithm.
     */
    public static String infixToPostfix(String infix) {
        infix = infix.replaceAll("\\s+", "");
        String postfix = "";
        Stack<Operator> operatorStack = new Stack<Operator>();

        for(char c : infix.toCharArray()) {
            if(Operator.isOperator(c)) {
                Operator currentOperator = Operator.get(c);
                while(
                    !operatorStack.isEmpty() &&
                    (operatorStack.peek().getPrecedence() > currentOperator.getPrecedence() ||
                    (operatorStack.peek().getPrecedence() == currentOperator.getPrecedence()) &&
                        operatorStack.peek().getAssociativity() == Associativity.LEFT))
                {
                    postfix += operatorStack.pop().getCharacter();
                }
                operatorStack.push(currentOperator);
            }
            else if(Character.isDigit(c)) {
                postfix += c;
            }
            else if(c == '(') {

            }
            else if(c == ')') {
                
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