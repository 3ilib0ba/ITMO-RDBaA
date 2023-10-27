package com.example.demo.studio.dto;

import com.example.demo.common.annotations.ToUpperCase;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {
    @JsonView
    @NotNull(message = "Studio-id cannot be null")
    @Min(value = 1, message = "Studio-id must be >= 1")
    Long studioId;
    @JsonView
    @NotBlank(message = "Address is required")
    @ToUpperCase
    String address;
    @JsonView
    @NotBlank(message = "Hours is required")
    @Pattern(regexp = "([01]\\d|2[0-3]):([0-5]\\d)-([01]\\d|2[0-3]):([0-5]\\d)", message = "Hours must match the pattern hh:mm-hh:mm")
    String hours;
}
