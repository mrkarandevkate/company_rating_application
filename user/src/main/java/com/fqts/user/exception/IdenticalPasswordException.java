package com.fqts.user.exception;

public class IdenticalPasswordException extends RuntimeException {
    public IdenticalPasswordException(String message) {
        super(message);
    }
}
