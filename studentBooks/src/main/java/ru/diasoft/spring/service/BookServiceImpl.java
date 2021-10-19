package ru.diasoft.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diasoft.spring.dao.BookDao;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Genre;

import java.util.List;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Override
    public void insert(Book book) {
        bookDao.insert(book);
    }

    @Override
    public void update(Book book) {
        bookDao.update(book);
    }

    @Override
    public void delete(Long id) {
        bookDao.delete(id);
    }

    @Override
    public void printBookInfo(Book book, Author author, Genre genre) {
        System.out.println("ID: " + book.getId()
                + " Name: " + book.getName()
                + " Genre: " + genre.getName()
                + " Author: " + author.getName());
    }

    @Override
    public Book getById(Long id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getByAuthorId(Long authorId) {
        return bookDao.getByAuthorId(authorId);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public Long getMaxId() {
        return bookDao.getMaxId();
    }
}
