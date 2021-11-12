package ru.diasoft.spring.dao;

import org.springframework.stereotype.Repository;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {
    void insert(Comment comment);
    List<Comment> getCommentByBook(Book book);
}
