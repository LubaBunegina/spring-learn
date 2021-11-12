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
import ru.diasoft.spring.dao.BookDao;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Genre;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@DisplayName("Сервис для работы с авторами")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AuthorServiceImpl.class)
public class AuthorServiceImplTest {

    @MockBean
    AuthorDao dao;

    @Autowired
    AuthorService service;

    @DisplayName("сохраняет автора в БД")
    @Test
    public void shouldSaveAuthor() {
        Author author1 = new Author();
        author1.setName("author1");
        service.insert(author1);
        verify(dao).insert(author1);
    }

    @DisplayName("возвращает автора по id")
    @Test
    public void shouldReturnAuthorById(){
        Author expectedAuthor = new Author();
        expectedAuthor.setName("author1");
        Mockito.when(dao.getById(1L)).thenReturn(Optional.of(expectedAuthor));

        Author actualAuthor = service.getById(1L);

        assertEquals(expectedAuthor, actualAuthor);

    }

    @DisplayName("возвращает автора по имени")
    @Test
    public void shouldReturnAuthorByName(){
        Author expectedAuthor = new Author();
        expectedAuthor.setName("author1");
        Mockito.when(dao.getByName("author1")).thenReturn(Optional.of(expectedAuthor));
        Author actualAuthor = service.getByName("author1");
        assertEquals(expectedAuthor, actualAuthor);
    }

}
