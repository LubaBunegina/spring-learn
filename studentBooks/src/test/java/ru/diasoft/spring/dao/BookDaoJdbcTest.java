package ru.diasoft.spring.dao;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import(BookDaoJdbc.class)
public class BookDaoJdbcTest {

    private static final long EXISTING_AUTHOR_ID = 1;
    private static final long EXISTING_GENRE_ID = 1;
    private static final long EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_NAME = "bookTest";
    private static final String EXISTING_GENRE_NAME = "genreTest";
    private static final String EXISTING_AUTHOR_NAME = "authorTest";

    @Autowired
    private BookDaoJdbc bookDao;

    @Rollback(value = true)
    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Book expectedBook = new Book(2, "bookTest2", new Genre(1, "genreTest"),new Author(1, "authorTest"));
        bookDao.insert(expectedBook);
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемую книгу по её автору id")
    @Test
    void shouldReturnExpectedBookByAuthorId() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME),
                new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME));
        List<Book> actualBooks =  bookDao.getByAuthorId(expectedBook.getAuthor().getId());
        assertThat(actualBooks).usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedBook);
    }

    @DisplayName("возвращать ожидаемую книгу по её id")
    @Test
    void shouldReturnExpectedBookById() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME),
                new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME));
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("удалять заданную книгу по её id")
    @Test
    void shouldCorrectDeletePersonById() {
        assertThatCode(() -> bookDao.getById(EXISTING_BOOK_ID))
                .doesNotThrowAnyException();

        bookDao.delete(EXISTING_BOOK_ID);

        assertThatThrownBy(() -> bookDao.getById(EXISTING_BOOK_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }


    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedPersonsList() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME),
                new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME));
        List<Book> actualPersonList = bookDao.getAll();
        assertThat(actualPersonList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedBook);
    }

}
