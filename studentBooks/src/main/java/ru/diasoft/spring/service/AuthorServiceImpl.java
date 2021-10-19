package ru.diasoft.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diasoft.spring.dao.AuthorDao;
import ru.diasoft.spring.domain.Author;

import java.util.List;

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
        return authorDao.getById(id);
    }

    @Override
    public Author getByName(String name) {
        return authorDao.getByName(name);
    }

    @Override
    public Long getMaxId() {
        return authorDao.getMaxId();
    }
}
