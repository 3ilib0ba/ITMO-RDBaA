package com.example.demo.common.exceptions;

public class MailIsAlreadyExistException extends RuntimeException {
    public MailIsAlreadyExistException(String mail) {
        super("Mail[" + mail + "] is used by another user");
    }
}
