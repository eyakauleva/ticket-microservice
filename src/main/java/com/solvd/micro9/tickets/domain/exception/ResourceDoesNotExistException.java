package com.solvd.micro9.tickets.domain.exception;

public class ResourceDoesNotExistException extends RuntimeException {

    public ResourceDoesNotExistException(String message) {
        super(message);
    }

}
