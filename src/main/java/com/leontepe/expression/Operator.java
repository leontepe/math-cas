
package com.leontepe.expression;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.*;

public abstract class Operator extends ExpressionElement {

    /**
     * The number of arguments an operator can take.
     * (https://en.wikipedia.org/wiki/Arity)
     */
    public enum Arity {
        UNARY, BINARY
    }

    public enum Associativity {
        LEFT, RIGHT
    }

    public interface IUnaryOperation {
        abstract Number perform(Number op1);
    }

    public interface IBinaryOperation {
        Number perform(Number op1, Number op2);
    }

    public static class UnaryOperator extends Operator {

        private IUnaryOperation operation;

        public UnaryOperator(char operatorChar, int precedence, IUnaryOperation operation) {
            super(operatorChar, precedence, Arity.UNARY);
            this.operation = operation;
        }

        public Number operate(Number op) {
            return this.operation.perform(op);
        }
    }

    public static class BinaryOperator extends Operator {

        private IBinaryOperation operation;
        private Associativity associativity;

        public BinaryOperator(char operatorChar, int precedence, Associativity associativity, IBinaryOperation operation) {
            super(operatorChar, precedence, Arity.BINARY);
            this.associativity = associativity;
            this.operation = operation;
        }

        public Associativity getAssociativity() {
            return this.associativity;
        }

        public Number operate(Number op1, Number op2) {
            return this.operation.perform(op1, op2);
        }
    }

    public static class MultiaryOperator extends BinaryOperator {

        public MultiaryOperator(char operatorChar, int precedence, Associativity associativity, IBinaryOperation operation) {
            super(operatorChar, precedence, associativity, operation);
        }

        public Number operate(Number op1, Number op2, Number[] ops) {
            Number result = operate(op1, op2);
            for(int i = 0; i < ops.length; i++) {
                result = operate(result, ops[i]);
            }
            return result;
        }
    }

    public static final MultiaryOperator ADD = new MultiaryOperator('+', 0, Associativity.LEFT, (op1, op2) -> { return new Number(op1.getValue() + op2.getValue()); });
    public static final BinaryOperator SUBTRACT = new BinaryOperator('-', 0, Associativity.LEFT, (op1, op2) -> { return new Number(op1.getValue() - op2.getValue()); });

    public static final MultiaryOperator MULTIPLY = new MultiaryOperator('*', 1, Associativity.LEFT, (op1, op2) -> { return new Number(op1.getValue() * op2.getValue()); });
    public static final BinaryOperator DIVIDE = new BinaryOperator('/', 1, Associativity.LEFT, (op1, op2) -> { return new Number(op1.getValue() / op2.getValue()); });
    public static final BinaryOperator EXPONENTIATE = new BinaryOperator('^', 2, Associativity.RIGHT, (op1, op2) -> { return new Number(Math.pow(op1.getValue(), op2.getValue())); });

    public static final UnaryOperator NEGATE = new UnaryOperator('-', 2, (op) -> { return new Number(-op.getValue()); });
    public static final UnaryOperator UNARY_PLUS = new UnaryOperator('+', 2, (op) -> { return new Number(op.getValue()); });

    private char operatorChar;
    private int precedence;
    private Arity arity;

    private Operator(char operatorChar, int precedence, Arity arity) {
        this.operatorChar = operatorChar;
        this.precedence = precedence;
        this.arity = arity;
    }

    public char getOperatorChar() { return this.operatorChar; }
    public int getPrecedence() { return this.precedence; }
    public Arity getArity() { return this.arity; }

    public static Operator get(char c, Arity arity) {
        List<Operator> ops = getOperators(arity);
        for(Operator op : ops) {
            if(op.getOperatorChar() == c) {
                return op;
            }
        }
        return null;
    }

    public static List<Operator> getOperators() {
        return getOperators(null);
    }

    public static List<Operator> getOperators(Arity arity) {
        // List<Field> fields = Arrays.stream(Operator.class.getFields()).collect(Collectors.toList());
        List<Operator> operators = new ArrayList<Operator>();
        for(Field f : Operator.class.getFields()) {
            try {
                Object possibleOperator = f.get(null);
                if(Operator.class.isAssignableFrom(possibleOperator.getClass())) {
                    Operator op = (Operator)possibleOperator;
                    if(arity == null || op.getArity() == arity) {
                        operators.add(op);
                    }
                }
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return operators;
    }

    public static boolean isOperator(char c) {
        return get(c, null) != null;
    }

    @Override
    public String getStringValue() { return String.valueOf(this.operatorChar); }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Operator) {
            Operator op = (Operator)obj;
            return op.getArity() == this.arity && op.getOperatorChar() == operatorChar;
        }
        return false;
    }
    
}