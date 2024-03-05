package com.github.juhachmann.estagios.core.exceptions;

public class InvalidUserException extends IllegalArgumentException {

    public InvalidUserException(String message) {
        super(message);
    }

    public InvalidUserException(String message, Throwable cause) {
        super(message, cause);
    }

}
