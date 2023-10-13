package com.example.carsharing.error.borrow;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class RentErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RentNotFoundException.class)
    public ResponseEntity<Object> handleCarNotFoundException(RentNotFoundException exception) {
        return new ResponseEntity<>("Rent with given id doesn't exists", HttpStatus.BAD_REQUEST);
    }
}
