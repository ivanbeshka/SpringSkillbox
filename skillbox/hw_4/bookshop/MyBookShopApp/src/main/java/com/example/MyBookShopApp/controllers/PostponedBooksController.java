package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class PostponedBooksController {
    private final BookService bookService;

    @Autowired
    public PostponedBooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("postponedBooks")
    public List<Book> recommendedBooks() {
        return bookService.getBooksData();
    }

    @GetMapping("/postponed")
    public String main() {
        return "postponed";
    }
}
