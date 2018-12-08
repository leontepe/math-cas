package com.leontepe.function;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FunctionValueTest {

    @Test
    public void testValueAt() {
        double delta = 1e-8;
        Function f1 = new Function("f(x) = 3x^5 - 1/2x^3 + 8/3x^2 - 21/4");
        assertEquals(f1.valueAt(0), -5.25, delta);
        assertEquals(f1.valueAt(-3), -696.75, delta);
        assertEquals(f1.valueAt(3), 734.25, delta);

        Function f2 = new Function("g(x) = 1/10 * x^5 - 1/8 * x^3");
        assertEquals(f2.valueAt(-5), -296.875, delta);
        assertEquals(f2.valueAt(2), 2.2, delta);
        assertEquals(f2.valueAt(6), 750.6, delta);
        assertEquals(f2.valueAt(-8), -3212.8, delta);

        Function f3 = new Function("h(x) = 1.3293x^2 - 0.831x + 182.244");
        assertEquals(f3.valueAt(2), 185.8992, delta);
        assertEquals(f3.valueAt(-6), 235.0848, delta);
        assertEquals(f3.valueAt(-47), 3157.7247, delta);
    }

    @Test
    public void testValueRange() {
        double delta = 1e-8;

        Function f1 = new Function("f(x) = 1/6x^3");
        double[] values1 = new double[] { -10.6666666667, -7.14583333333, -4.5, -2.60416666667,
            -1.33333333333, -0.5625, -0.166666666667, -0.0208333333333, 0, 0.0208333333333, 0.166666666667,
            0.5625, 1.33333333333, 2.60416666667, 4.5, 7.14583333333, 10.6666666667};
        assertArrayEquals(f1.valueRange(-4, 4, 0.5), values1, delta);
        
        Function f2 = new Function("g(x) = x^2");
        double[] values2 = new double[] { 9, 7.5625, 6.25, 5.0625, 4, 3.0625, 2.25, 1.5625, 1, 0.5625, 0.25, 0.0625, 0, 0.0625, 0.25, 0.5625, 1, 1.5625, 2.25, 3.0625, 4, 5.0625, 6.25, 7.5625, 9 };
        assertArrayEquals(f2.valueRange(-3, 3, 0.25), values2, delta);

        // definiton gap at x = 0 (division by zero)
        // Function f3 = new Function("f(x) = 15.2x^-2");
        // double[] values3 = new double[] { 1.68888888889, 3.8, 15.2,  };
        // assertArrayEquals(f3.valueRange(-3, 3, 0.25), values3, delta);

        Function f3 = new Function("h(x) = -0.7x^6 - 0.4x^5 - 4.3x^3 + 6.2x + 1.7");
        double[] values3 = new double[] { 1.9765625, -0.5, -0.8609375, 1.7, 4.2390625, 2.5, -14.5234375 };
        assertArrayEquals(f3.valueRange(-1.5, 1.5, 0.5), values3, delta);
    }
    
}