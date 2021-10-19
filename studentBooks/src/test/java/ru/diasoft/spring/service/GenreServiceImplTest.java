package ru.diasoft.spring.service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.diasoft.spring.dao.AuthorDao;
import ru.diasoft.spring.dao.GenreDao;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Genre;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@DisplayName("Сервис для работы с жанрами")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = GenreServiceImpl.class)
public class GenreServiceImplTest {
    @MockBean
    GenreDao dao;

    @Autowired
    GenreService service;

    @DisplayName("сохраняет жанр в БД")
    @Test
    public void shouldSaveGenre() {
        Genre genre1 = new Genre(1,"genre1");
        service.insert(genre1);
        verify(dao).insert(genre1);
    }

    @DisplayName("возвращает жанр по id")
    @Test
    public void shouldReturnGenreById(){
        Genre expectedGenre = new Genre(1,"genre1");
        Mockito.when(dao.getById(1L)).thenReturn(expectedGenre);

        Genre actualGenre = service.getById(1L);

        assertEquals(expectedGenre, actualGenre);

    }

    @DisplayName("возвращает жанр по названию")
    @Test
    public void shouldReturnGenreByName(){
        Genre expectedGenre = new Genre(1,"genre1");
        Mockito.when(dao.getByName("genre1")).thenReturn(expectedGenre);
        Genre actualGenre = service.getByName("genre1");
        assertEquals(expectedGenre, actualGenre);
    }

}
