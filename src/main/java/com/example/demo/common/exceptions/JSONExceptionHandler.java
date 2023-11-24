package com.example.demo.common.exceptions;

import com.example.demo.common.dto.ErrorDTO;
import com.example.demo.common.dto.ValidationErrorDTO;
import com.example.demo.common.dto.ViolationDTO;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@ControllerAdvice
@ResponseBody
@Slf4j
public class JSONExceptionHandler {
    @ExceptionHandler(UnrecognizedPropertyException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleUnrecognizedPropertyException(UnrecognizedPropertyException ex) {
        ErrorDTO errorDTO = new ErrorDTO(
                HttpStatus.BAD_REQUEST.name(), "Unidentified JSON field", LocalDateTime.now());
        return errorDTO;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationErrorDTO handleJsonException(MethodArgumentNotValidException ex) {
        ValidationErrorDTO error = new ValidationErrorDTO();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.getViolations().add(new ViolationDTO(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return error;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ValidationErrorDTO onHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ValidationErrorDTO error = new ValidationErrorDTO();
        String message = e.getMessage();
        log.info(message);
        String field = Pattern.compile("\"(.*?)\"")
                .matcher(message).results().map(mr -> mr.group(1)).findFirst().get();
        // String cause = message.substring(message.indexOf("expected"));
        error.getViolations().add(new ViolationDTO(field, message));
        return error;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ValidationErrorDTO onMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ValidationErrorDTO error = new ValidationErrorDTO();
        String message = e.getMessage();
        log.info(message);
        String field = Pattern.compile("\"(.*?)\"")
                .matcher(message).results().map(mr -> mr.group(1)).findFirst().get();
        // String cause = message.substring(message.indexOf("expected"));
        error.getViolations().add(new ViolationDTO(field, message));
        return error;
    }
}
