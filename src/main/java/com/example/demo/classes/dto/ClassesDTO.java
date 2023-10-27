package com.example.demo.classes.dto;

import com.example.demo.common.annotations.CustomJsonTimeDeserializer;
import com.example.demo.common.annotations.ToUpperCase;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClassesDTO {
    @JsonView
    @NotBlank(message = "Name is required")
    @ToUpperCase
    private String name;
    @JsonView
    @NotNull(message = "Date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @JsonView
    @NotNull(message = "Start cannot be null")
    @JsonDeserialize(using = CustomJsonTimeDeserializer.class)
    private Time start;
    @JsonView
    @NotNull(message = "End cannot be null")
    @JsonDeserialize(using = CustomJsonTimeDeserializer.class)
    private Time end;
    @JsonView
    @NotNull(message = "Amount cannot be null")
    @Min(value = 0, message = "Amount must be >= 0")
    private Float amount;
    @JsonView
    @NotNull(message = "Pos-id cannot be null")
    @Min(value = 1, message = "Pos-id must be >= 1")
    private Long posId;
}
