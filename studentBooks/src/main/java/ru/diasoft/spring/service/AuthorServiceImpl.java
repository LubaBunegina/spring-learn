package ru.diasoft.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.spring.dao.AuthorDao;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorDao;

    @Override
    public void insert(Author author) {
        authorDao.save(author);
    }

    @Override
    public Author getById(Long id) {
        Optional<Author> optionalAuthor = authorDao.findById(id);
        return optionalAuthor.get();
    }

    @Transactional
    @Override
    public Author getByName(String name) {
        Optional<Author> optionalAuthor = authorDao.findByName(name);
        if(optionalAuthor.isPresent()) {
            return optionalAuthor.get();
        } else {
            return null;
        }
    }

}
