package com.br.myhealth.exception;

public class HealthException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public HealthException() {
        super();
    }

    public HealthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HealthException(String message, Throwable cause) {
        super(message, cause);
    }

    public HealthException(String message) {
        super(message);
    }

    public HealthException(Throwable cause) {
        super(cause);
    }
}
