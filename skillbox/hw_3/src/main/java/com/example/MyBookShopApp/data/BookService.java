package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BookService {

    private JdbcTemplate jdbcTemplate;
    private AuthorService authorService;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate, AuthorService authorService) {
        this.jdbcTemplate = jdbcTemplate;
        this.authorService = authorService;
    }

    public List<Book> getBooksData(){

        List<Book> books = jdbcTemplate.query("SELECT * FROM books", (ResultSet rs, int rownum)->{
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(authorService.getAuthor(Integer.parseInt(rs.getString("author_id"))).getName());
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getInt("price_old"));
            book.setPrice(rs.getInt("price"));
            return book;
        });
        return new ArrayList<>(books);
    }

    public List<Book> getRecommendBooks(int countOfBooks)
    {
        List<Book> books = getBooksData();
        List<Book> recommendBooksList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < countOfBooks; i++) {
            int randomIndex = random.nextInt(books.size());
            recommendBooksList.add(books.get(randomIndex));
            books.remove(randomIndex);
        }

        return recommendBooksList;
    }
}
