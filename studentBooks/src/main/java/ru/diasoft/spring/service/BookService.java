package ru.diasoft.spring.service;

import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Genre;

import java.util.List;

public interface BookService {
    Book insert(String bookName, String genreName, String authorName) throws Exception;
    void update(Long bookId, String bookName, String authorName, String genreName) throws Exception;
    void delete(Long id) throws Exception;
    Book getById(Long id);
    List<Book> getBookByAuthor(String authorName) throws Exception;
    List<Book> getBookByGenre(String genreName) throws Exception;
    List<Book> getAll();
}
