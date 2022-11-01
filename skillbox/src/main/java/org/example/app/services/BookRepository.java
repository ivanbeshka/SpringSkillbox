package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        if (!book.getAuthor().isEmpty() && !book.getAuthor().replace(" ", "").isEmpty()
                || !book.getTitle().isEmpty() && !book.getTitle().replace(" ", "").isEmpty()
                || book.getSize() != null
        ) {
            book.setId(book.hashCode());
            logger.info("store new book: " + book);
            repo.add(book);
        }
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        for (Book book : retreiveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
            }
        }
        return false;
    }

    public boolean removeItemsByRegex(String regex) {
        List<Book> toRemove = retreiveAll().stream()
                .filter(book -> book.getTitle().contains(regex)
                        || book.getAuthor().contains(regex)
                        || book.getSize().toString().contains(regex))
                .collect(Collectors.toList());
        return repo.removeAll(toRemove);
    }
}
