
package com.leontepe;

import java.util.List;
import java.util.ArrayList;

public class Operator {

    public enum Associativity {
        LEFT, RIGHT
    }

    private int precedence;
    private Associativity associativity;
    private char character;

    private static final List<Operator> operators = new ArrayList<Operator>() {{
        add(new Operator('+', 0, Associativity.LEFT));
        add(new Operator('-', 0, Associativity.LEFT));
        add(new Operator('*', 1, Associativity.LEFT));
        add(new Operator('/', 1, Associativity.LEFT));
        add(new Operator('^', 2, Associativity.RIGHT));
        add(new Operator('(', -1, null));
    }};

    private Operator(char character, int precedence, Associativity associativity) {
        this.character = character;
        this.precedence = precedence;
        this.associativity = associativity;
    }

    public char getCharacter() { return this.character; }
    public int getPrecedence() { return this.precedence; }
    public Associativity getAssociativity() { return this.associativity; }
    public boolean isLeftBracket() { return this.}
    public boolean isBracket() { return this.character == '(' || this.character == ')'; }

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
}