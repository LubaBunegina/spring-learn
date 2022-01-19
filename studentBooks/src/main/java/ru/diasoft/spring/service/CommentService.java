package ru.diasoft.spring.service;

import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    void insert(String author, String bookName, String commentText);
    Comment insert(String author, Long bookId, String commentText);
    List<Comment> getCommentByBook(String bookName);
}
