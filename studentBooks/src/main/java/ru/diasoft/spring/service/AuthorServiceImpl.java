package ru.diasoft.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.spring.dao.AuthorDao;
import ru.diasoft.spring.domain.Author;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorDao authorDao;

    @Override
    public void insert(Author author) {
        authorDao.insert(author);
    }

    @Override
    public Author getById(Long id) {
        Optional<Author> optionalAuthor = authorDao.getById(id);
        return optionalAuthor.get();
    }

    @Transactional
    @Override
    public Author getByName(String name) {
        Optional<Author> optionalAuthor = authorDao.getByName(name);
        if(optionalAuthor.isPresent()) {
            return optionalAuthor.get();
        } else {
            return null;
        }
    }

}
