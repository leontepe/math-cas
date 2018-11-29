
package com.leontepe;

public class Number<T extends java.lang.Number> extends ExpressionElement {

    public static final char DECIMAL_SEPARATOR = '.';

    private T value;
    
    public Number(T value) {
        this.value = value;
    }

    public static Number get(String s) {
        if(s.contains(String.valueOf(Number.DECIMAL_SEPARATOR))) {
            return new Number<Double>(Double.parseDouble(s));
        }
        else {
            return new Number<Integer>(Integer.parseInt(s));
        }
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

    public String getStringValue() {
        return String.valueOf(getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Number) {
            Number number = (Number)obj;
            return number.getValue().equals(this.value);
        }
        return false;
    }


}