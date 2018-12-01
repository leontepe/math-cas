
package com.leontepe;

import java.util.*;

import com.leontepe.Operator.Associativity;

public class Expression {
    
    private String expressionString;

    public Expression(String expressionString) {
        this.expressionString = expressionString;
    }

    public String getInfix() { return this.expressionString; }

    public List<ExpressionElement> getExpressionElements() {
        String ex = expressionString.replaceAll("\\s+", "");
        List<ExpressionElement> elements = new ArrayList<ExpressionElement>();
        int numberStartIndex = -1;

        for(int i = 0; i < ex.length(); i++) {
            String c = ex.substring(i, i+1);

            if(numberStartIndex == -1 && (Number.isNumberCharacter(c) || isSignCharacter(ex, i))) {
                    // start reading number
                    numberStartIndex = i;
            }
            else {
                if(!Number.isNumberCharacter(c) && numberStartIndex != -1) {
                    String numberString = ex.substring(numberStartIndex, i);
                    elements.add(Number.get(numberString));
                    numberStartIndex = -1;
                }

                if(Operator.isOperator(c)) {
                    elements.add(Operator.get(c));
                }
                else if(Bracket.isBracket(c)) {
                    elements.add(Bracket.get(c));
                }
            }
        }
        if(numberStartIndex != -1) {
            String numberString = ex.substring(numberStartIndex, ex.length());
            elements.add(Number.get(numberString));
            numberStartIndex = -1; 
        }

        return elements;
    }

    /**
     * Determines if a plus/minus character in an expression string is a sign or an operator.
     */
    private boolean isSignCharacter(String ex, int i) {
        return "+-".contains(ex.substring(i, i+1)) &&
            (i == 0 || ex.substring(i-1, i).equals(Bracket.LEFT_BRACKET.getStringValue()));
    }

    /**
     * Shunting-yard algorithm.
     */
    @SuppressWarnings("rawtypes")
    public List<ExpressionElement> getPostfix() {
        
        List<ExpressionElement> infix = getExpressionElements();
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
            else if(el instanceof Bracket) {
                Bracket bracket = (Bracket)el;
                if(bracket == Bracket.LEFT_BRACKET) {
                    operatorStack.push(Bracket.LEFT_BRACKET);
                }
                else if(bracket == Bracket.RIGHT_BRACKET) {
                    while(!operatorStack.isEmpty() &&
                        operatorStack.peek() != Bracket.LEFT_BRACKET) {
                        postfix.add(((Operator)operatorStack.pop()));
                    }
                    // Pop left bracket from the stack.
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

    private boolean mustPopStack(Stack<ExpressionElement> operatorStack, Operator currentOperator) {
        if(!operatorStack.isEmpty() && operatorStack.peek() != Bracket.LEFT_BRACKET) {
            Operator peekOperator = (Operator) operatorStack.peek();
            if(peekOperator.getPrecedence() > currentOperator.getPrecedence() ||
                (peekOperator.getPrecedence() == currentOperator.getPrecedence() &&
                peekOperator.getAssociativity() == Associativity.LEFT)) {
                    return true;
            }
        }
        return false;
    }

    @SuppressWarnings("rawtypes")
    public Number evaluate() {
        Stack<Number> numberStack = new Stack<Number>();
        List<ExpressionElement> postfix = getPostfix();
        for(ExpressionElement el : postfix) {
            if(el instanceof Number) {
                Number number = (Number)el;
                numberStack.push(number);
            }
            else if(el instanceof Operator) {
                Operator operator = (Operator)el;
                Number operand2 = numberStack.pop();
                Number operand1 = numberStack.pop();
                numberStack.push(operator.operate(op1, op2))
            }
            else if(Operator.getStringValue(c)) {
                int op2 = numberStack.pop();
                int op1 = numberStack.pop();
                numberStack.push(Operator.get(c).operate(op1, op2));
            }
            else {
                System.out.println("Unexpected");
            }
        }
        return numberStack.firstElement();
    }

}