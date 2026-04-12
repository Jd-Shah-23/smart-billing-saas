package com.jaydeep.backend.dto;

import com.jaydeep.backend.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    private String customerName;
    private String customerEmail;
    private String customerCity;
    @NotNull
    private User user;
}
