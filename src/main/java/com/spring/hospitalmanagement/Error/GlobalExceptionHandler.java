package com.spring.hospitalmanagement.Error;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiError> HandleUsernameNotFound(UsernameNotFoundException ex){


        ApiError err = new ApiError("username Not found"+ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(err,err.getStatusCode());


    }




}
