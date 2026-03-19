package com.gv.dpal.account.exception;

public class AccountDoesNotExistException extends RuntimeException {

    public AccountDoesNotExistException(String message) {
        super(message);
    }

    public AccountDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
