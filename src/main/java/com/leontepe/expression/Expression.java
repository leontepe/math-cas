
package com.leontepe.expression;

import java.util.*;

import com.leontepe.expression.Operator.Associativity;
import com.leontepe.exception.EvaluationException;

public class Expression extends ExpressionElement {
    
    private String expressionString;
    private List<ExpressionElement> elements;

    public Expression(String expressionString) {
        this.expressionString = expressionString;
        this.elements = parseExpressionElements(expressionString);
    }

    public Expression(List<ExpressionElement> elements) {
        this.elements = elements;
        this.expressionString = parseExpressionString(elements);
    }

    public String getStringValue() { return this.expressionString; }
    public List<ExpressionElement> getElements() { return this.elements; }

    private static String parseExpressionString(List<ExpressionElement> elements) {
        String s = "";
        for(ExpressionElement el : elements) {
            s += el.getStringValue();
        }
        return s;
    }

    private static List<ExpressionElement> parseExpressionElements(String s) {
        s = s.replaceAll("\\s+", "");
        List<ExpressionElement> elements = new ArrayList<ExpressionElement>();
        int numberStartIndex = -1;

        for(int i = 0; i < s.length(); i++) {
            String c = s.substring(i, i+1);

            if(isSignCharacter(s, i)) {
                elements.add(new Number(0));
            }
            
            if(numberStartIndex == -1 && Number.isNumberCharacter(c)) {
                    // start reading number
                    numberStartIndex = i;
            }
            else {
                if(!Number.isNumberCharacter(c) && numberStartIndex != -1) {
                    String numberString = s.substring(numberStartIndex, i);
                    elements.add(Number.get(numberString));
                    numberStartIndex = -1;
                }
                if(Operator.isOperator(c)) {
                    elements.add(Operator.get(c));
                }
                else if(Paranthesis.isParanthesis(c)) {
                    elements.add(Paranthesis.get(c));
                }
                else if(Variable.isVariable(c)) {
                    if(i > 0) {
                    String previous = s.substring(i-1, i);
                    if(Number.isNumberCharacter(previous) || Paranthesis.isRightParanthesis(previous)) {
                        elements.add(Operator.get("*"));
                    }
                }
                    elements.add(Variable.get(c));
                }
            }
        }
        if(numberStartIndex != -1) {
            String numberString = s.substring(numberStartIndex, s.length());
            elements.add(Number.get(numberString));
            numberStartIndex = -1; 
        }

        return elements;
    }

    /**
     * Determines if a plus/minus character in an expression string is a sign or an operator.
     */
    private static boolean isSignCharacter(String s, int i) {
        return "+-".contains(s.substring(i, i+1)) &&
            (i == 0 || s.substring(i-1, i).equals(Paranthesis.LEFT_PARANTHESIS.getStringValue()));
    }

    /**
     * Shunting-yard algorithm.
     */
    public List<ExpressionElement> getPostfix() {
        
        List<ExpressionElement> postfix = new ArrayList<ExpressionElement>();
        Stack<ExpressionElement> operatorStack = new Stack<ExpressionElement>();

        for(ExpressionElement el : elements) {
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
        if(!operatorStack.isEmpty() && operatorStack.peek() != Paranthesis.LEFT_PARANTHESIS) {
            Operator peekOperator = (Operator) operatorStack.peek();
            if(peekOperator.getPrecedence() > currentOperator.getPrecedence() ||
                (peekOperator.getPrecedence() == currentOperator.getPrecedence() &&
                peekOperator.getAssociativity() == Associativity.LEFT)) {
                    return true;
            }
        }
        return false;
    }

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
                numberStack.push(operator.operate(operand1, operand2));
            }
            else throw new EvaluationException();
        }
        return numberStack.firstElement();
    }

    public List<Variable> getVariables() {
        List<Variable> variables = new ArrayList<Variable>();
        for(ExpressionElement el : elements) {
            if(el instanceof Variable) {
                variables.add((Variable)el);
            }
        }
        return variables;
    }

    public void printElements() {
        for(ExpressionElement el : elements) {
            System.out.print(el.getStringValue());
        }
        System.out.println();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Expression) {
            Expression ex = (Expression)obj;
            return ex.getElements().equals(this.elements);
        }
        return false;
    }

    public Expression substitute(Variable variable, Number number) {
        for(ExpressionElement el : elements) {
            if(el instanceof Variable) {
                Variable var = (Variable)el;
                if(variable.equals(var)) {
                    int i = elements.indexOf(el);
                    elements.set(i, number);
                }
            }
        }
        return new Expression(elements);
    }

    // List<Expression> or List<ExpressionElement>?

    // Expression("3x^4-5x^2+2x-4").getSummands()
    // => Expression[]: 3x^4, -5x^2, 2x, -4
    // Expression("x^3 - (3+4)x - 4 / (3 / 2)").getSummands()
    // => Expression[]: x^3, -(3+4)x, -4 / (3/2)

    public List<Expression> getSummands() {
        Operator plus = Operator.get("+");
        Operator minus = Operator.get("-");
        List<Expression> summands = new ArrayList<Expression>();
        int start = 0;
        int end = 0;
        for(ExpressionElement el : elements) {
            if(el instanceof Operator) {
                Operator op = (Operator)el;
                if(op.equals(minus) || op.equals(plus)) {
                    List<ExpressionElement> subElements = elements.subList(start, end);
                    summands.add(new Expression(subElements));
                    start = op.equals(minus) ? end : end+1;
                }
            }
            end++;
        }
        summands.add(new Expression(elements.subList(start, elements.size())));

        return summands;
    }

    public boolean contains(ExpressionElement el) {
        return elements.contains(el);
    }

    public boolean containsParantheses() {
        return elements.contains(Paranthesis.LEFT_PARANTHESIS) || elements.contains(Paranthesis.RIGHT_PARANTHESIS);
    }

}