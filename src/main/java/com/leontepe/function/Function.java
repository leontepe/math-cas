
package com.leontepe.function;

import com.leontepe.expression.*;
import com.leontepe.expression.Number;
import java.util.List;

public class Function {

    private String functionString;
    private Expression expression;
    private String functionName;

    public Function(String functionString) {
        this.functionString = functionString;
        this.functionName = parseFunctionName(functionString);
        this.expression = parseExpression(functionString);
    }

    public String getString() { return this.functionString; }
    public String getFunctionName() { return this.functionName; }
    public Expression getExpression() { return this.expression; }

    public Function differentiate() {
        return null;
    }

    public Function integrate() {
        return null;
    }

    private static Expression parseExpression(String s) {
        s = s.replaceAll("\\s+", "");
        int i = s.indexOf("=");
        Expression ex = new Expression(s.substring(i+1));
        return ex;
    }

    private static String parseFunctionName(String s) {
        s = s.replaceAll("\\s+", "");
        int i = s.indexOf("(");
        return s.substring(0, i);
    }

    public Expression substitute(Variable variable, Number number) {
        List<ExpressionElement> elements = expression.getElements();
        for(ExpressionElement el : elements) {
            if(el instanceof Variable) {
                Variable var = (Variable)el;
                if(variable.equals(var)) {
                    int i = elements.indexOf(el);
                    elements.set(i, number);
                }
            }
        }
        return new Expression(elements);
    }

    public List<Variable> getVariables() {
        // Implement
        return null;
    }

    public Number valueAt(Number... numbers) {
        // Implement
        return null;
    }
}