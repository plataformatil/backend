package com.til.users.domain.exceptions;

public class InvalidAddressException extends Exception {
    public InvalidAddressException(String message) {
        super(message);
    }
}
