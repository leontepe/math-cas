
package com.leontepe;

import com.leontepe.exception.AmbiguousSyntaxException;
import com.leontepe.exception.InvalidSyntaxException;
import com.leontepe.exception.MathContextException;
import com.leontepe.exception.NotImplementedException;
import com.leontepe.expression.*;
import com.leontepe.expression.Number;
import com.leontepe.function.Function;
import com.leontepe.function.LogarithmicFunction;
import com.leontepe.function.TrigonometricFunction;

import java.util.ArrayList;
import java.util.List;

public class MathContext {

    // public static MathContext STANDARD_CONTEXT = new MathContext();

    private List<Equation> equations = new ArrayList<Equation>();

    // f(x) = 3*x^2 - 12            [FuncDef]
    // f(3) = 3*3^2 - 12 =          [FuncEval]
    // f(2^8) = 3*(2^8)^2 - 12 =    [FuncEval]
    // a = 3                        [VarDef]
    // 3*12-2!+(-3/4) =             [ExprEval]
    // f'(x) =                      [FuncDiff]
    // F(x) =                       [FuncAntiDiff]
    // sin(3) =                     [ExprEval]

    private List<Variable> declaredVariables;
    private List<Function> declaredFunctions;

    private String output;

    public MathContext() {
        this.declaredFunctions = new ArrayList<Function>();
        this.declaredVariables = new ArrayList<Variable>();

        // Load standard functions and constants into context
        defineTrigonometricFunctions();
        defineLogarithmicFunctions();
        defineConstants();
    }

    public List<Variable> getDeclaredVariables() {
        return declaredVariables;
    }

    public List<Function> getDeclaredFunctions() {
        return declaredFunctions;
    }

    public Function getDeclaredFunction(String functionName) {
        for(Function f : declaredFunctions) {
            if(f.getFunctionName().equals(functionName)) {
                return f;
            }
        }
        return null;
    }

    public boolean isFunctionDeclared(String functionName) {
        return getDeclaredFunction(functionName) != null;
    }

    public void declareFunction(Function f) {
        if (!isFunctionDeclared(f.getFunctionName())) {
            declaredFunctions.add(f);
        }
        else {
            throw new MathContextException("Function already declared");
        }
    }

    public Variable getDeclaredVariable(char variableChar) {
        for(Variable v : declaredVariables) {
            if(v.getChar() == variableChar) {
                return v;
            }
        }
        return null;
    }

    public boolean isVariableDeclared(char variableChar) {
        return getDeclaredVariable(variableChar) != null;
    }

    public void declareVariable(Variable var) {
        if (!isVariableDeclared(var.getChar())) {
            declaredVariables.add(var);
        }
        else {
            throw new MathContextException("Variable already declared");
        }
    }

    public void defineTrigonometricFunctions() {
        declaredFunctions.addAll(TrigonometricFunction.getAllTrigonometricFunctions());
    }

    public void defineLogarithmicFunctions() {
        declaredFunctions.addAll(LogarithmicFunction.getAllLogarithmicFunctions());
    }

    public void defineConstants() {
        declaredVariables.addAll(Constant.getAllConstants());
    }

    // public void evaluateEquation(Equation eq) {
    //     if (eq.getExpressionCount() == 2) {
    //         Expression leftSide = eq.getExpressions().get(0);
    //         if (leftSide.isFunctionLeftSide()) {
    //             Variable v = leftSide.getExpressionElements().get(0)
    //         }
    //     }
    // }

    public void parseEquation(String equationString) throws InvalidSyntaxException, AmbiguousSyntaxException, NotImplementedException {

        this.output = "";

        // Remove whitespace
        equationString = equationString.replaceAll("\\s+", "");

        if (!equationString.contains("=")) {
            throw new InvalidSyntaxException("No equal sign");
        }
        if (equationString.matches("(.*)=(=+)(.*)")) {
            throw new InvalidSyntaxException("Two or more equal signs in a row");
        }
        else if (equationString.matches("=(.*)=")) {
            throw new InvalidSyntaxException("Starts and ends with equal sign");
        }

        String[] equationExpressions = equationString.split("=");

        if (equationString.startsWith("=")) {

            if (true) throw new NotImplementedException();
            
            // Follow-up equation
            for (int i = 0; i < equationExpressions.length; i++) {
                Expression ex = new Expression(this, equationExpressions[i]);
                this.equations.get(this.equations.size() - 1).addExpression(ex);
            }

            // check if 
            
        }
        else if (equationString.endsWith("=")) {
            // Solve request
            if (equationExpressions.length > 1) {
                throw new NotImplementedException();
            }

            Expression ex = new Expression(equationExpressions[0]);
            if (ex.isEvaluatable()) {
                Number evaluated = ex.evaluate();
                List<Expression> expressions = new ArrayList<Expression>();
                expressions.add(ex);
                expressions.add(evaluated.toExpression());
                Equation eq = new Equation(expressions);
                this.equations.add(eq);
                this.output = evaluated.toString();
            }
            else {
                throw new NotImplementedException();
            }

        }
        else {
            // Standard one-liner equation
            if (equationExpressions.length > 2) {
                throw new NotImplementedException();
            }

            List<Expression> expressions = new ArrayList<>();
            for (int i = 0; i < equationExpressions.length; i++) {
                Expression ex = new Expression(this, equationExpressions[i]);
                expressions.add(ex);
            }

            if (expressions.get(0).isFunctionLeftSide()) {
                String functionName = expressions.get(0).getExpressionElements().get(0).toString();
                Function f = getDeclaredFunction(functionName);
                f.setDefinition(expressions.get(1));
                Equation eq = new Equation(expressions);
                this.equations.add(eq);
            }
            else {
                throw new NotImplementedException();
            }
        }
    }

    public String getOutput() {
        return this.output;
    }
}