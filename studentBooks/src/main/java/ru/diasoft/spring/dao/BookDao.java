package ru.diasoft.spring.dao;

import ru.diasoft.spring.domain.Book;

import java.util.List;

public interface BookDao {
    void insert(Book book);
    void update(Book book);
    void delete(Long id);
    Book getById(Long id);
    List<Book> getByAuthorId(Long authorId);
    List<Book> getAll();
    Long getMaxId();
}
