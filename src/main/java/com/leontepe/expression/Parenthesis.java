
package com.leontepe.expression;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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

    public static Parenthesis getParenthesis(char c) {
        for (Parenthesis p : getAllParentheses()) {
            if (p.getParenthesisChar() == c) {
                return p;
            }
        }
        return null;
    }

    public static boolean isParenthesisChar(char c) {
        return getParenthesis(c) != null;
    }

    public static List<Parenthesis> getAllParentheses() {
        List<Parenthesis> parentheses = new ArrayList<Parenthesis>();
        for(Field f : Parenthesis.class.getFields()) {
            try {
                Object possibleParenthesis = f.get(null);
                if(Parenthesis.class.isAssignableFrom(possibleParenthesis.getClass())) {
                    parentheses.add((Parenthesis)possibleParenthesis);
                }
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return parentheses;
    }

}