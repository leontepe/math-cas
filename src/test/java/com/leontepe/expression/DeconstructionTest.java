package com.leontepe.expression;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DeconstructionTest {

    @Test
    public void testBasicExpressions() {
        String expected1 = "(3+4)*2";
        String actual1 = new Expression(expected1).toString();
        assertEquals(expected1, actual1);
        String expected2 = "10/5+4";
        String actual2 = new Expression(expected2).toString();
        assertEquals(expected2, actual2);
        String expected3 = "3-2^5";
        String actual3 = new Expression(expected3).toString();
        assertEquals(expected3, actual3);
        String expected4 = "-2*5/3";
        String actual4 = new Expression(expected4).toString();
        assertEquals(expected4, actual4);
        String expected5 = "-(5+3)/2*(12)+3";
        String actual5 = new Expression(expected5).toString();
        assertEquals(expected5, actual5);
    }

}