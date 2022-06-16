package ru.diasoft.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Genre;


import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с книгами должно")
@DataJpaTest
@ComponentScan(basePackages = "ru.diasoft.spring.config")
public class BookRepositoryTest {

    private static final long EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_NAME = "bookTest";
    private static final String EXISTING_GENRE_NAME = "genreTest";
    private static final String EXISTING_AUTHOR_NAME = "authorTest";

    @Autowired
    private BookRepository bookDao;

    @Autowired
    private TestEntityManager em;

    @DisplayName("удалять заданную книгу по её id")
    @Test
    void shouldCorrectDeleteBookById() {
        Book book = createBook();
        final Long id = em.persistAndGetId(book, Long.class);
        bookDao.deleteById(id);
        em.flush();
        Book after = em.find(Book.class, id);
        assertThat(after).isNull();
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
