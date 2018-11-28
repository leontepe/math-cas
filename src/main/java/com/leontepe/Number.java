
package com.leontepe;

public class Number<T extends java.lang.Number> extends ExpressionElement {

    public static final char DECIMAL_SEPARATOR = '.';

    private T value;
    
    public Number(T value) {
        this.value = value;
    }

    public static boolean isNumber(String s) {
        // if for example ".003" is not a valid number, replace with "^[0-9]+\\.?[0-9]*$"
        return s.matches("^[0-9]*\\.?[0-9]$");
    }

    public static boolean isNumberCharacter(char c) {
        return Character.isDigit(c) || c == DECIMAL_SEPARATOR;
    }

    public T getValue() {
        return this.value;
    }


}