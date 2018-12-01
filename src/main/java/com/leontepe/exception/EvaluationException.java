
package com.leontepe.exception;

@SuppressWarnings("serial")
public class EvaluationException extends RuntimeException {

    public EvaluationException() {}

    public EvaluationException(String message) {
        super(message);
    }

}