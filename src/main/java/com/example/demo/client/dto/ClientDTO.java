package com.example.demo.client.dto;

import com.example.demo.common.annotations.ToUpperCase;
import com.example.demo.common.enums.Gender;
import com.example.demo.common.annotations.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    @JsonView
    @NotBlank(message = "Name is required")
    private String name;
    @JsonView
    @NotBlank(message = "Mail is required")
    @Email(message = "Incorrect mail pattern")
    private String mail;
    @JsonView
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "\\+7[0-9]{10}", message = "Phone-number must start with +7, then - 10 numbers more")
    private String phone;
    @JsonView
    @NotBlank(message = "Gender is required")
    @ToUpperCase
    @ValueOfEnum(enumClass = Gender.class)
    private String gender;
}