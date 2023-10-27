package com.example.demo.instructor.exceptions;

public class InstructorNotFoundException extends RuntimeException {
    public InstructorNotFoundException(Long instructorId) {
        super("Instructor with id[" + instructorId.toString() + "] not found");
    }
}
