package ru.diasoft.spring.dao;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import ru.diasoft.spring.domain.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с авторами должно")
@DataJpaTest
@Import(AuthorDaoImpl.class)
@ComponentScan(basePackages = "ru.diasoft.spring.config")
public class AuthorDaoImplTest {

    private static final String EXISTING_AUTHOR_NAME = "authorTest";

    @Autowired
    private AuthorDaoImpl authorDao;

    @Autowired
    private TestEntityManager em;

    @Rollback(value = true)
    @DisplayName("добавлять автора в БД")
    @Test
    void shouldInsertAuthor() {
        Author expectedAuthor = new Author();
        expectedAuthor.setName("author2");
        authorDao.insert(expectedAuthor);
        Optional<Author> actualAuthor = authorDao.getById(expectedAuthor.getId());
        assertThat(actualAuthor.get()).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("получать ожидаемого автора по ид")
    @Test
    void shouldReturnAuthorById() {
        Author expectedAuthor = new Author();
        expectedAuthor.setName(EXISTING_AUTHOR_NAME);
        em.persist(expectedAuthor);
        Optional<Author> actualAuthor = authorDao.getById(expectedAuthor.getId());
        assertThat(actualAuthor.get()).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("получать ожидаемого автора по его имени")
    @Test
    void shouldReturnAuthorByName() {
        Author expectedAuthor = new Author();
        expectedAuthor.setName(EXISTING_AUTHOR_NAME);
        em.persist(expectedAuthor);
        Optional<Author> actualAuthor = authorDao.getByName(expectedAuthor.getName());
        assertThat(actualAuthor.get()).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

}
