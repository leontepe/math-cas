
package com.leontepe.expression;

import java.util.List;
import java.util.ArrayList;

public class Operator extends ExpressionElement {

    public static enum Associativity {
        LEFT, RIGHT
    }

    @SuppressWarnings("serial")
    private static final List<Operator> operators = new ArrayList<Operator>() {{
        add(new Operator("+", 0, Associativity.LEFT, (op1, op2) -> { return new Number(op1.getValue() + op2.getValue()); }));
        add(new Operator("-", 0, Associativity.LEFT, (op1, op2) -> { return new Number(op1.getValue() - op2.getValue()); })); 
        add(new Operator("*", 1, Associativity.LEFT, (op1, op2) -> { return new Number(op1.getValue() * op2.getValue()); }));
        add(new Operator("/", 1, Associativity.LEFT, (op1, op2) -> { return new Number(op1.getValue() / op2.getValue()); }));
        add(new Operator("^", 2, Associativity.RIGHT, (op1, op2) -> { return new Number(Math.pow(op1.getValue(), op2.getValue())); }));
    }};

    private String stringValue;

    private int precedence;
    private Associativity associativity;
    private IOperator operator;

    private Operator(String stringValue, int precedence, Associativity associativity, IOperator operator) {
        this.stringValue = stringValue;
        this.precedence = precedence;
        this.associativity = associativity;
        this.operator = operator;
    }

    public String getStringValue() { return this.stringValue; }
    public int getPrecedence() { return this.precedence; }
    public Associativity getAssociativity() { return this.associativity; }
    public Number operate(Number op1, Number op2) { return this.operator.operate(op1, op2); }

    public static Operator get(String s) {
        Operator op = getOperator(s);
        if(op != null) return op;
        else throw new IllegalArgumentException();
    }

    private static Operator getOperator(String s) {
        Operator operator = null;
        for(Operator op : operators) {
            if(op.getStringValue().equals(s)) {
                operator = op;
            }
        }
        return operator;
    }

    public static boolean isOperator(String s) {
        return getOperator(s) != null;
    }
}