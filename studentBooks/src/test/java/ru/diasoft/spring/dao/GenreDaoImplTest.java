package ru.diasoft.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import ru.diasoft.spring.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с жанрами должно")
@DataJpaTest
@Import(GenreDaoImpl.class)
@ComponentScan(basePackages = "ru.diasoft.spring.config")
public class GenreDaoImplTest {

    private static final long EXISTING_GENRE_ID = 1;
    private static final String EXISTING_GENRE_NAME = "genreTest";

    @Autowired
    private GenreDaoImpl genreDao;

    @Autowired
    private TestEntityManager em;

    @Rollback(value = true)
    @DisplayName("добавлять жанр в БД")
    @Test
    void shouldInsertGenre() {
        Genre expectedGenre = new Genre();
        expectedGenre.setName("genre2");
        genreDao.insert(expectedGenre);
        Optional<Genre> actualGenre = genreDao.getById(expectedGenre.getId());
        assertThat(actualGenre.get()).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("получать ожидаемый жанр по ид")
    @Test
    void shouldReturnGenreById() {
        Genre expectedGenre = new Genre();
        expectedGenre.setName(EXISTING_GENRE_NAME);
        em.persist(expectedGenre);
        Optional<Genre> actualGenre = genreDao.getById(expectedGenre.getId());
        assertThat(actualGenre.get()).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("получать ожидаемый жанр по его названию")
    @Test
    void shouldReturnGenreByName() {
        Genre expectedGenre = new Genre();
        expectedGenre.setName(EXISTING_GENRE_NAME);
        em.persist(expectedGenre);
        Optional<Genre> actualGenre = genreDao.getByName(expectedGenre.getName());
        assertThat(actualGenre.get()).usingRecursiveComparison().isEqualTo(expectedGenre);
    }


}
