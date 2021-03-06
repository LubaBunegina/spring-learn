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
import ru.diasoft.spring.dao.AuthorDao;
import ru.diasoft.spring.dao.BookDao;
import ru.diasoft.spring.dao.GenreDao;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Genre;
import ru.diasoft.spring.repository.AuthorRepository;
import ru.diasoft.spring.repository.BookRepository;
import ru.diasoft.spring.repository.GenreRepository;
import ru.diasoft.spring.rest.BookDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@DisplayName("Сервис для работы с книгами")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BookServiceImpl.class)
public class BookServiceImplTest {

    @MockBean
    BookRepository bookDao;

    @MockBean
    AuthorRepository authorDao;

    @MockBean
    GenreRepository genreDao;

    @Autowired
    BookService service;

    @MockBean
    MapStructMapperImpl mapStructMapper;

    @DisplayName("возвращает нужную книгу по id")
    @Test
    public void shouldReturnBookById(){
        Genre genre = new Genre();
        genre.setName("genre1");

        Author author = new Author();
        author.setName("author1");

        Book expectedBook = new Book();
        expectedBook.setName("book1");
        expectedBook.setGenre(genre);
        expectedBook.setAuthor(author);

        Mockito.when(bookDao.findById(1L)).thenReturn(Optional.of(expectedBook));

        Book actualBook = service.getById(1L);

        assertEquals(expectedBook, actualBook);

    }

    @DisplayName("возвращает нужную книгу по id автора")
    @Test
    public void shouldReturnBookByAuthorId() throws Exception {
        Book book = createBookForTest("book1", "author1", "genre1");

        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(book);

        Author author = new Author();
        author.setName("author1");

        Mockito.when(bookDao.findByAuthor(author)).thenReturn(expectedBooks);
        Mockito.when(authorDao.findByName("author1")).thenReturn(Optional.of(author));

        List<Book> actualBooks = service.getBookByAuthor(author.getName());

        assertEquals(expectedBooks, actualBooks);

    }

    @DisplayName("возвращает список всех книг")
    @Test
    public void shouldReturnAllBooks(){
        Book book1 = createBookForTest("book1", "author1", "genre1");
        Book book2 = createBookForTest("book2", "author2", "genre2");

        List expectedBookList = new ArrayList();
        expectedBookList.add(book1);
        expectedBookList.add(book2);

        Mockito.when(bookDao.findAll()).thenReturn(expectedBookList);

        List<Book> actualBookList = service.getAll();

        assertEquals(expectedBookList, actualBookList);

    }

    @DisplayName("сохраняет книгу")
    @Test
    public void shouldSaveBook() throws Exception {
        Book book1 = createBookForTest("book1", "author1", "genre1");
        BookDto dto = new BookDto();
        dto.setGenreName("genre1");
        dto.setAuthorName("author1");
        dto.setName("book1");
        service.insert(dto);
        verify(bookDao).saveAndFlush(book1);
    }

    @DisplayName("удаляет книгу")
    @Test
    public void shouldDeleteBookById() throws Exception {

        Book book1 = createBookForTest("book1", "author1", "genre1");

        Mockito.when(bookDao.getById(1L)).thenReturn(book1);

        service.delete(1L);
        verify(bookDao).deleteBookById(1L);
    }

    private Book createBookForTest(String bookName, String authorName, String genreName) {
        Genre genre = new Genre();
        genre.setName(genreName);

        Author author = new Author();
        author.setName(authorName);

        Book book = new Book();
        book.setName(bookName);
        book.setGenre(genre);
        book.setAuthor(author);

        return book;
    }

}
