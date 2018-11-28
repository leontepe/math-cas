
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
        List<ExpressionElement> elements = new ArrayList<ExpressionElement>();
        int numberStartIndex = -1;
        for(int i = 0; i < expressionString.length(); i++) {
            char c = expressionString.charAt(i);
            if(Number.isNumberCharacter(c) && numberStartIndex == -1) {
                numberStartIndex = i;
            }
            else if(Operator.isOperator(c) && numberStartIndex != -1) {
                String number = expressionString.substring(numberStartIndex, i);
                if(number.contains(String.valueOf(Number.DECIMAL_SEPARATOR))) {
                    elements.add(new Number<Double>(Double.parseDouble(number)));
                }
                else {
                    elements.add(new Number<Integer>(Integer.parseInt(number)));
                }
                numberStartIndex = -1;
                elements.add(Operator.get(c));
            }
            else {
                System.out.print("Unexpected");
            }
        }
    }

    /**
     * Shunting-yard algorithm.
     */
    public String getPostfix() {
        
        // Remove whitespace.
        String infix = expressionString.replaceAll("\\s+", "");
        String postfix = "";

        Stack<ExpressionElement> operatorStack = new Stack<ExpressionElement>();

        for(char c : infix.toCharArray()) {
            if(Operator.isOperator(c)) {
                Operator currentOperator = Operator.get(c);
                while(mustPopStack(operatorStack, currentOperator))
                {
                    postfix += ((Operator)operatorStack.pop()).getCharacter();
                }
                operatorStack.push(currentOperator);
            }
            else if(Bracket.isLeftBracket(c)) {
                operatorStack.push(Bracket.LEFT_BRACKET);
            }
            else if(Bracket.isRightBracket(c)) {
                while(!operatorStack.isEmpty() &&
                    operatorStack.peek() != Bracket.LEFT_BRACKET) {
                    postfix += ((Operator)(operatorStack.pop())).getCharacter();
                }
                // Pop left bracket from the stack.
                operatorStack.pop();
            }
            else if(Character.isDigit(c)) {
                postfix += c;
            }
            else {
                System.out.println("Infix expression is invalid. Cannot parse to postfix.");
            }
        }
        while(!operatorStack.isEmpty()) {
            ExpressionElement popped = operatorStack.pop();
            if(popped instanceof Operator) {
                postfix += ((Operator)popped).getCharacter();
            }
            else if(popped instanceof Bracket) {
                postfix += ((Bracket)popped).getCharacter();
            }
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

}