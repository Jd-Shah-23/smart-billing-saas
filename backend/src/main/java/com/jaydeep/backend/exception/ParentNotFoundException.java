package com.jaydeep.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ParentNotFoundException extends RuntimeException {
    private final String parameterName;
    private final String parameterMessage;
}
