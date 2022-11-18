package com.registration.exception;

public class CustomerExistException extends RuntimeException {
    public CustomerExistException(String message) {
        super(message);
    }
}
