package com.example.demo.studio.exceptions;

import com.example.demo.client.exceptions.RoleNotFoundException;
import com.example.demo.common.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
@ResponseBody
public class StudioExceptionHandler {
    @ExceptionHandler(PositionsNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handlePositionNotFoundException(PositionsNotFoundException ex) {
        return new ErrorDTO(
                HttpStatus.BAD_REQUEST.name(), ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(StudioNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleStudioNotFoundException(StudioNotFoundException ex) {
        return new ErrorDTO(
                HttpStatus.BAD_REQUEST.name(), ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(TinIsAlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleTinIsAlreadyExistException(TinIsAlreadyExistException ex) {
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
