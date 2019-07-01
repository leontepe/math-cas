
package com.leontepe.expression;

import java.util.ArrayList;
import java.util.List;

import com.leontepe.Factorial;
import com.leontepe.function.Function;

import java.lang.reflect.*;

public abstract class Operator extends Function {

    public enum Associativity {
        LEFT, RIGHT
    }

    public enum NotationType {
        PREFIX, INFIX, POSTFIX
    }

    public static class UnaryOperator extends Operator {

        public interface IUnaryOperation {
            abstract Number perform(Number op1);
        }

        private IUnaryOperation operation;

        public UnaryOperator(char operatorChar, NotationType notationType, int precedence, IUnaryOperation operation) {
            super(operatorChar, 1, notationType, precedence);
            this.operation = operation;
        }

        public Number operate(Number op) {
            return this.operation.perform(op);
        }

        @Override
        public Number apply(Number[] arguments) {
            super.apply(arguments);
            return operate(arguments[0]);
        }

    }

    public static class BinaryOperator extends Operator {

        public interface IBinaryOperation {
            Number perform(Number op1, Number op2);
        }

        private IBinaryOperation operation;
        private Associativity associativity;

        public BinaryOperator(char operatorChar, int precedence, Associativity associativity,
                IBinaryOperation operation) {
            super(operatorChar, 2, NotationType.INFIX, precedence);
            this.associativity = associativity;
            this.operation = operation;
        }

        public Associativity getAssociativity() {
            return this.associativity;
        }

        public Number operate(Number op1, Number op2) {
            return this.operation.perform(op1, op2);
        }

        @Override
        public Number apply(Number[] arguments) {
            super.apply(arguments);
            return operate(arguments[0], arguments[1]);
        }
    }

    public static class MultiaryOperator extends BinaryOperator {

        public MultiaryOperator(char operatorChar, int precedence, Associativity associativity,
                IBinaryOperation operation) {
            super(operatorChar, precedence, associativity, operation);
        }

        public Number operate(Number op1, Number op2, Number[] ops) {
            Number result = super.operate(op1, op2);
            for (int i = 0; i < ops.length; i++) {
                result = super.operate(result, ops[i]);
            }
            return result;
        }
    }

    public static final MultiaryOperator ADD = new MultiaryOperator('+', 0, Associativity.LEFT, (op1, op2) -> {
        return new Number(op1.getValue() + op2.getValue());
    });
    public static final BinaryOperator SUBTRACT = new BinaryOperator('-', 0, Associativity.LEFT, (op1, op2) -> {
        return new Number(op1.getValue() - op2.getValue());
    });

    public static final MultiaryOperator MULTIPLY = new MultiaryOperator('*', 1, Associativity.LEFT, (op1, op2) -> {
        return new Number(op1.getValue() * op2.getValue());
    });
    public static final BinaryOperator DIVIDE = new BinaryOperator('/', 1, Associativity.LEFT, (op1, op2) -> {
        return new Number(op1.getValue() / op2.getValue());
    });
    public static final BinaryOperator EXPONENTIATE = new BinaryOperator('^', 2, Associativity.RIGHT, (op1, op2) -> {
        return new Number(Math.pow(op1.getValue(), op2.getValue()));
    });

    public static final UnaryOperator NEGATE = new UnaryOperator('-', NotationType.PREFIX, 2, (op) -> {
        return new Number(-op.getValue());
    });
    public static final UnaryOperator UNARY_PLUS = new UnaryOperator('+', NotationType.PREFIX, 2, (op) -> {
        return new Number(op.getValue());
    });

    public static final UnaryOperator FACTORIAL = new UnaryOperator('!', NotationType.POSTFIX, 3, (op) -> {
        return new Number(Factorial.factorial((long) op.getValue()));
    });

    private char operatorChar;
    private NotationType notationType;
    private int precedence;

    private Operator(char operatorChar, int arity, NotationType notationType, int precedence) {
        super(null, null, arity);
        this.operatorChar = operatorChar;
        this.notationType = notationType;
        this.precedence = precedence;
    }

    public char getChar() {
        return this.operatorChar;
    }

    public int getPrecedence() {
        return this.precedence;
    }

    public NotationType getNotationType() {
        return this.notationType;
    }

    public static Operator getOperator(char c, int arity, NotationType notationType) {
        for (Operator op : getAllOperators()) {
            if (op.getChar() == c && op.getArity() == arity && op.getNotationType() == notationType) {
                return op;
            }
        }
        return null;
    }

    public static List<Operator> getAllOperators() {
        List<Operator> operators = new ArrayList<Operator>();
        for (Field f : Operator.class.getFields()) {
            try {
                Object possibleOperator = f.get(null);
                if (Operator.class.isAssignableFrom(possibleOperator.getClass())) {
                    operators.add((Operator) possibleOperator);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return operators;
    }

    public static boolean isOperatorChar(char c) {
        for (Operator op : getAllOperators()) {
            if (op.getChar() == c) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(this.operatorChar);
    }

}