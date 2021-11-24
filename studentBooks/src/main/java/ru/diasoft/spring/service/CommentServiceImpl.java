package ru.diasoft.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.spring.dao.BookDao;
import ru.diasoft.spring.dao.CommentDao;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Comment;
import ru.diasoft.spring.repository.BookRepository;
import ru.diasoft.spring.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentDao;
    private final BookRepository bookDao;

    @Transactional
    @Override
    public void insert(String author, String bookName, String commentText) {
        Optional<Book> optionalBook = bookDao.findBookByName(bookName);
        if(optionalBook.isPresent()){
            Comment comment = new Comment();
            comment.setBook(optionalBook.get());
            comment.setText(commentText);
            comment.setAuthor(author);
            commentDao.save(comment);
        }
        return;
    }

    @Override
    public List<Comment> getCommentByBook(String bookName) {
        Optional<Book> optionalBook = bookDao.findBookByName(bookName);
        if(optionalBook.isPresent()) {
            return commentDao.findCommentsByBook(optionalBook.get());
        } else {
            return new ArrayList<>();
        }
    }
}
