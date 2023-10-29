package com.example.demo.common.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorDTO {
    private final List<ViolationDTO> violations = new ArrayList<>();
}
