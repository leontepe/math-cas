
package com.leontepe;

import com.leontepe.expression.*;
import com.leontepe.function.Function;
import com.leontepe.function.LogarithmicFunction;
import com.leontepe.function.TrigonometricFunction;

import java.util.ArrayList;
import java.util.List;

public class MathContext {

    private List<Variable> definedVariables;
    private List<Function> definedFunctions;

    public MathContext() {
        this.definedFunctions = new ArrayList<Function>();
        this.definedVariables = new ArrayList<Variable>();

        // Load standard functions and constants into context
        loadTrigonometricFunctions();
        loadLogarithmicFunctions();
        loadConstants();
    }

    public List<Variable> getDefinedVariables() {
        return definedVariables;
    }

    public List<Function> getDefinedFunctions() {
        return definedFunctions;
    }

    public Function getFunction(String functionName) {
        for(Function f : definedFunctions) {
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
        for(Variable v : definedVariables) {
            if(v.getVariableChar() == variableChar) {
                return v;
            }
        }
        return null;
    }

    public boolean containsVariable(char variableChar) {
        return getVariable(variableChar) != null;
    }

    public void loadTrigonometricFunctions() {
        definedFunctions.addAll(TrigonometricFunction.getAllTrigonometricFunctions());
    }

    public void loadLogarithmicFunctions() {
        definedFunctions.addAll(LogarithmicFunction.getAllLogarithmicFunctions());
    }

    public void loadConstants() {
        definedVariables.addAll(Constant.getAllConstants());
    }
}