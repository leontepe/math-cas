
package com.leontepe.expression;

import java.time.temporal.ValueRange;

public class Variable extends ExpressionElement {

    private char variableChar;
    private Number value;

    public Variable(char variableChar) {
        if (!isVariableChar(variableChar))
            throw new IllegalArgumentException();
        this.variableChar = variableChar;
    }

    public Variable(char variableChar, Number value) {
        this(variableChar);
        setValue(value);
    }

    public char getChar() {
        return this.variableChar;
    }

    public Number getValue() {
        return this.value;
    }

    public boolean hasValue() {
        return value != null;
    }

    public void setValue(Number number) {
        this.value = number;
    }

    @Override
    public String getStringValue() {
        return String.valueOf(variableChar);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable) {
            Variable other = (Variable)obj;
            if(other.getChar() == this.variableChar) {
                if(other.hasValue()) {
                    return other.getValue() == this.value;
                }
                else return true;
            }
        }
        return false;
    }

    public static boolean isVariableChar(char c) {
        return Character.isLetter(c);
    }

}