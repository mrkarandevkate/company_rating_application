package com.fqts.user.exception;

public class DuplicateEmailException  extends  RuntimeException{
    public DuplicateEmailException(String message) {
        super(message);
    }
}
