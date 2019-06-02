package com.leontepe;

import com.leontepe.expression.*;
import com.leontepe.expression.Number;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class NotationConverter {

    /**
     * Converts an expression element list (i.e. token list) in infix notation to postfix notation.
     * 
     * Example: 3+4*2 => 342*+
     */
    public static List<ExpressionElement> infixToPostfix(List<ExpressionElement> infix) {

        // prepare for postfix conversion
        /*
        List<ExpressionElement> elementsCopy = new ArrayList<ExpressionElement>(elements);
        int offset = 0;
        for(int i = 0; i < elementsCopy.size(); i++) {
            ExpressionElement el = elementsCopy.get(i);
            if(el.equals(Operator.get("-"))) {
                if(elementsCopy.get(i+1).equals(Paranthesis.LEFT_PARANTHESIS)) {
                    elements.set(i + offset, new Number(-1));
                    elements.add(i + offset + 1, Operator.get("*"));
                    offset++;
                }
                else if(i == 0 || elementsCopy.get(i-1).equals(Paranthesis.LEFT_PARANTHESIS)) {
                    elements.add(i-1, new Number(0));
                    offset++;
                }
            }
        }
        */
        
        List<ExpressionElement> postfix = new ArrayList<ExpressionElement>();
        Stack<ExpressionElement> operatorStack = new Stack<ExpressionElement>();

        for(ExpressionElement el : infix) {
            if(el instanceof Operator) {
                Operator currentOperator = (Operator)el;
                while(mustPopStack(operatorStack, currentOperator))
                {
                    postfix.add(((Operator)operatorStack.pop()));
                }
                operatorStack.push(currentOperator);
            }
            else if(el instanceof Paranthesis) {
                Paranthesis par = (Paranthesis)el;
                if(par == Paranthesis.LEFT_PARANTHESIS) {
                    operatorStack.push(Paranthesis.LEFT_PARANTHESIS);
                }
                else if(par == Paranthesis.RIGHT_PARANTHESIS) {
                    while(!operatorStack.isEmpty() &&
                        operatorStack.peek() != Paranthesis.LEFT_PARANTHESIS) {
                        postfix.add(((Operator)operatorStack.pop()));
                    }
                    operatorStack.pop();
                }
                
            }
            else if(el instanceof Number) {
                Number number = (Number)el;
                postfix.add(number);
            }
        }
        while(!operatorStack.isEmpty()) {
            ExpressionElement popped = operatorStack.pop();
            postfix.add(popped);
        }

        return postfix;
    }

    private static boolean mustPopStack(Stack<ExpressionElement> operatorStack, Operator currentOperator) {
        if(!operatorStack.isEmpty() && operatorStack.peek() != Paranthesis.LEFT_PARANTHESIS) {
            Operator peekOperator = (Operator) operatorStack.peek();
            if(peekOperator.getPrecedence() > currentOperator.getPrecedence() ||
                (peekOperator.getPrecedence() == currentOperator.getPrecedence() &&
                peekOperator.getAssociativity() == Operator.Associativity.LEFT)) {
                    return true;
            }
        }
        return false;
    }
}