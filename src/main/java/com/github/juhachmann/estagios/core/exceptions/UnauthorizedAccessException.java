package com.github.juhachmann.estagios.core.exceptions;

/**
 * When user has no authorized access to a resource
 */
public class UnauthorizedAccessException extends Exception {

    public UnauthorizedAccessException() {
    }

    public UnauthorizedAccessException(String message) {
        super(message);
    }

    public UnauthorizedAccessException(String message, Throwable cause) {
        super(message, cause);
    }

}
