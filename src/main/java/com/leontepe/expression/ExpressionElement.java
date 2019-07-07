
package com.leontepe.expression;

import java.util.ArrayList;
import java.util.List;

public abstract class ExpressionElement {

    @Override
    public abstract String toString();

    public Expression toExpression() {
        List<ExpressionElement> elements = new ArrayList<>();
        elements.add(this);
        return new Expression(elements);
    }
}