package com.leontepe;

import com.leontepe.expression.*;
import com.leontepe.function.*;


public class App 
{
    public static void main( String[] args )
    {
        Function f = new Function("f(x) = x^2");
        f.printValueRange(-4, 4, 0.5);
    }

    // "-3+5" -> "0-3+5" -> "0", "-", "3", "+", "5" -> "03-5+" -> 2
    // "-2^3" -> "0-2^3" -> "0", "-", "2", "^", "3" -> "023^-" -> -8
    // "-(3+5)" -> "0-(3+5)" -> "0", "-", "(", "3", "+", "5", ")" -> "035+-" -> -8
    // ==> Solves unary operator problem and power problem
}
