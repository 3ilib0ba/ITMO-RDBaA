package com.example.demo.common.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class ErrorDTO {
    private final String code;
    private final String message;
    private final LocalDateTime time;
}
