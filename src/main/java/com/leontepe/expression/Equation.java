package com.leontepe.expression;

import java.util.List;

public class Equation {
    private List<Expression> expressions;

    public Equation(List<Expression> expressions) {
        this.expressions = expressions;
    }

    public Equation(String equationString) {
        parseEquationString(equationString);
    }

    public List<Expression> getExpressions() {
        return this.expressions;
    }

    public void addExpression(Expression e) {
        this.expressions.add(e);
    }

    private void parseEquationString(String equationString) {
        String[] split = equationString.split("=");
        for (int i = 0; i < split.length; i++) {
            expressions.add(new Expression(split[i]));
        }
    }

    public int getExpressionCount() {
        return this.expressions.size();
    }
}