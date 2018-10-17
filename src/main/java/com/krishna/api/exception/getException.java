package com.krishna.api.exception;

public class getException extends Exception{

    private String errorCode;

    public getException(String message) {
        super(message);
    }

    public getException(String message, Throwable cause) {
        super(message, cause);
    }

    public getException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getException() {
        return errorCode;
    }
}
