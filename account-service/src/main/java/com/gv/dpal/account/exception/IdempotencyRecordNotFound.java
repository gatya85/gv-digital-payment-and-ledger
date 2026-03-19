package com.gv.dpal.account.exception;

public class IdempotencyRecordNotFound extends Exception {
    public IdempotencyRecordNotFound(String message) {
        super(message);
    }
}
