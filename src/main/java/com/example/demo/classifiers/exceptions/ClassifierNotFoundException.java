package com.example.demo.classifiers.exceptions;

public class ClassifierNotFoundException extends RuntimeException {
    public ClassifierNotFoundException(String name, String value) {
        super("Classifier with name[" + name + "], value[" + value + "] not found");
    }
}
