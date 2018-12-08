
package com.leontepe.expression;

public class Paranthesis extends ExpressionElement {

    public static final Paranthesis LEFT_PARANTHESIS = new Paranthesis("(");
    public static final Paranthesis RIGHT_PARANTHESIS = new Paranthesis(")");

    private String stringValue;

    private Paranthesis(String stringValue) {
        this.stringValue = stringValue;
    }

    public static Paranthesis get(String s) {
        if(isLeftParanthesis(s)) return LEFT_PARANTHESIS;
        else if(isRightParanthesis(s)) return RIGHT_PARANTHESIS;
        else throw new IllegalArgumentException();
    }

    public static boolean isLeftParanthesis(String s) {
        return s.equals(LEFT_PARANTHESIS.getStringValue());
    }

    public static boolean isRightParanthesis(String s) {
        return s.equals(RIGHT_PARANTHESIS.getStringValue());
    }

    public static boolean isParanthesis(String s) {
        return isLeftParanthesis(s) || isRightParanthesis(s);
    }

    public String getStringValue() {
        return this.stringValue;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Paranthesis) {
            Paranthesis par = (Paranthesis)obj;
            return par.getStringValue().equals(stringValue);
        }
        return false;
    }
}