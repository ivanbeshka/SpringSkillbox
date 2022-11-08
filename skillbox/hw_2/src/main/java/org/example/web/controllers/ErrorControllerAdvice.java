package org.example.web.controllers;

import org.example.app.exceptions.LoginException;
import org.example.app.exceptions.NullMultipartFileException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(LoginException.class)
    public String handleError(Model model, LoginException e) {
        model.addAttribute("errorMessage", e.getMessage());
        return "errors/404";
    }

    @ExceptionHandler(NullMultipartFileException.class)
    public String handleNullMultipartFileError(Model model, NullMultipartFileException e)
    {
        model.addAttribute("errorMessage", e.getMessage());
        return "errors/400";
    }
}
