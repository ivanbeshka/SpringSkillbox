package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.book.BookService;
import com.example.MyBookShopApp.data.book.BooksPageDto;
import com.example.MyBookShopApp.data.SearchWordDto;
import com.example.MyBookShopApp.data.tag.TagEntity;
import com.example.MyBookShopApp.data.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MainPageController {

    private final BookService bookService;
    private final TagService tagService;

    private static final int PAGES_LIMIT = 6;
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy");

    @Autowired
    public MainPageController(BookService bookService, TagService tagService) {
        this.bookService = bookService;
        this.tagService = tagService;
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookService.getPageOfRecommendedBooks(0, PAGES_LIMIT).getContent();
    }

    @ModelAttribute("newBooks")
    public List<Book> newBooks() {
        return bookService.getPageOfNewBooks(0, PAGES_LIMIT).getContent();
    }

    @ModelAttribute("popularBooks")
    public List<Book> popularBooks() {
        return bookService.getPageOfPopularBooks(0, PAGES_LIMIT);
    }

    @ModelAttribute("tags")
    public List<TagEntity> tags() {
        return tagService.getAllTags();
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<Book> searchResults() {
        return new ArrayList<>();
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksPageDto getBooksPage(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit
    ) {
        return new BooksPageDto(bookService.getPageOfRecommendedBooks(offset, limit).getContent());
    }

    @GetMapping("/books/popular")
    @ResponseBody
    public BooksPageDto getPopularBooksPage(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit
    ) {
        List<Book> popularBooks = bookService.getPageOfPopularBooks(offset, limit);
        return new BooksPageDto(popularBooks);
    }

    @GetMapping("/books/recent")
    @ResponseBody
    public BooksPageDto getNewBooksPage(
            @RequestParam(value = "from", required = false) String from,
            @RequestParam(value = "to", required = false) String to,
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit
    ) throws ParseException {

        List<Book> books;
        if (from == null || to == null) {
            books = bookService.getPageOfNewBooks(offset, limit).getContent();
        } else {
            Date dateFrom = SDF.parse(from);
            Date dateTo = SDF.parse(to);
            books = bookService.getPageOfNewBooksBetweenDates(offset, limit, dateFrom, dateTo).getContent();
        }
        return new BooksPageDto(books);
    }

    @GetMapping(value = {"/search", "/search/{searchWord}"})
    public String getSearchResults(
            @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto,
            Model model
    ) {
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults",
                bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), 0, PAGES_LIMIT).getContent());
        return "/search/index";
    }

    @GetMapping("/search/page/{searchWord}")
    @ResponseBody
    public BooksPageDto getNextSearchPage(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit,
            @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto
    ) {
        return new BooksPageDto(
                bookService.getPageOfSearchResultBooks(
                        searchWordDto.getExample(),
                        offset,
                        limit
                ).getContent());
    }
}
