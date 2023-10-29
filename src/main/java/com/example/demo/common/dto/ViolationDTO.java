package com.example.demo.common.dto;

import lombok.Data;

@Data
public class ViolationDTO {
    private final String fieldName;
    private final String message;
}
