
package com.leontepe.expression;

public class Number extends ExpressionElement {

    public static final char DECIMAL_SEPARATOR = '.';

    private double value;
    
    public Number(double value) {
        this.value = value;
    }

    public static Number get(String s) {
        try {
            return new Number(Double.parseDouble(s));
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
        return value == Math.floor(value) && !Double.isInfinite(value);
    }

    public boolean isEven() {
        return value % 2 == 0;
    }

    public double getValue() {
        return this.value;
    }
    
    public String getStringValue() {
        if(isInteger()) {
            return Integer.toString((int)value);
        }
        else {
            return Double.toString(value);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Number) {
            Number number = (Number)obj;
            return number.getValue() == this.value;
        }
        return false;
    }


}