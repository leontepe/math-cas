
package com.leontepe.function;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.leontepe.expression.Number;

public class LogarithmicFunction extends Function {

    public LogarithmicFunction(String functionName, int arity) {
        super(functionName, null, arity);
    }

    // function names according to ISO 80000-2 (https://en.wikipedia.org/wiki/ISO_80000-2)

    // to implement the general logarithm log_b(x) I first need to implement parameters, as the base b is a parameter

    // public static LogarithmicFunction LOGARITHM = new LogarithmicFunction("log", 2) {
    //     @Override
    //     public Number apply(Number[] arguments) {
    //         super.apply(arguments);
    //         return new Number(Math.log(arguments[1].getValue()) / Math.log(arguments[0].getValue()));
    //     }
    // };

    public static LogarithmicFunction NATURAL_LOGARITHM = new LogarithmicFunction("ln", 1) {
        @Override
        public Number apply(Number[] arguments) {
            super.apply(arguments);
            return new Number(Math.log(arguments[0].getValue()));
        }
    };

    public static LogarithmicFunction COMMON_LOGARITHM = new LogarithmicFunction("lg", 1) {
        @Override
        public Number apply(Number[] arguments) {
            super.apply(arguments);
            return new Number(Math.log10(arguments[0].getValue()));
        }
    };

    public static List<LogarithmicFunction> getAllLogarithmicFunctions() {
        List<LogarithmicFunction> functions = new ArrayList<LogarithmicFunction>();
        for(Field f : LogarithmicFunction.class.getFields()) {
            try {
                Object possibleLogFunction = f.get(null);
                if(LogarithmicFunction.class.isAssignableFrom(possibleLogFunction.getClass())) {
                    functions.add((LogarithmicFunction)possibleLogFunction);
                }
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return functions;
    }

}