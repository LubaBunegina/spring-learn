package ru.diasoft.spring.service;

import ru.diasoft.spring.domain.Genre;

import java.util.List;

public interface GenreService {
    void insert(Genre genre);
    Genre getById(Long id);
    Genre getByName(String name);
    Long getMaxId();
}
