
package com.leontepe;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RegexTest {

    @Test
    public void testRegex() {
        // not more than two equal signs in a row
        String regex = "(.*)=(=+)(.*)";
        assertTrue(!"a=b".matches(regex));
        assertTrue(!"a=b=c=d".matches(regex));
        assertTrue(!"=a=b".matches(regex));
        assertTrue(!"a=".matches(regex));
        assertTrue("a==b".matches(regex));
        assertTrue("a======b=c=d".matches(regex));
        assertTrue("a==b==c==d".matches(regex));
    }
}