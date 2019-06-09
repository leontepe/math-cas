
package com.leontepe.expression;

public class Parenthesis extends ExpressionElement {

    public static final Parenthesis LEFT_PARENTHESIS = new Parenthesis('(');
    public static final Parenthesis RIGHT_PARENTHESIS = new Parenthesis(')');

    private char parenthesisChar;

    private Parenthesis(char paranthesisChar) {
        this.parenthesisChar = paranthesisChar;
    }

    public char getParenthesisChar() {
        return this.parenthesisChar;
    }

    @Override
    public String getStringValue() {
        return String.valueOf(this.parenthesisChar);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Parenthesis) {
            Parenthesis par = (Parenthesis)obj;
            return par.getParenthesisChar() == this.parenthesisChar;
        }
        return false;
    }

    public static Parenthesis get(char c) {
        if(isLeftParenthesis(c)) return LEFT_PARENTHESIS;
        else if(isRightParenthesis(c)) return RIGHT_PARENTHESIS;
        else throw new IllegalArgumentException();
    }

    public static boolean isLeftParenthesis(char c) {
        return c == LEFT_PARENTHESIS.getParenthesisChar();
    }

    public static boolean isRightParenthesis(char c) {
        return c == RIGHT_PARENTHESIS.getParenthesisChar();
    }

    public static boolean isParenthesis(char c) {
        return isLeftParenthesis(c) || isRightParenthesis(c);
    }

}