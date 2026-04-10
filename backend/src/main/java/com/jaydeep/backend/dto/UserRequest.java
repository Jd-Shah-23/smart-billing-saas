package com.jaydeep.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class UserRequest {
    @NotBlank(message = "Name must not be blank")
    private final String name;
    @NotBlank(message = "Name must not be blank")
    @Email(message = "Invalid email id")
    private final String email;
}
