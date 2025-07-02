package com.kairos_assignment.lucas.infrastructure.rest.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
