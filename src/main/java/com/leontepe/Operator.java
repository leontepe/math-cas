
package com.leontepe;

import java.util.List;
import java.util.ArrayList;

public class Operator {

    public static enum Associativity {
        LEFT, RIGHT
    }

    // Parantheses are special kind of operators (in terms of the shunting-yard algorithm).
    public static final Operator LEFT_BRACKET = new Operator('(', -1, null, null);
    public static final Operator RIGHT_BRACKET = new Operator(')', -1, null, null);

    private static final List<Operator> operators = new ArrayList<Operator>() {{
        add(new Operator('+', 0, Associativity.LEFT, (op1, op2) -> { return op1 + op2; }));
        add(new Operator('-', 0, Associativity.LEFT, (op1, op2) -> { return op1 - op2; })); 
        add(new Operator('*', 1, Associativity.LEFT, (op1, op2) -> { return op1 * op2; }));
        add(new Operator('/', 1, Associativity.LEFT, (op1, op2) -> { return op1 / op2; }));
        add(new Operator('^', 2, Associativity.RIGHT, (op1, op2) -> { return op1 ^ op2; }));
    }};

    private int precedence;
    private Associativity associativity;
    private char character;
    private IOperator operator;

    private Operator(char character, int precedence, Associativity associativity, IOperator operator) {
        this.character = character;
        this.precedence = precedence;
        this.associativity = associativity;
        this.operator = operator;
    }

    public char getCharacter() { return this.character; }
    public int getPrecedence() { return this.precedence; }
    public Associativity getAssociativity() { return this.associativity; }
    public int operate(int op1, int op2) { return this.operator.operate(op1, op2); }

    public static Operator get(char c) {
        Operator operator = null;
        for(Operator op : operators) {
            if(op.getCharacter() == c) {
                operator = op;
            }
        }
        return operator;
    }

    public static boolean isOperator(char c) {
        return get(c) != null;
    }

    public static boolean isLeftBracket(char c) {
        return c == LEFT_BRACKET.getCharacter();
    }

    public static boolean isRightBracket(char c) {
        return c == RIGHT_BRACKET.getCharacter();
    }
}