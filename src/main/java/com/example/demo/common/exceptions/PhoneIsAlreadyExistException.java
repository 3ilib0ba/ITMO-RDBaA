package com.example.demo.common.exceptions;

public class PhoneIsAlreadyExistException extends RuntimeException {
    public PhoneIsAlreadyExistException(String phone) {
        super("Phone[" + phone + "] is used by another user");
    }
}
