package com.leontepe.expression;

import java.util.HashMap;

public class GreekLetters {

    public static final HashMap<String, Character> GREEK_LETTERS_LOWERCASE = new HashMap<String, Character>() {
        {
            put("alpha", 'α');
            put("beta", 'β');
            put("gamma", 'γ');
            put("delta", 'δ');
            put("epsilon", 'ε');
            put("zeta", 'ζ');
            put("eta", 'η');
            put("theta", 'θ');
            put("iota", 'ι');
            put("kappa", 'κ');
            put("lambda", 'λ');
            put("mu", 'µ');
            put("nu", 'ν');
            put("xi", 'ξ');
            put("omicron", 'o');
            put("pi", 'π');
            put("rho", 'ρ');
            put("sigma", 'σ');
            put("tau", 'τ');
            put("upsilon", 'υ');
            put("phi", 'ϕ');
            put("chi", 'χ');
            put("psi", 'ψ');
            put("omega", 'ω');
        }
    };

    public static final HashMap<String, Character> GREEK_LETTERS_UPPERCASE = new HashMap<String, Character>() {
        {
            put("Gamma", 'Γ');
            put("Delta", '∆');
            put("Theta", 'Θ');
            put("Lambda", 'Λ');
            put("Xi", 'Ξ');
            put("Pi", 'Π');
            put("Sigma", 'Σ');
            put("Upsilon", 'Υ');
            put("Phi", 'Φ');
            put("Psi", 'Φ');
            put("Omega", 'Ω');
        }
    };
    
}