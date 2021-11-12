package ru.diasoft.spring.dao;

import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void insert(Book book);
    void update(Book book);
    void delete(Long id);
    Optional<Book> getById(Long id);
    Optional<Book> getByName(String bookName);
    List<Book> getBookByAuthor(Author author);
    List<Book> getBookByGenre(Genre genre);
    List<Book> getAll();
}
