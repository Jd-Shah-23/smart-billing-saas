package com.jaydeep.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class UserRequest {

    @NotBlank(message = "Login id must not be blank")
    private final String loginId;
    @NotBlank(message = "Name must not be blank")
    private final String userName;
    @NotBlank(message = "Name must not be blank")
    @Email(message = "Invalid email id")
    private final String userEmail;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@#$%^&+=]{6,}$",
            message = "Password must be minimum 6 characters, include letters and numbers"
    )
    private final String userPassword;
    private final String userCity;
}
