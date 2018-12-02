
package com.leontepe.expression;

public class Variable extends ExpressionElement {

    private String stringValue;

    public Variable(String stringValue) {
        this.stringValue = stringValue;
    }

    public static boolean isVariable(String c) {
        if(c.length() != 1) throw new IllegalArgumentException();
        return Character.isLetter(c.charAt(0));
    }

    public static Variable get(String c) {
        if(!isVariable(c)) throw new IllegalArgumentException();
        else return new Variable(c);
    }

    public String getStringValue() { return this.stringValue; }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Variable) {
            Variable variable = (Variable)obj;
            return variable.getStringValue().equals(stringValue);
        }
        else return false;
    }

}