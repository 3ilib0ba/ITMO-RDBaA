package com.example.demo.classes.exceptions;

import com.example.demo.common.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
@ResponseBody
public class ClassesExceptionHandler {
    @ExceptionHandler(ClassesNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleClassesNotFoundException(ClassesNotFoundException ex) {
        return new ErrorDTO(
                HttpStatus.BAD_REQUEST.name(), ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(ClassesStartTimeGreaterOrEqualsClassesEndTimeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleClassesStartTimeGreaterOrEqualsClassesEndTimeException(ClassesStartTimeGreaterOrEqualsClassesEndTimeException ex) {
        return new ErrorDTO(
                HttpStatus.BAD_REQUEST.name(), ex.getMessage(), LocalDateTime.now());
    }
}
