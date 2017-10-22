package me.ron.fastioc.core.exception;

public class NoSuchConstructorException extends RuntimeException {

    public NoSuchConstructorException() {
    }

    public NoSuchConstructorException(String message) {
        super(message);
    }

    public NoSuchConstructorException(String message, Throwable cause) {
        super(message, cause);
    }
}
