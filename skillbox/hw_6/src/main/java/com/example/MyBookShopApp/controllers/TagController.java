package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.book.BookService;
import com.example.MyBookShopApp.data.book.BooksPageDto;
import com.example.MyBookShopApp.data.tag.TagService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TagController {
    private final BookService bookService;
    private final TagService tagService;

    private final static int PAGE_LIMIT = 20;

    public TagController(BookService bookService, TagService tagService) {
        this.bookService = bookService;
        this.tagService = tagService;
    }

    @GetMapping("/books/tag/{id}")
    @ResponseBody
    public BooksPageDto getPageByTagId(
            @PathVariable(name = "id") Integer id,
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit
    ) {
        return new BooksPageDto(bookService.getBooksByTagId(id, offset, limit).getContent());
    }

    @GetMapping("/tags/{tagId}")
    public String tagsPage(@PathVariable(name = "tagId") Integer tagId, Model model) {
        Page<Book> page = bookService.getBooksByTagId(tagId, 0, PAGE_LIMIT);
        model.addAttribute("tagBooks", page.getContent());
        model.addAttribute("tag", tagService.getTagById(tagId));
        return "/tags/index";
    }
}
