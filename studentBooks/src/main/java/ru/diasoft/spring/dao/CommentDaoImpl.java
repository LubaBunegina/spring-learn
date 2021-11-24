package ru.diasoft.spring.dao;

import org.springframework.stereotype.Repository;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Comment;
import ru.diasoft.spring.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentDaoImpl implements CommentDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
        } else {
            em.merge(comment);
        }
    }

    @Override
    public List<Comment> getCommentByBook(Book book) {
        TypedQuery<Comment> query = em.createQuery("select s from Comment s  where s.book = :book", Comment.class);
        query.setParameter("book", book);
        return  query.getResultList();
    }
}
