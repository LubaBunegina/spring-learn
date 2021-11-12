package ru.diasoft.spring.service;

import ru.diasoft.spring.domain.Author;


public interface AuthorService {
    void insert(Author author);
    Author getById(Long id);
    Author getByName(String name);
}
