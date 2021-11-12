package ru.diasoft.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diasoft.spring.dao.GenreDao;
import ru.diasoft.spring.domain.Genre;

import java.util.List;

@AllArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public void insert(Genre genre) {
        genreDao.insert(genre);
    }

    @Override
    public Genre getById(Long id) {
        return genreDao.getById(id);
    }

    @Override
    public Genre getByName(String name) {
        return genreDao.getByName(name);
    }

    @Override
    public Long getMaxId() {
        return genreDao.getMaxId();
    }
}
