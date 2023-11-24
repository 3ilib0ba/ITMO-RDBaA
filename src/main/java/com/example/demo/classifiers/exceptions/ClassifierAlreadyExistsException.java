package com.example.demo.classifiers.exceptions;

public class ClassifierAlreadyExistsException extends RuntimeException {
    public ClassifierAlreadyExistsException(String name, String value) {
        super("Classifier with name[" + name + "] and value[" + value + "] already exists");
    }
}
