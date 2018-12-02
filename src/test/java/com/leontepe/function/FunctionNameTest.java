package com.leontepe.function;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FunctionNameTest {

    @Test
    public void testFunctionNameParsing() {
        Function f1 = new Function("f(x) = 20x - 2");
        assertEquals(f1.getFunctionName(), "f");

        Function f2 = new Function("g (x)=20x-2");
        assertEquals(f2.getFunctionName(), "g");

        Function f3 = new Function("sin(x) = 20x - 2");
        assertEquals(f3.getFunctionName(), "sin");

        Function f4 = new Function("cos (x) = 20x - 2");
        assertEquals(f4.getFunctionName(), "cos");

        Function f5 = new Function("    k   ( y )  = 20x - 2");
        assertEquals(f5.getFunctionName(), "k");
    }
}