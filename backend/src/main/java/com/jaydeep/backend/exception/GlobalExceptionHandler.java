package com.jaydeep.backend.exception;

import com.jaydeep.backend.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<String, String>();
        for (FieldError e : ex.getBindingResult().getFieldErrors()) {
            errors.put(e.getField(), e.getDefaultMessage());
        }
        logger.error("Validation failed: {}", ex.getMessage());
        ErrorResponse response = new ErrorResponse(
                400,
                "Validation failed",
                errors
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> errors = new HashMap<String, String>();
        errors.put("Sort By", "Not a valid field present for sorting");
        logger.error("Invalid request: {}", ex.getMessage());
        ErrorResponse response = new ErrorResponse(
                400,
                "Invalid Field",
                errors
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> MissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        Map<String, String> errors = new HashMap<String, String>();
        errors.put(ex.getParameterName(), ex.getMessage());
        logger.error(ex.getParameterName(), ex.getMessage());
        ErrorResponse response = new ErrorResponse(
                400,
                "Required fields are missing.",
                errors
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParentNotFoundException.class)
    public ResponseEntity<ErrorResponse> UserNotFoundException(ParentNotFoundException ex)
    {
        Map<String, String> errors = new HashMap<String, String>();
        errors.put(ex.getParameterName(), ex.getParameterMessage());
        logger.error(ex.getParameterName(), ex.getParameterMessage());
        ErrorResponse response = new ErrorResponse(
                404,
                "User with given userId not found.",
                errors
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}