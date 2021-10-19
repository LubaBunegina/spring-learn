package ru.diasoft.spring.service;

import ru.diasoft.spring.domain.Author;

import java.util.List;

public interface AuthorService {
    void insert(Author author);
    Author getById(Long id);
    Author getByName(String name);
    Long getMaxId();
}
