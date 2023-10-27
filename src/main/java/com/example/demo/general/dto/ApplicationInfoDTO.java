package com.example.demo.general.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationInfoDTO {
    @JsonView
    @NotBlank(message = "Application-name is required")
    private String applicationName;
    @JsonView
    @NotBlank(message = "Author-name is required")
    private String authorName;
}
