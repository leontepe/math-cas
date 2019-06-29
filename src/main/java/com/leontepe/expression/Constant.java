
package com.leontepe.expression;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Constant extends Variable {

    public Constant(char constantChar, Number value) {
        super(constantChar, value);
    }

    public static Constant PI = new Constant('\u03c0', new Number(Math.PI));
    public static Constant E = new Constant('e', new Number(Math.E));

    public static List<Constant> getAllConstants() {
        List<Constant> constants = new ArrayList<Constant>();
        for(Field f : Constant.class.getFields()) {
            try {
                Object possibleConstant = f.get(null);
                if(Constant.class.isAssignableFrom(possibleConstant.getClass())) {
                    constants.add((Constant)possibleConstant);
                }
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return constants;
    }

}