package com.authserver.authserver.helper.exceptions;

public
class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}