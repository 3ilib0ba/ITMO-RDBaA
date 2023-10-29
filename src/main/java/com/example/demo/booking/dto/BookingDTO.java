package com.example.demo.booking.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class BookingDTO {
    @JsonView
    @NotNull(message = "Client-id cannot be null")
    @Min(value = 1, message = "Client-id must be >= 1")
    private final Long clientId;
    @JsonView
    @NotNull(message = "Class-id cannot be null")
    @Min(value = 1, message = "Class-id must be >= 1")
    private final Long classId;
}
