
package com.leontepe.function;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.leontepe.expression.*;

import org.junit.Test;

public class FunctionParsingTest {

    // @Test
    // public void testVariableParsing() {
    //     Function f1 = new Function("f(x) = 31x - 2");
    //     Variable[] variables1 = new Variable[] {Variable.get("x")};
    //     assertArrayEquals(f1.getVariables(), variables1);

    //     Function f2 = new Function("sin(x) = 20 * x");
    //     Variable[] variables2 = new Variable[] {Variable.get("x")};
    //     assertArrayEquals(f2.getVariables(), variables2);

    //     Function f3 = new Function("z( x ,y ) = 1/2x - 3/4y + 10/3");
    //     Variable[] variables3 = new Variable[] { Variable.get("x"), Variable.get("y") };
    //     assertArrayEquals(f3.getVariables(), variables3);

    //     Function f4 = new Function("a( b, c, d, e, f) = b - c + d - e + 2f");
    //     Variable[] variables4 = new Variable[] { Variable.get("b"), Variable.get("c"), Variable.get("d"), Variable.get("e"), Variable.get("f") };
    //     assertArrayEquals(f4.getVariables(), variables4);
    // }

    // @Test
    // public void testFunctionExpressionParsing() {
    //     Function f1 = new Function("f(x) = 20x - 2");
    //     assertEquals(f1.getExpression(), new Expression("20x-2"));

    //     Function f2 = new Function("g (x )=   -40 x + 3 x");
    //     assertEquals(f2.getExpression(), new Expression("-40x + 3x"));

    //     Function f3 = new Function("z(x, y) = - 7 x + 2y - 10x + 0");
    //     assertEquals(f3.getExpression(), new Expression("-7x + 2y - 10x + 0"));
    // }

    // @Test
    // public void testFunctionNameWithVariables() {
    //     Function f1 = new Function("f( x ) = 4x + 3");
    //     assertEquals(f1.getFunctionNameWithVariables(), "f(x)");
    //     Function f2 = new Function("g(z)=1/3x-2");
    //     assertEquals(f2.getFunctionNameWithVariables(), "g(z)");
    //     Function f3 = new Function("h ( x ,y)= 12x - 4y + 3");
    //     assertEquals(f3.getFunctionNameWithVariables(), "h(x,y)");
    // }

}