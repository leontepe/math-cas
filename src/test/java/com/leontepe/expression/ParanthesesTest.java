
package com.leontepe.expression;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ParanthesesTest {
    
    @Test
    public void testContainsParantheses() {
        Expression e1 = new Expression("3x - 2 + 4y");
        assertEquals(e1.containsParantheses(), false);
        Expression e2 = new Expression("3x - (2 + 4y)");
        assertEquals(e2.containsParantheses(), true);
    }

}