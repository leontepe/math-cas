
package com.leontepe;

import com.leontepe.exception.MathContextException;
import com.leontepe.expression.*;
import com.leontepe.function.Function;
import com.leontepe.function.LogarithmicFunction;
import com.leontepe.function.TrigonometricFunction;

import java.util.ArrayList;
import java.util.List;

public class MathContext {

    private List<Variable> declaredVariables;
    private List<Function> declaredFunctions;

    public MathContext() {
        this.declaredFunctions = new ArrayList<Function>();
        this.declaredVariables = new ArrayList<Variable>();

        // Load standard functions and constants into context
        loadTrigonometricFunctions();
        loadLogarithmicFunctions();
        loadConstants();
    }

    public List<Variable> getDefinedVariables() {
        return declaredVariables;
    }

    public List<Function> getDefinedFunctions() {
        return declaredFunctions;
    }

    public Function getFunction(String functionName) {
        for(Function f : declaredFunctions) {
            if(f.getFunctionName().equals(functionName)) {
                return f;
            }
        }
        return null;
    }

    public boolean containsFunction(String functionName) {
        return getFunction(functionName) != null;
    }

    public Variable getVariable(char variableChar) {
        for(Variable v : declaredVariables) {
            if(v.getChar() == variableChar) {
                return v;
            }
        }
        return null;
    }

    public boolean containsVariable(char variableChar) {
        return getVariable(variableChar) != null;
    }

    public void addVariable(Variable var) {
        if (!containsVariable(var.getChar())) {
            declaredVariables.add(var);
        }
        else {
            throw new MathContextException("Variable to be added is already declared");
        }
    }

    public void loadTrigonometricFunctions() {
        declaredFunctions.addAll(TrigonometricFunction.getAllTrigonometricFunctions());
    }

    public void loadLogarithmicFunctions() {
        declaredFunctions.addAll(LogarithmicFunction.getAllLogarithmicFunctions());
    }

    public void loadConstants() {
        declaredVariables.addAll(Constant.getAllConstants());
    }
}