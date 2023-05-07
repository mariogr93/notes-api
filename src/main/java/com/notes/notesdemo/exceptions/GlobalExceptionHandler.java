package com.notes.notesdemo.exceptions;


import com.notes.notesdemo.model.response.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(
                GeneralResponse.builder()
                        .message(ex.getMessage())
                        .error( errors)
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST)
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .build());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<GeneralResponse> handleAuthenticationException(AuthenticationException exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(GeneralResponse.builder()
                        .message(exception.getMessage())
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.UNAUTHORIZED)
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .build());
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<GeneralResponse> handleEmailAlreadyRegisteredException(UsernameNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(GeneralResponse.builder()
                        .message(exception.getMessage())
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.CONFLICT)
                        .statusCode(HttpStatus.CONFLICT.value())
                        .build());
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<GeneralResponse> handleIllegalStateExceptionException(IllegalStateException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(GeneralResponse.builder()
                        .message(exception.getMessage())
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.CONFLICT)
                        .statusCode(HttpStatus.CONFLICT.value())
                        .build());
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

}
