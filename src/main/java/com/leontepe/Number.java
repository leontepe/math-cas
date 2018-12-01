
package com.leontepe;

public class Number<T extends java.lang.Number> extends ExpressionElement {

    public static final char DECIMAL_SEPARATOR = '.';

    private T value;
    
    public Number(T value) {
        this.value = value;
    }

    @SuppressWarnings("rawtypes")
    public static Number get(String s) {
        try {
            if(s.contains(String.valueOf(Number.DECIMAL_SEPARATOR))) {
                return new Number<Double>(Double.parseDouble(s));
            }
            else {
                return new Number<Integer>(Integer.parseInt(s));
            }
        }
        catch(NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public static boolean isNumberCharacter(String s) {
        if(s.length() > 1) throw new IllegalArgumentException();
        return Character.isDigit(s.charAt(0)) || s.charAt(0) == DECIMAL_SEPARATOR;
    }

    public boolean isInteger() {
        return getValue() == Math.floor(getValue()) && !Double.isInfinite(number);
    }

    public T getValue() {
        return this.value;
    }
    
    public String getStringValue() {
        return this.value.toString();
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean equals(Object obj) {
        if(obj instanceof Number) {
            Number number = (Number)obj;
            return number.getValue().equals(this.value);
        }
        return false;
    }


}