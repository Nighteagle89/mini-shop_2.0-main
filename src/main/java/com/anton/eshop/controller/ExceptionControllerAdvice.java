package com.anton.eshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getException(Exception e, Model model) {
        String messageError;
        if (Objects.nonNull(e)) {
            messageError = e.getMessage();
        } else {
            messageError = "Unknown exception";
        }

        model.addAttribute("messageException", messageError);
        return "error";
    }
}
