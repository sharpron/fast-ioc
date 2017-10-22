package me.ron.fastioc.core.exception;

public class NotHaveDefaultConstructor extends RuntimeException {

    public NotHaveDefaultConstructor() {
    }

    public NotHaveDefaultConstructor(String message) {
        super(message);
    }

    public NotHaveDefaultConstructor(String message, Throwable cause) {
        super(message, cause);
    }

    public NotHaveDefaultConstructor(Throwable cause) {
        super(cause);
    }

    public NotHaveDefaultConstructor(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
