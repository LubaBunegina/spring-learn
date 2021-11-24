package ru.diasoft.spring.dao;

import ru.diasoft.spring.domain.Genre;

import java.util.Optional;

public interface GenreDao {
    void insert(Genre genre);
    Optional<Genre> getById(Long id);
    Optional<Genre> getByName(String name);
}
