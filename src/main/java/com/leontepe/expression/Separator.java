
package com.leontepe.expression;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Separator extends ExpressionElement {

    // e.g. for separating function arguments
    public static final Separator COMMA = new Separator(',');

    // e.g. for separating vector rows
    public static final Separator SEMICOLON = new Separator(';');

    private char separatorChar;

    public Separator(char separatorChar) {
        super();
        this.separatorChar = separatorChar;
    }

    public char getChar() {
        return this.separatorChar;
    }

    @Override
    public String getStringValue() {
        return null;
    }

    public static Separator getSeparator(char c) {
        for(Separator s : getAllSeparators()) {
            if(s.getChar() == c)
                return s;
        }
        return null;
    }

    public static boolean isSeparatorChar(char c) {
        return getSeparator(c) != null;
    }

    public static List<Separator> getAllSeparators() {
        List<Separator> separators = new ArrayList<Separator>();
        for(Field f : Separator.class.getFields()) {
            try {
                Object possibleSeparator = f.get(null);
                if(Separator.class.isAssignableFrom(possibleSeparator.getClass())) {
                    separators.add((Separator)possibleSeparator);
                }
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return separators;
    }

}