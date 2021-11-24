package ru.diasoft.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.spring.dao.GenreDao;
import ru.diasoft.spring.domain.Genre;
import ru.diasoft.spring.repository.GenreRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreDao;

    @Override
    public void insert(Genre genre) {
        genreDao.save(genre);
    }

    @Override
    public Genre getById(Long id) {
        Optional<Genre> optionalGenre = genreDao.findById(id);
        return optionalGenre.get();
    }

    @Override
    public Genre getByName(String name) {
        Optional<Genre> optionalGenre = genreDao.findByName(name);
        if(optionalGenre.isPresent()){
            return optionalGenre.get();
        } else {
            return null;
        }

    }

}
