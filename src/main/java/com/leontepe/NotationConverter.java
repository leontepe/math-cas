package com.leontepe;

import com.leontepe.expression.*;
import com.leontepe.expression.Number;
import com.leontepe.expression.Operator.Arity;
import com.leontepe.expression.Operator.BinaryOperator;
import com.leontepe.expression.Operator.UnaryOperator;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class NotationConverter {

    /**
     * Converts a token list from infix to postfix notation.
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
                // 2 * 3 + 4
                // 23*4+

                // -2^3
                // 23^-

                // 2^-3
                // 23-^

                // Pop operators on stack with higher precedence
                while(mustPopStack(operatorStack, currentOperator))
                {
                    postfix.add((Operator)operatorStack.pop());
                }
                operatorStack.push(currentOperator);
            }
            else if(el instanceof Parenthesis) {
                Parenthesis par = (Parenthesis)el;
                if(par == Parenthesis.LEFT_PARENTHESIS) {
                    operatorStack.push(Parenthesis.LEFT_PARENTHESIS);
                }
                else if(par == Parenthesis.RIGHT_PARENTHESIS) {
                    while(!operatorStack.isEmpty() &&
                        operatorStack.peek() != Parenthesis.LEFT_PARENTHESIS) {
                        postfix.add(((Operator)operatorStack.pop()));
                    }
                    operatorStack.pop();
                }
                
            }
            else if(el instanceof Number) {
                Number num = (Number)el;
                postfix.add(num);
            }
            else if(el instanceof Variable) {
                Variable var = (Variable)el;
                postfix.add(var);
            }
        }

        // Pop all remaining operators
        while(!operatorStack.isEmpty()) {
            ExpressionElement popped = operatorStack.pop();
            postfix.add(popped);
        }

        return postfix;
    }

    // Unary operators = highest precedence?
    // Unary operators should not pop binary ones (true?)
    // Can binary operators pop unary ones?
    //
    // a^-b --> ab-^
    // -a^b --> ab^-
    // 
    private static boolean mustPopStack(Stack<ExpressionElement> opStack, Operator currentOp) {
        if(!opStack.isEmpty() && opStack.peek() != Parenthesis.LEFT_PARENTHESIS) {
            Operator peekOp = (Operator) opStack.peek();
            if(peekOp.getArity() == Arity.UNARY) {
                UnaryOperator peekOpUnary = (UnaryOperator)peekOp;
                if(currentOp.getArity() == Arity.UNARY && peekOpUnary.getPrecedence() > currentOp.getPrecedence()) {
                    return true;
                }
            }
            else if(peekOp.getArity() == Arity.BINARY) {
                BinaryOperator peekOpBinary = (BinaryOperator)peekOp;
                if(peekOpBinary.getPrecedence() > currentOp.getPrecedence() ||
                    (peekOpBinary.getPrecedence() == currentOp.getPrecedence() &&
                    peekOpBinary.getAssociativity() == Operator.Associativity.LEFT)) {
                    return true;
                }
            }


        }
        return false;
    }
}