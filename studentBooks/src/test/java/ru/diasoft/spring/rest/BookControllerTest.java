package ru.diasoft.spring.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Genre;
import ru.diasoft.spring.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@DisplayName("Контроллер для работы с книгами должен")
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BookService service;

    @DisplayName("возвращать верный список книг")
    @Test
    void shouldReturnCorrectPersonsList() throws Exception {
        List<Book> books = new ArrayList<>();
        Book b1 = createBookForTest("b1", "a1", "g1");
        Book b2 = createBookForTest("b2", "a2", "g2");
        books.add(b1);
        books.add(b2);

        given(service.getAll()).willReturn(books);

        List<BookDto> expectedResult = books.stream()
                .map(BookDto::toDto).collect(Collectors.toList());

        mvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @DisplayName("создавать новую книгу")
    @Test
    void shouldCorrectSaveNewBook() throws Exception {
        Book book = createBookForTest("b1", "a1", "g1");;
        given(service.insert(any(), any(), any())).willReturn(book);
        String expectedResult = mapper.writeValueAsString(BookDto.toDto(book));

        mvc.perform(post("/api/books").contentType(APPLICATION_JSON)
                .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @DisplayName("получать книгу по идентификатору")
    @Test
    void shouldReturnBookById() throws Exception {
        Book book = createBookForTest("b1", "a1", "g1");
        given(service.getById(1L)).willReturn(book);
        BookDto expectedResult = BookDto.toDto(book);

        mvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @DisplayName("удалять книгу по идентификатору")
    @Test
    void shouldCorrectDeleteBook() throws Exception {
        mvc.perform(delete("/api/books/1"))
                .andExpect(status().isOk());
        verify(service, times(1)).delete(1L);
    }

    @DisplayName("обновлять существующую книгу")
    @Test
    void shouldCorrectUpdateBook() throws Exception {
        Book book = createBookForTest("b1", "a1", "g1");
        given(service.getById(1L)).willReturn(book);
        mvc.perform(
                put("/api/books/{id}", book.getId())
                        .content(mapper.writeValueAsString(BookDto.toDto(book)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("b1"))
                .andExpect(jsonPath("$.genreName").value("g1"))
                .andExpect(jsonPath("$.authorName").value("a1"));
    }



    private Book createBookForTest(String bookName, String authorName, String genreName) {
        Genre genre = new Genre();
        genre.setName(genreName);

        Author author = new Author();
        author.setName(authorName);

        Book book = new Book();
        book.setId(1L);
        book.setName(bookName);
        book.setGenre(genre);
        book.setAuthor(author);

        return book;
    }
}
