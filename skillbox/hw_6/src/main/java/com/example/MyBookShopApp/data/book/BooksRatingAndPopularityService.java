package com.example.MyBookShopApp.data.book;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksRatingAndPopularityService {

    private static final double C_COEFF = 0.7;
    private static final double K_COEFF = 0.4;

    public List<Book> sortBooksByPopularity(List<Book> books) {
        List<Book> newBooks = books.stream()
                .sorted(Comparator
                        .comparingInt((Book o) -> (int) (o.getB() + o.getC() * C_COEFF + o.getK() * K_COEFF))
                        .reversed())
                .collect(Collectors.toList());
        newBooks.forEach(System.out::println);
        return newBooks;
    }
}
