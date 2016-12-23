package com.demo.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by kingwu on 15/12/2016.
 */
@ControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler (value = Exception.class)
    public String handleException(Exception e) {
        e.printStackTrace();
        return "index";
    }
}
