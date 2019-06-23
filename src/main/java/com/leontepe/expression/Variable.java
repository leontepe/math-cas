
package com.leontepe.expression;

public class Variable extends ExpressionElement {

    private char variableChar;
    private Number value;

    public Variable(char variableChar) {
        if(!isVariable(variableChar)) throw new IllegalArgumentException();
        this.variableChar = variableChar;
    }

    public char getVariableChar() {
        return this.variableChar;
    }

    public Number getValue() {
        return this.value;
    }

    public void setValue(Number number) {
        this.value = number;
    }

    public static boolean isVariable(char c) {
        return Character.isLetter(c);
    }

    @Override
    public String getStringValue() { return String.valueOf(variableChar); }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Variable) {
            Variable variable = (Variable)obj;
            return variable.getVariableChar() == this.variableChar;
        }
        else return false;
    }

}