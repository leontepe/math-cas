
package com.leontepe.exception;

@SuppressWarnings("serial")
public class ExpressionParsingException extends RuntimeException {

    public ExpressionParsingException() {}

    public ExpressionParsingException(String message) {
        super(message);
    }

}