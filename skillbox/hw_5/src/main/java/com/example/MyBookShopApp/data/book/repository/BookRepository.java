package com.example.MyBookShopApp.data.book.repository;

import com.example.MyBookShopApp.data.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByAuthorFirstName(String firstName);

    @Query("from Book")
    List<Book> findAllBooks();
}
