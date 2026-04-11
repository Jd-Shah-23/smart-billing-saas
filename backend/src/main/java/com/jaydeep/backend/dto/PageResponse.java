package com.jaydeep.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class PageResponse<T> {
    private String message;
    private T data;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
