package ru.diasoft.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.spring.dao.GenreDao;
import ru.diasoft.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

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
        Optional<Genre> optionalGenre = genreDao.getById(id);
        return optionalGenre.get();
    }

    @Transactional
    @Override
    public Genre getByName(String name) {
        Optional<Genre> optionalGenre = genreDao.getByName(name);
        if(optionalGenre.isPresent()){
            return optionalGenre.get();
        } else {
            return null;
        }

    }

}
