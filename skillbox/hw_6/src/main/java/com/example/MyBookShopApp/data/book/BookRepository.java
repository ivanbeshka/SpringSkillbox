package com.example.MyBookShopApp.data.book;

import com.example.MyBookShopApp.data.author.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByAuthorFirstName(String firstName);

    @Query("from Book")
    List<Book> customFindAllBooks();

    List<Book> findBooksByAuthorFirstNameContaining(String authorFirstName);

    List<Book> findBooksByTitleContaining(String bookTitle);

    List<Book> findBooksByPriceOldBetween(Integer min, Integer max);

    List<Book> findBooksByPriceOldIs(Integer price);

    @Query("from Book where isBestseller = 1")
    List<Book> getBestsellers();

    @Query(value = "SELECT * FROM books WHERE discount = (SELECT MAX(discount) FROM books)", nativeQuery = true)
    List<Book> getBooksWithMaxDiscount();

    Page<Book> findBookByTitleContaining(String bookTitle, Pageable nextPage);

    Page<Book> findAllByOrderByPubDateDesc(Pageable nextPage);

    @Query(value = "SELECT * FROM books " +
            "WHERE pub_date BETWEEN :startDate AND :endDate " +
            "ORDER BY pub_date DESC", nativeQuery = true)
    Page<Book> getAllBooksBetweenDates(
            Pageable nextPage,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

    Page<Book> findAllByTagId(Pageable nextPage, Integer tagId);

    Page<Book> findAllByGenreId(Pageable nextPage, Integer genreId);

    Page<Book> findAllByAuthorId(Pageable nextPage, Integer authorId);

    Page<Book> findBooksByAuthor(Pageable nextPage, Author author);
}
