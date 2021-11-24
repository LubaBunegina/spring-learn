package ru.diasoft.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.diasoft.spring.dao.BookDao;
import ru.diasoft.spring.dao.CommentDao;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Comment;
import ru.diasoft.spring.domain.Genre;
import ru.diasoft.spring.repository.BookRepository;
import ru.diasoft.spring.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@DisplayName("Сервис для работы с комментариями книг")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CommentServiceImpl.class)
public class CommentServiceImplTest {

    private static final String BOOK_NAME = "book1";
    private static final String BOOK_AUTHOR = "author1";
    private static final String BOOK_GENRE = "genre1";
    private static final String COMMENT_TEXT = "text1";
    private static final String COMMENT_NIK = "nik1";

    @MockBean
    CommentRepository commentDao;

    @MockBean
    BookRepository bookDao;

    @Autowired
    CommentService service;

    @DisplayName("сохраняет комментарий")
    @Test
    public void shouldSaveComment() throws Exception {
        Comment comment = createComment(COMMENT_NIK, COMMENT_TEXT);
        Mockito.when(bookDao.findBookByName(BOOK_NAME)).thenReturn(
                Optional.of(createBookForTest(BOOK_NAME, BOOK_AUTHOR, BOOK_GENRE)));
        service.insert(COMMENT_NIK, BOOK_NAME, COMMENT_TEXT);
        verify(commentDao).save(comment);
    }

    @DisplayName("возвращает все комментарии для книги")
    @Test
    public void shouldReturnCommentsByBook() {
        Book expectedBook = createBookForTest(BOOK_NAME, BOOK_AUTHOR, BOOK_GENRE);
        Comment expectedComment = createComment(COMMENT_NIK, COMMENT_TEXT);
        List<Comment> expectedCommentList = new ArrayList<>();
        expectedCommentList.add(expectedComment);
        Mockito.when(bookDao.findBookByName(BOOK_NAME)).thenReturn(
                Optional.of(expectedBook));

        Mockito.when(commentDao.findCommentsByBook(expectedBook)).thenReturn(expectedCommentList);

        List<Comment> actualCommentList = service.getCommentByBook(BOOK_NAME);

        assertEquals(expectedCommentList, actualCommentList);
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
        book.setName(bookName);
        book.setGenre(genre);
        book.setAuthor(author);

        return book;
    }
}
