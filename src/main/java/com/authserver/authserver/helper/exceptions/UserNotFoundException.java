package com.authserver.authserver.helper.exceptions;

public
class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}