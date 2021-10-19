package ru.diasoft.spring.dao;

import ru.diasoft.spring.domain.Genre;

import java.util.List;

public interface GenreDao {
    void insert(Genre genre);
    Genre getById(Long id);
    Genre getByName(String name);
    Long getMaxId();
}
