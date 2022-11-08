package org.example.app.exceptions;

public class LoginException extends Exception {

    private final String message;

    public LoginException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
