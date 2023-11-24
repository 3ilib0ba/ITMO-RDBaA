package com.example.demo.client.exceptions;

import com.example.demo.common.dto.ErrorDTO;
import com.example.demo.common.exceptions.MailIsAlreadyExistException;
import com.example.demo.common.exceptions.PhoneIsAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
@ResponseBody
public class ClientExceptionHandler {
    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleClientNotFoundException(ClientNotFoundException ex) {
        return new ErrorDTO(
                HttpStatus.BAD_REQUEST.name(), ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(MailIsAlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleMailsAlreadyExistException(MailIsAlreadyExistException ex) {
        return new ErrorDTO(
                HttpStatus.BAD_REQUEST.name(), ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(PhoneIsAlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handlePhoneIsAlreadyExistException(PhoneIsAlreadyExistException ex) {
        return new ErrorDTO(
                HttpStatus.BAD_REQUEST.name(), ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleRoleNotFoundException(RoleNotFoundException ex) {
        return new ErrorDTO(
                HttpStatus.BAD_REQUEST.name(), ex.getMessage(), LocalDateTime.now());
    }
}
