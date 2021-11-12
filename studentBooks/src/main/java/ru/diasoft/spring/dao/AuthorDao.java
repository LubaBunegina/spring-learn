package ru.diasoft.spring.dao;

import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;

import java.util.Optional;

public interface AuthorDao {
    void insert(Author author);
    Optional<Author> getById(Long id);
    Optional<Author> getByName(String name);
}
