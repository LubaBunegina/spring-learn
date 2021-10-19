package ru.diasoft.spring.service;

import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Genre;

import java.util.List;

public interface BookService {
    void insert(Book book);
    void update(Book book);
    void delete(Long id);
    void printBookInfo(Book book, Author author, Genre genre);
    Book getById(Long id);
    List<Book> getByAuthorId(Long authorId);
    List<Book> getAll();
    Long getMaxId();
}
