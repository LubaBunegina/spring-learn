package ru.diasoft.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.spring.dao.BookDao;
import ru.diasoft.spring.dao.CommentDao;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentDao commentDao;
    private final BookDao bookDao;

    @Transactional
    @Override
    public void insert(String author, String bookName, String commentText) {
        Optional<Book> optionalBook = bookDao.getByName(bookName);
        if(optionalBook.isPresent()){
            Comment comment = new Comment();
            comment.setBook(optionalBook.get());
            comment.setText(commentText);
            comment.setAuthor(author);
            commentDao.insert(comment);
        }
        return;
    }

    @Override
    public List<Comment> getCommentByBook(String bookName) {
        Optional<Book> optionalBook = bookDao.getByName(bookName);
        if(optionalBook.isPresent()) {
            return commentDao.getCommentByBook(optionalBook.get());
        } else {
            return new ArrayList<>();
        }
    }
}
