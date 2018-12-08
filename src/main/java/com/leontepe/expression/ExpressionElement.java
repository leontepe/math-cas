
package com.leontepe.expression;

public abstract class ExpressionElement {

    public abstract String getStringValue();

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ExpressionElement) {
            ExpressionElement el = (ExpressionElement)obj;
            return el.getStringValue().equals(getStringValue());
        }
        return false;
    }
}