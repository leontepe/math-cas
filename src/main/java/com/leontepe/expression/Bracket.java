
package com.leontepe.expression;

public class Bracket extends ExpressionElement {

    public static final Bracket LEFT_BRACKET = new Bracket("(");
    public static final Bracket RIGHT_BRACKET = new Bracket(")");

    private String stringValue;

    private Bracket(String stringValue) {
        this.stringValue = stringValue;
    }

    public static Bracket get(String s) {
        if(isLeftBracket(s)) return LEFT_BRACKET;
        else if(isRightBracket(s)) return RIGHT_BRACKET;
        else throw new IllegalArgumentException();
    }

    public static boolean isLeftBracket(String s) {
        return s.equals(LEFT_BRACKET.getStringValue());
    }

    public static boolean isRightBracket(String s) {
        return s.equals(RIGHT_BRACKET.getStringValue());
    }

    public static boolean isBracket(String s) {
        return isLeftBracket(s) || isRightBracket(s);
    }

    public String getStringValue() {
        return this.stringValue;
    }
}