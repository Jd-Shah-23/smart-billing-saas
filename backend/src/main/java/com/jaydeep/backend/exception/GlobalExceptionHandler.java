package com.jaydeep.backend.exception;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> validationHandler(MethodArgumentNotValidException ex)
    {
        Map<String, String> errors = new HashMap<String,String>();
        for(FieldError e :  ex.getBindingResult().getFieldErrors() )
        {
            errors.put(e.getField(),e.getDefaultMessage());
        }
        return errors;
    }

}
