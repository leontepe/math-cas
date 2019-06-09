
package com.leontepe.expression;

public class Variable extends ExpressionElement {

    private char variableChar;

    public Variable(char variableChar) {
        this.variableChar = variableChar;
    }

    public char getVariableChar() {
        return this.variableChar;
    }

    public static boolean isVariable(char c) {
        return Character.isLetter(c);
    }

    public static Variable get(char c) {
        if(!isVariable(c)) throw new IllegalArgumentException();
        else return new Variable(c);
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