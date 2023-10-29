package com.example.demo.studio.dto;

import com.example.demo.common.annotations.ToUpperCase;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
public class StudioDTO {
    @JsonView
    @NotBlank(message = "Name is required")
    @ToUpperCase
    private String name;
    @JsonView
    @NotBlank(message = "Description is required")
    @ToUpperCase
    private String description;
    @JsonView
    @NotBlank(message = "Full-description is required")
    @ToUpperCase
    private String fullDescription;
    @JsonView
    @NotBlank(message = "Mail is required")
    @Email(message = "Incorrect mail pattern")
    private final String mail;
    @JsonView
    @Pattern(regexp = "\\+7[0-9]{10}", message = "Phone-number must start with +7, then - 10 numbers more")
    private final String phone;
    @JsonView
    @NotBlank(message = "Tin is required")
    @Pattern(regexp = "\\d{4}-\\d{3}", message = "TIN must match the pattern xxxx-xxx")
    private final String tin;
}
