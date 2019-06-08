
package com.leontepe.expression;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.reflect.*;

public class Operator extends ExpressionElement {

    /**
     * The number of arguments an operator can take.
     * (https://en.wikipedia.org/wiki/Arity)
     */
    public enum Arity {
        UNARY, BINARY
    }

    public enum Associativity {
        LEFT, RIGHT, NONE
    }

    public interface IUnaryOperation {
        Number perform(Number op1);
    }

    public interface IBinaryOperation {
        Number perform(Number op1, Number op2);
    }

    public static class UnaryOperator extends Operator {

        private IUnaryOperation operation;

        public UnaryOperator(String stringValue, int precedence, Associativity associativity, IUnaryOperation operation) {
            super(stringValue, precedence, associativity, Arity.UNARY);
            this.operation = operation;
        }

        public Number operate(Number op) {
            return this.operation.perform(op);
        }
    }

    public static class BinaryOperator extends Operator {

        private IBinaryOperation operation;

        public BinaryOperator(String stringValue, int precedence, Associativity associativity, IBinaryOperation operation) {
            super(stringValue, precedence, associativity, Arity.BINARY);
            this.operation = operation;
        } 

        public Number operate(Number op1, Number op2) {
            return this.operation.perform(op1, op2);
        }
    }

    // @SuppressWarnings("serial")
    // private static final ArrayList<Operator> operators = new ArrayList<Operator>() {{
    //     add(new Operator("+", 0, Associativity.LEFT, (op1, op2) -> { return new Number(op1.getValue() + op2.getValue()); }));
    //     add(new Operator("-", 0, Associativity.LEFT, (op1, op2) -> { return new Number(op1.getValue() - op2.getValue()); })); 
    //     add(new Operator("*", 1, Associativity.LEFT, (op1, op2) -> { return new Number(op1.getValue() * op2.getValue()); }));
    //     add(new Operator("/", 1, Associativity.LEFT, (op1, op2) -> { return new Number(op1.getValue() / op2.getValue()); }));
    //     add(new Operator("^", 2, Associativity.RIGHT, (op1, op2) -> { return new Number(Math.pow(op1.getValue(), op2.getValue())); }));
    // }};

    public static final BinaryOperator ADD = new BinaryOperator("+", 0, Associativity.LEFT, (op1, op2) -> { return new Number(op1.getValue() + op2.getValue()); });
    public static final BinaryOperator SUBTRACT = new BinaryOperator("-", 0, Associativity.LEFT, (op1, op2) -> { return new Number(op1.getValue() - op2.getValue()); });
    public static final BinaryOperator MULTIPLY = new BinaryOperator("*", 1, Associativity.LEFT, (op1, op2) -> { return new Number(op1.getValue() * op2.getValue()); });
    public static final BinaryOperator DIVIDE = new BinaryOperator("/", 1, Associativity.LEFT, (op1, op2) -> { return new Number(op1.getValue() / op2.getValue()); });
    public static final BinaryOperator EXPONENTIATE = new BinaryOperator("^", 2, Associativity.RIGHT, (op1, op2) -> { return new Number(Math.pow(op1.getValue(), op2.getValue())); });

    public static final UnaryOperator NEGATE = new UnaryOperator("-", 0, Associativity.LEFT, (op) -> { return new Number(-op.getValue()); });
    public static final UnaryOperator UNARY_PLUS = new UnaryOperator("+", 0, Associativity.LEFT, (op) -> { return new Number(op.getValue()); });

    private String stringValue;
    private int precedence;
    private Associativity associativity;
    private Arity arity;

    private Operator(String stringValue, int precedence, Associativity associativity, Arity arity) {
        this.stringValue = stringValue;
        this.precedence = precedence;
        this.associativity = associativity;
        this.arity = arity;
    }

    public String getStringValue() { return this.stringValue; }
    public int getPrecedence() { return this.precedence; }
    public Associativity getAssociativity() { return this.associativity; }

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

    public static List<Operator> getOperators() {
        // List<Field> fields = Arrays.stream(Operator.class.getFields()).collect(Collectors.toList());
        List<Operator> operators = new ArrayList<Operator>();
        for(Field f : Operator.class.getFields()) {
            try {
                if(Operator.class.isAssignableFrom(f.get(null).getClass())) {
                    operators.add((Operator)f.get(null));
                }
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return operators;
    }

    public static boolean isOperator(String s) {
        return getOperator(s) != null;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Operator) {
            Operator op = (Operator)obj;
            return op.getStringValue().equals(this.stringValue);
        }
        return false;
    }
}