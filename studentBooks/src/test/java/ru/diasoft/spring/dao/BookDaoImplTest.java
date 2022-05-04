package ru.diasoft.spring.dao;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Genre;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с книгами должно")
@DataJpaTest
@Import(BookDaoImpl.class)
@ComponentScan(basePackages = "ru.diasoft.spring.config")
public class BookDaoImplTest {

    private static final long EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_NAME = "bookTest";
    private static final String EXISTING_GENRE_NAME = "genreTest";
    private static final String EXISTING_AUTHOR_NAME = "authorTest";

    @Autowired
    private BookDaoImpl bookDao;

    @Autowired
    private TestEntityManager em;

    @Rollback(value = true)
    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Book expectedBook = createBook();
        bookDao.insert(expectedBook);


        Book actualBook = em.find(Book.class, expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемую книгу по её автору")
    @Test
    void shouldReturnExpectedBookByAuthorId() {
        Book expectedBook = createBook();
        em.persist(expectedBook);
        List<Book> actualBooks =  bookDao.getBookByAuthor(expectedBook.getAuthor());
        assertThat(actualBooks).usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedBook);
    }

    @DisplayName("возвращать ожидаемую книгу по её id")
    @Test
    void shouldReturnExpectedBookById() {
        Book expectedBook = createBook();
        em.persist(expectedBook);
        Optional<Book> actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook.get()).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("удалять заданную книгу по её id")
    @Test
    void shouldCorrectDeleteBookById() {
        Book expectedBook = createBook();
        em.persist(expectedBook);
        assertThatCode(() -> bookDao.getById(expectedBook.getId()))
                .doesNotThrowAnyException();
        bookDao.delete(expectedBook.getId());
        em.detach(expectedBook);
        assertThat(bookDao.getById(expectedBook.getId()))
                .isEqualTo(Optional.empty());
    }


    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksList() {
        Book expectedBook = createBook();
        em.persist(expectedBook);
        List<Book> actualBookList = bookDao.getAll();
        assertThat(actualBookList)
                .contains(expectedBook);
    }

    private Book createBook(){
        Book expectedBook = new Book();

        Genre genre = new Genre();
        genre.setName(EXISTING_GENRE_NAME);

        Author author = new Author();
        author.setName(EXISTING_AUTHOR_NAME);

        expectedBook.setName(EXISTING_BOOK_NAME);
        expectedBook.setAuthor(author);
        expectedBook.setGenre(genre);

        return expectedBook;
    }

}
