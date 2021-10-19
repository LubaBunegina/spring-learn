package ru.diasoft.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.spring.dao.BookDao;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@DisplayName("Сервис для работы с книгами")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BookServiceImpl.class)
public class BookServiceImplTest {

    @MockBean
    BookDao dao;

    @Autowired
    BookService service;

    @DisplayName("возвращает нужную книгу по id")
    @Test
    public void shouldReturnBookById(){
        Book expectedBook = new Book(1,"book1", new Genre(1, "genre1"),
                new Author(1, "author1"));
        Mockito.when(dao.getById(1L)).thenReturn(expectedBook);

        Book actualBook = service.getById(1L);

        assertEquals(expectedBook, actualBook);

    }

    @DisplayName("возвращает нужную книгу по id автора")
    @Test
    public void shouldReturnBookByAuthorId(){
        Book book = new Book(1,"book1", new Genre(1, "genre1"),
                new Author(1, "author1"));
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(book);

        Mockito.when(dao.getByAuthorId(1L)).thenReturn(expectedBooks);

        List<Book> actualBooks = service.getByAuthorId(1L);

        assertEquals(expectedBooks, actualBooks);

    }

    @DisplayName("возвращает список всех книг")
    @Test
    public void shouldReturnAllBooks(){
        Book book1 = new Book(1,"book1", new Genre(1, "genre1"),
                new Author(1, "author1"));
        Book book2 = new Book(2,"book1", new Genre(1, "genre1"),
                new Author(1, "author1"));

        List expectedBookList = new ArrayList();
        expectedBookList.add(book1);
        expectedBookList.add(book2);

        Mockito.when(dao.getAll()).thenReturn(expectedBookList);

        List<Book> actualBookList = service.getAll();

        assertEquals(expectedBookList, actualBookList);

    }

    @DisplayName("сохраняет книгу")
    @Test
    public void shouldSaveBook() {
        Book book1 = new Book(1,"book1", new Genre(1, "genre1"),
                new Author(1, "author1"));
        service.insert(book1);
        verify(dao).insert(book1);
    }

    @DisplayName("удаляет книгу")
    @Test
    public void shouldDeleteBookById() {
        service.delete(1L);
        verify(dao).delete(1L);
    }

}
