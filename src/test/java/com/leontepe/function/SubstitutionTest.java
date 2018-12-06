
package com.leontepe.function;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.leontepe.expression.*;
import com.leontepe.expression.Number;

public class SubstitutionTest {

    // "f(x) = 3x - 5"
    // => functionName = "f"
    // => expression = "3x-5"
    //      => expressionElements = {"3", "*", "x", "-", "5"}
    //

    @Test
    public void testSubstitution() {
        Function f1 = new Function("f(x) = 3x - 5");
        List<ExpressionElement> elements1 = new ArrayList<ExpressionElement>();
        elements1.add(Number.get("3"));
        elements1.add(Operator.get("*"));
        elements1.add(Number.get("2"));
        elements1.add(Operator.get("-"));
        elements1.add(Number.get("5"));
        assertEquals(f1.getExpression().substitute(Variable.get("x"), new Number(2)).getElements(), elements1);

        Function f2 = new Function("g(x) = 1/4x^2 - 2x + 3");
        List<ExpressionElement> elements2 = new ArrayList<ExpressionElement>();
        elements2.add(Number.get("3"));
        elements2.add(Operator.get("*"));
        elements2.add(Number.get("2"));
        elements2.add(Operator.get("-"));
        elements2.add(Number.get("5"));
        assertEquals(f2.getExpression().substitute(Variable.get("x"), new Number(2)).getElements(), elements2);
    }

}