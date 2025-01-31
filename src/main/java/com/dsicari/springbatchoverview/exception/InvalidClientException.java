package com.dsicari.springbatchoverview.exception;

public class InvalidClientException extends RuntimeException {
    public InvalidClientException(String message) {
        super(message);
    }
}
