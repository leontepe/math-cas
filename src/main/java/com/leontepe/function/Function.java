
package com.leontepe.function;

import com.leontepe.exception.FunctionException;
import com.leontepe.expression.*;
import com.leontepe.expression.Number;

public abstract class Function extends ExpressionElement {

    private String functionName;
    private Expression functionExpression;
    private int arity;

    public Function(String functionName, Expression functionExpression, int arity) {
        this.functionName = functionName;
        this.functionExpression = functionExpression;

        // This might be the only attribute that a function MUST have
        this.arity = arity;
    }

    /**
     * Gets the function name (e.g. f, sin, ln). Optional, as not every function has an explicit name (y = 3x; x -> x^2).
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * Gets expression defining this function. Optional, as not every function can be represented as an expression (e.g. trig. functions)
     */
    public Expression getFunctionExpression() {
        return functionExpression;
    }

    /**
     * Gets the arity (i.e. number of arguments) of this function. Cannot be negative.
     */
    public int getArity() {
        return arity;
    }

    /**
     * Applies this function to the input arguments.
     * @param arguments
     * @return
     */
    public Number apply(Number[] arguments) {
        if(arguments.length != arity) {
            throw new FunctionException("Number of arguments does not equal arity of function");
        }

        // does this belong here?
        if(functionExpression != null) {
            // substitute variables in expression for the argument numbers
            // evaluate expression
            // return result
        }
        return null;
    }

    @Override
    public String getStringValue() {
        return getFunctionName();
    }

    // private String functionString;
    // private Expression expression;
    // private String functionName;
    // private Variable[] variables;

    // public Function(String functionString) {
    //     this.functionString = functionString;
    //     this.functionName = parseFunctionName(functionString);
    //     this.expression = parseExpression(functionString);
    //     this.variables = parseVariables(functionString);
    // }

    // public String getString() { return this.functionString; }
    // public String getFunctionName() { return this.functionName; }
    // public Expression getExpression() { return this.expression; }
    // public Variable[] getVariables() { return this.variables; }

    // public String getFunctionNameWithVariables() {
    //     String s = functionName + "(";
    //     for(int i = 0; i < variables.length; i++) {
    //         s += variables[i].getStringValue();
    //         if(i < variables.length-1) s += ",";
    //     }
    //     return s + ")";
    // }

    // public Function differentiate() {
    //     return null;
    // }

    // public Function integrate() {
    //     return null;
    // }

    // private static Expression parseExpression(String s) {
    //     s = s.replaceAll("\\s+", "");
    //     int i = s.indexOf("=");
    //     Expression ex = new Expression(s.substring(i+1));
    //     return ex;
    // }

    // private static String parseFunctionName(String s) {
    //     s = s.replaceAll("\\s+", "");
    //     int i = s.indexOf("(");
    //     return s.substring(0, i);
    // }

    // private static Variable[] parseVariables(String s) {
    //     s = s.replaceAll("\\s+", "");
    //     s = s.substring(s.indexOf("(")+1, s.indexOf(")"));
    //     String[] splits = s.split(",");
    //     Variable[] vars = new Variable[splits.length];
    //     for(int i = 0; i < splits.length; i++) {
    //         vars[i] = Variable.get(splits[i]);
    //     }
    //     return vars;
    // }

    // public Number valueAt(Number... numbers) {
    //     Expression ex = expression;
    //     if(numbers.length != variables.length) throw new IllegalArgumentException();
    //     for(int i = 0; i < numbers.length; i++) {
    //         ex = ex.substitute(variables[i], numbers[i]);
    //     }
    //     return ex.evaluate();
    // }

    // public double valueAt(double... inputs) {
    //     Expression ex = new Expression(this.expression.getStringValue());
    //     if(inputs.length != variables.length) throw new IllegalArgumentException();
    //     for(int i = 0; i < inputs.length; i++) {
    //         ex = ex.substitute(variables[i], new Number(inputs[i]));
    //     }
    //     return ex.evaluate().getValue();
    // }

    // public double[] valueRange(double start, double end, double step) {
    //     double range = (end - start);
    //     if(range % step != 0) throw new IllegalArgumentException();
    //     int length = (int)(range / step) + 1;
    //     double[] values = new double[length];
    //     for(int i = 0; i < length; i++) {
    //         double input = start + i * step;
    //         values[i] = valueAt(input);
    //     }
    //     return values;
    // }

    // public void printValueRange(double start, double end, double step) {
    //     System.out.println("Printing value range for function " + getString() + " for values from " + start + " to "  + end + " with a step of " + step + ".");
    //     double[] valueRange = valueRange(start, end, step);
    //     for(int i = 0; i < valueRange.length; i++) {
    //         double input = start + i * step;
    //         System.out.println(functionName + "(" + input + ")" + " = " + valueRange[i]);
    //     }
    // }
}