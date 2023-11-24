package com.example.demo.general.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class ApplicationInfoDTO {
    @JsonView
    @NotBlank(message = "Application-name is required")
    private final String applicationName;

    @JsonView
    @NotBlank(message = "Author-name is required")
    private final String authorName;

}
