package com.jaydeep.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class ApiResponse<T> {
    private String message;
    private T data;
}
