package org.example.web.controllers;

import org.example.app.exceptions.BookShelfLoginException;
import org.example.app.exceptions.UploadNullFileException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(BookShelfLoginException.class)
    public String handleError(Model model, BookShelfLoginException e) {
        model.addAttribute("errorMessage", e.getMessage());
        return "errors/404";
    }

    @ExceptionHandler(UploadNullFileException.class)
    public String handleNullMultipartFileError(Model model, UploadNullFileException e)
    {
        model.addAttribute("errorMessage", e.getMessage());
        return "errors/400";
    }
}