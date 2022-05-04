package ru.diasoft.spring.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.diasoft.spring.SpringSecurityConfig;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Genre;
import ru.diasoft.spring.repository.UserRepository;
import ru.diasoft.spring.rest.BookController;
import ru.diasoft.spring.rest.BookDto;
import ru.diasoft.spring.security.CustomUserDetailsService;
import ru.diasoft.spring.service.BookService;
import ru.diasoft.spring.service.MapStructMapperImpl;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@DisplayName("Контроллер для работы с книгами должен")
@WebMvcTest(BookController.class)
@WithMockUser(username="admin",password = "123")
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private MapStructMapperImpl mapStructMapper;


    @MockBean
    private BookService service;

    @MockBean
    private CustomUserDetailsService userDetailsService;

    @MockBean
    private DataSource dataSource;


    @DisplayName("возвращать верный список книг")
    @Test
    void shouldReturnCorrectPersonsList() throws Exception {
        List<BookDto> books = new ArrayList<>();
        Book b1 = createBookForTest("b1", "a1", "g1");
        Book b2 = createBookForTest("b2", "a2", "g2");
        books.add(mapStructMapper.bookToBookDto(b1));
        books.add(mapStructMapper.bookToBookDto(b2));

        given(service.getAllDto()).willReturn(books);

        mvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(books)));
    }

    @DisplayName("создавать новую книгу")
    @Test
    void shouldCorrectSaveNewBook() throws Exception {
        Book book = createBookForTest("b1", "a1", "g1");;
        given(service.insert(any())).willReturn(book);
        BookDto bookDto = createBookDtoForTest();
        String expectedResult = mapper.writeValueAsString(bookDto);
        given(mapStructMapper.bookToBookDto(book)).willReturn(bookDto);
        mvc.perform(post("/api/books").with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_messages")))
                .contentType(APPLICATION_JSON)
                .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @DisplayName("получать книгу по идентификатору")
    @Test
    void shouldReturnBookById() throws Exception {
        Book book = createBookForTest("b1", "a1", "g1");
        BookDto expectedResult = createBookDtoForTest();

        given(service.getById(1L)).willReturn(book);
        given(mapStructMapper.bookToBookDto(book)).willReturn(expectedResult);

        mvc.perform(get("/api/books/1").with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_messages"))))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @DisplayName("удалять книгу по идентификатору")
    @Test
    void shouldCorrectDeleteBook() throws Exception {
        mvc.perform(delete("/api/books/1").with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_messages"))))
                .andExpect(status().isOk());
        verify(service, times(1)).delete(1L);
    }

    @DisplayName("обновлять существующую книгу")
    @Test
    void shouldCorrectUpdateBook() throws Exception {
        Book book = createBookForTest("b1", "a1", "g1");
        BookDto bookDto = createBookDtoForTest();
        given(mapStructMapper.bookToBookDto(book)).willReturn(bookDto);
        given(service.getById(1L)).willReturn(book);
        mvc.perform(
                put("/api/books/{id}", book.getId()).with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_messages")))
                        .content(mapper.writeValueAsString(mapStructMapper.bookToBookDto(book)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("0"))
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

    private BookDto createBookDtoForTest() {
        BookDto expectedResult = new BookDto();
        expectedResult.setName("b1");
        expectedResult.setAuthorName("a1");
        expectedResult.setGenreName("g1");

        return expectedResult;
    }
}
