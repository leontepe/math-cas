package com.leontepe.function;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.leontepe.expression.*;

import org.junit.Test;

public class FunctionVariablesTest  {

    @Test
    public void testVariableParsing() {
        Function f1 = new Function("f(x) = 31x - 2");
        List<Variable> variables1 = new ArrayList<Variable>();
        variables1.add(Variable.get("x"));
        assertEquals(f1.getVariables(), variables1);

        Function f2 = new Function("sin(x) = 20 * x");
        List<Variable> variables2 = new ArrayList<Variable>();
        variables2.add(Variable.get("x"));
        assertEquals(f2.getVariables(), variables2);

        Function f3 = new Function("z( x ,y ) = 1/2x - 3/4y + 10/3");
        List<Variable> variables3 = new ArrayList<Variable>();
        variables3.add(Variable.get("x"));
        variables3.add(Variable.get("y"));
        assertEquals(f3.getVariables(), variables3);

        Function f4 = new Function("a( b, c, d, e, f) = b - c + d - e + 2f");
        List<Variable> variables4 = new ArrayList<Variable>();
        variables4.add(Variable.get("b"));
        variables4.add(Variable.get("c"));
        variables4.add(Variable.get("d"));
        variables4.add(Variable.get("e"));
        variables4.add(Variable.get("f"));
        assertEquals(f4.getVariables(), variables4);
    }
}