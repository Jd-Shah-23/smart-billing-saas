package com.jaydeep.backend.dto;

import com.jaydeep.backend.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    @NotBlank
    private String customerName;
    @Email
    private String customerEmail;
    @NotBlank
    private String customerCity;
}
