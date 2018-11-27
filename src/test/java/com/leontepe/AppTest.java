package com.leontepe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    /**
     * Test infix-to-postfix conversion for simple expressions (no parantheses and single digit integers).
     */
    @Test
    public void testBasicExpressions() {
        assertEquals(new Expression("3 - 4 / 2").getPostfix(), "342/-");
        assertEquals(new Expression("3+4+4-2").getPostfix(), "34+4+2-");
        assertEquals(new Expression("7/8 - 4*3").getPostfix(), "78/43*-");
        assertEquals(new Expression("2^3^3").getPostfix(), "233^^");
        assertEquals(new Expression("5").getPostfix(), "5");
    }

    @Test
    public void testParanthesesExpressions() {
        
    }
}
