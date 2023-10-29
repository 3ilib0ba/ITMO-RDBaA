package com.example.demo.classifiers.dto;

import com.example.demo.common.annotations.ToUpperCase;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class ClassifierDTO {
    @JsonView
    @NotBlank(message = "Name is required")
    @ToUpperCase
    private String name;

    @JsonView
    @NotBlank(message = "Value is required")
    @ToUpperCase
    private String value;

}
