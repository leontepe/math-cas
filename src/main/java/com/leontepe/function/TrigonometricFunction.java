
package com.leontepe.function;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.leontepe.expression.Number;

public class TrigonometricFunction extends Function {

    // all inputs are currently in radians according to standards from java class Math

    private TrigonometricFunction(String functionName) {
        super(functionName, null, 1);
    }

    // Trigonometric functions

    public static TrigonometricFunction SINE = new TrigonometricFunction("sin") {
        @Override
        public Number apply(Number[] arguments) {
            super.apply(arguments);
            return new Number(Math.sin(arguments[0].getValue()));
        }
    };

    public static TrigonometricFunction COSINE = new TrigonometricFunction("cos") {
        @Override
        public Number apply(Number[] arguments) {
            super.apply(arguments);
            return new Number(Math.cos(arguments[0].getValue()));
        }
    };

    public static TrigonometricFunction TANGENT = new TrigonometricFunction("tan") {
        @Override
        public Number apply(Number[] arguments) {
            super.apply(arguments);
            return new Number(Math.tan(arguments[0].getValue()));
        }
    };

    public static TrigonometricFunction COTANGENT = new TrigonometricFunction("cot") {
        @Override
        public Number apply(Number[] arguments) {
            super.apply(arguments);
            return new Number(1 / Math.tan(arguments[0].getValue()));
        }
    };

    public static TrigonometricFunction SECANT = new TrigonometricFunction("sec") {
        @Override
        public Number apply(Number[] arguments) {
            super.apply(arguments);
            return new Number(1 / Math.cos(arguments[0].getValue()));
        }
    };

    public static TrigonometricFunction COSECANT = new TrigonometricFunction("csc") {
        @Override
        public Number apply(Number[] arguments) {
            super.apply(arguments);
            return new Number(1 / Math.sin(arguments[0].getValue()));
        }
    };

    // Inverse trigonometric functions

    public static TrigonometricFunction ARCSINE = new TrigonometricFunction("arcsin") {
        @Override
        public Number apply(Number[] arguments) {
            super.apply(arguments);
            return new Number(Math.asin(arguments[0].getValue()));
        }
    };

    public static TrigonometricFunction ARCCOSINE = new TrigonometricFunction("arccos") {
        @Override
        public Number apply(Number[] arguments) {
            super.apply(arguments);
            return new Number(Math.acos(arguments[0].getValue()));
        }
    };

    public static TrigonometricFunction ARCTANGENT = new TrigonometricFunction("arctan") {
        @Override
        public Number apply(Number[] arguments) {
            super.apply(arguments);
            return new Number(Math.atan(arguments[0].getValue()));
        }
    };

    public static TrigonometricFunction ARCCOTANGENT = new TrigonometricFunction("arccot") {
        @Override
        public Number apply(Number[] arguments) {
            super.apply(arguments);
            return new Number(Math.atan(1 / arguments[0].getValue()));
        }
    };

    public static TrigonometricFunction ARCSECANT = new TrigonometricFunction("arcsec") {
        @Override
        public Number apply(Number[] arguments) {
            super.apply(arguments);
            return new Number(Math.acos(1 / arguments[0].getValue()));
        }
    };

    public static TrigonometricFunction ARCCOSECANT = new TrigonometricFunction("arccsc") {
        @Override
        public Number apply(Number[] arguments) {
            super.apply(arguments);
            return new Number(Math.asin(1 / arguments[0].getValue()));
        }
    };

    public static List<TrigonometricFunction> getAllTrigonometricFunctions() {
        List<TrigonometricFunction> functions = new ArrayList<TrigonometricFunction>();
        for(Field f : TrigonometricFunction.class.getFields()) {
            try {
                Object possibleTrigFunction = f.get(null);
                if(TrigonometricFunction.class.isAssignableFrom(possibleTrigFunction.getClass())) {
                    functions.add((TrigonometricFunction)possibleTrigFunction);
                }
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return functions;
    }

}