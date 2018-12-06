
package com.leontepe.function;

import com.leontepe.expression.*;
import com.leontepe.expression.Number;

import java.util.ArrayList;
import java.util.List;

public class Function {

    private String functionString;
    private Expression expression;
    private String functionName;
    private List<Variable> variables;

    public Function(String functionString) {
        this.functionString = functionString;
        this.functionName = parseFunctionName(functionString);
        this.expression = parseExpression(functionString);
        this.variables = parseVariables(functionString);
    }

    public String getString() { return this.functionString; }
    public String getFunctionName() { return this.functionName; }
    public Expression getExpression() { return this.expression; }
    public List<Variable> getVariables() { return this.variables; }

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

    private static List<Variable> parseVariables(String s) {
        List<Variable> vars = new ArrayList<Variable>();
        s = s.replaceAll("\\s+", "");
        s = s.substring(s.indexOf("(")+1, s.indexOf(")"));
        for(String var : s.split(",")) {
            vars.add(Variable.get(var));
        }
        return vars;
    }

    // TODO: Add tests
    public Number valueAt(Number... numbers) {
        Expression ex = expression;
        if(numbers.length != variables.size()) throw new IllegalArgumentException();
        for(int i = 0; i < numbers.length; i++) {
            ex = ex.substitute(variables.get(i), numbers[i]);
        }
        return ex.evaluate();
    }

    // TODO: Add tests
    public double valueAt(double... inputs) {
        Expression ex = new Expression(this.expression.getString());
        if(inputs.length != variables.size()) throw new IllegalArgumentException();
        for(int i = 0; i < inputs.length; i++) {
            ex = ex.substitute(variables.get(i), new Number(inputs[i]));
        }
        return ex.evaluate().getValue();
    }

    // TODO: Add tests
    public double[] valueRange(double start, double end, double step) {
        double range = (end - start);
        if(range % step != 0) throw new IllegalArgumentException();
        int length = (int)(range / step) + 1;
        double[] values = new double[length];
        for(int i = 0; i < length; i++) {
            double input = start + i * step;
            values[i] = valueAt(input);
        }
        return values;
    }

    public void printValueRange(double start, double end, double step) {
        double[] valueRange = valueRange(start, end, step);
        for(int i = 0; i < valueRange.length; i++) {
            double input = start + i * step;
            System.out.println(functionName + "(" + input + ")" + " = " + valueRange[i]);
        }
    }
}