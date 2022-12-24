package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.book.BookService;
import com.example.MyBookShopApp.data.book.BooksPageDto;
import com.example.MyBookShopApp.data.genre.GenreEntity;
import com.example.MyBookShopApp.data.genre.GenreService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class GenreController {
    private final BookService bookService;
    private final GenreService genreService;

    private static final int BOOKS_BY_GENRE_LIMIT = 20;

    public GenreController(BookService bookService, GenreService genreService) {
        this.bookService = bookService;
        this.genreService = genreService;
    }

    @ModelAttribute("genres")
    public List<GenreEntity> getGenres() {
        List<GenreEntity> genres = genreService.getGenres();
        genres.forEach(genre -> {
            for (GenreEntity genreChild : genres) {
                if (Objects.equals(genreChild.getParentId(), genre.getId())) {
                    genre.addNewChild(genreChild);
                }
            }
        });
        return genres;
    }

    @GetMapping("/genres")
    public String getGenresPage() {
        return "genres/index";
    }

    @GetMapping("/books/genre/{genreId}")
    @ResponseBody
    public BooksPageDto getGenreBooksPage(
            @PathVariable("genreId") Integer genreId,
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit
    ) {
        return new BooksPageDto(bookService.getBooksByGenreId(genreId, offset, limit).getContent());
    }

    @GetMapping("/genres/{genreId}")
    public String getGenrePage(Model model, @PathVariable("genreId") Integer genreId) {
        Page<Book> page = bookService.getBooksByGenreId(genreId, 0, BOOKS_BY_GENRE_LIMIT);
        model.addAttribute("genre", genreService.getGenre(genreId));
        model.addAttribute("genreBooks", page.getContent());
        model.addAttribute("path", genreService.getGenresPath(genreId));
        return "genres/slug";
    }
}
