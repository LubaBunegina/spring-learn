package ru.diasoft.spring.dao;

import ru.diasoft.spring.domain.Author;

import java.util.List;

public interface AuthorDao {
    void insert(Author author);
    Author getById(Long id);
    Author getByName(String name);
    Long getMaxId();
}
