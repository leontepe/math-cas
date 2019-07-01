
package com.leontepe;

public class Factorial {
    public static long factorial(long n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}