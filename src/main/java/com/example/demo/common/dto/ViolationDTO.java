package com.example.demo.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ViolationDTO {
    private final String fieldName;
    private final String message;
}
