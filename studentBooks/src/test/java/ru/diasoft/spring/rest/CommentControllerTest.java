package ru.diasoft.spring.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Comment;
import ru.diasoft.spring.domain.Genre;
import ru.diasoft.spring.dto.CommentDto;
import ru.diasoft.spring.security.CustomUserDetailsService;
import ru.diasoft.spring.service.CommentService;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Контроллер для работы с комментариями книги должен")
@WebMvcTest(CommentController.class)
@WithMockUser(username="admin",password = "123")
public class CommentControllerTest {

    private static final String BOOK_NAME = "book1";
    private static final String BOOK_AUTHOR = "author1";
    private static final String BOOK_GENRE = "genre1";
    private static final String COMMENT_TEXT = "t1";
    private static final String COMMENT_NIK = "n1";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CommentService service;

    @MockBean
    private CustomUserDetailsService userDetailsService;

    @MockBean
    private DataSource dataSource;

    @DisplayName("создавать новый комментарий для книги")
    @Test
    void shouldCorrectSaveNewComment() throws Exception {
        Comment comment = createComment(COMMENT_NIK, COMMENT_TEXT );
        given(service.insert(COMMENT_NIK, 1L, COMMENT_TEXT)).willReturn(comment);
        String expectedResult = mapper.writeValueAsString(CommentDto.toDto(comment));
        mvc.perform(post("/api/comments").with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_messages")))
                .contentType(APPLICATION_JSON)
                .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @DisplayName("возвращать верный список комментариев по книге")
    @Test
    void shouldReturnCorrectCommentList() throws Exception {
        List<Comment> comments = new ArrayList<>();
        Comment c1 = createComment(COMMENT_NIK, COMMENT_TEXT );
        Comment c2 = createComment(COMMENT_NIK, COMMENT_TEXT );
        comments.add(c1);
        comments.add(c2);

        given(service.getCommentByBook(BOOK_NAME)).willReturn(comments);

        List<CommentDto> expectedResult = comments.stream()
                .map(CommentDto::toDto).collect(Collectors.toList());

        mvc.perform(get("/api/comments")
                .with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_messages")))
                .param("bookName", BOOK_NAME))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    private Comment createComment(String nik, String text){
        Book book = createBookForTest(BOOK_NAME, BOOK_AUTHOR, BOOK_GENRE);
        Comment comment = new Comment();
        comment.setAuthor(nik);
        comment.setText(text);
        comment.setBook(book);

        return comment;
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
