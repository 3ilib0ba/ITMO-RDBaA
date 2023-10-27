package com.example.demo.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationErrorDTO {
    private List<ViolationDTO> violations = new ArrayList<>();
}
