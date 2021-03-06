package com.example.divine.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), status.value(), "Validation Failed",
                ex.getBindingResult().toString());
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = StudentNotFoundException.class)
    public final ResponseEntity<Object> handleStudentNotFound(StudentNotFoundException ex, WebRequest request ) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), 404, ex.getMessage(),
                ex.getMessage());

        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }
}
