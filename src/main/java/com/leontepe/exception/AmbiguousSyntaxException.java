package com.leontepe.exception;

public class AmbiguousSyntaxException extends Exception {
    
    public AmbiguousSyntaxException() {
        super();
    }

    public AmbiguousSyntaxException(String message) {
        super(message);
    }
}