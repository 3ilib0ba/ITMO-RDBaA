package com.example.demo.studio.exceptions;

public class TinIsAlreadyExistException extends RuntimeException {
    public TinIsAlreadyExistException(String tin) {
        super("Tin[" + tin + "] is used by another user");
    }
}
