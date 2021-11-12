package ru.diasoft.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Comment;
import ru.diasoft.spring.domain.Genre;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с комментариями должно")
@DataJpaTest
@Import(CommentDaoImpl.class)
public class CommentDaoImplTest {

    private static final String EXISTING_BOOK_NAME = "bookTest";
    private static final String EXISTING_GENRE_NAME = "genreTest";
    private static final String EXISTING_AUTHOR_NAME = "authorTest";

    @Autowired
    private CommentDaoImpl commentDao;

    @Autowired
    private TestEntityManager em;

    @Rollback(value = true)
    @DisplayName("добавлять комментарий в БД")
    @Test
    void shouldInsertComment() {
        Comment expectedComment =  createComment("nik3", "commentText3");
        commentDao.insert(expectedComment);

        Comment actualComment = em.find(Comment.class, expectedComment.getId());
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("возвращать все комментарии по книге из БД")
    @Test
    void shouldReturnCommentsByBook() {
        Book book = createBook();

        Comment comment = createComment("nik3", "commentText3");
        comment.setBook(book);
        em.persist(comment);

        List<Comment> commentList = commentDao.getCommentByBook(book);
        assertThat(commentList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(comment);

    }

    private Comment createComment(String nik, String text){
        Comment comment = new Comment();
        comment.setAuthor(nik);
        comment.setText(text);

        return comment;
    }

    private Book createBook(){
        Book expectedBook = new Book();

        Genre genre = new Genre();
        genre.setName(EXISTING_GENRE_NAME);

        Author author = new Author();
        author.setName(EXISTING_AUTHOR_NAME);

        expectedBook.setName(EXISTING_BOOK_NAME);
        expectedBook.setAuthor(author);
        expectedBook.setGenre(genre);

        return expectedBook;
    }
}
