package com.example.demo.classes.exceptions;

public class ClassesNotFoundException extends RuntimeException {
    public ClassesNotFoundException(Long classesId) {
        super("Classes with id[" + classesId.toString() + "] not found");
    }
}
