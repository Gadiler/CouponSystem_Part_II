/*
 * Copyright (c) // Author Gadiler 2.5.2021, 13:11.
 * All rights reserved to Gadi Engelsman.
 * https://github.com/Gadiler
 */

package com.example.demo.advice;

import com.example.demo.exceptions.CustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class CustomerControllerAdvice {

    @ExceptionHandler(CustomerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleErrors(Exception e) {
        return new ErrorDetails("CatException: Something went wrong!", e.getMessage(), 400);
    }
}
