package com.hillsol.exceptions;

public class DuplicateCardException extends RuntimeException{

    public DuplicateCardException(String message) {
        super(message);
    }
}
