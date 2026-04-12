package com.jaydeep.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class UserResponse {
    private final Long id;
    private final String userName;
    private final String userEmail;
    private final String userCity;

}
