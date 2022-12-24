package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class RecentBooksController {
    private final BookService bookService;

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy");
    private static final int PAGE_LIMIT = 20;

    @Autowired
    public RecentBooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("newBooks")
    public List<Book> newBooks() throws ParseException {

        Date dateFrom = SDF.parse("14.11.2022");
        Date dateTo = SDF.parse("14.12.2022");
        return bookService.getPageOfNewBooksBetweenDates(0, PAGE_LIMIT, dateFrom, dateTo).getContent();
    }

    @GetMapping("/recent")
    public String getRecent() {
        return "recent";
    }
}
