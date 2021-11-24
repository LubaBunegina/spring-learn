package ru.diasoft.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Genre;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void insert(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
        } else {
            em.merge(book);
        }
    }

    @Transactional
    @Override
    public void update(Book book) {
        em.merge(book);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Query query = em.createQuery("delete " +
                "from Book s " +
                "where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();

    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> getById(Long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> getByName(String bookName) {
        try {
            EntityGraph<?> entityGraph = em.getEntityGraph("books-entity-graph");
            TypedQuery<Book> query = em.createQuery("select s from Book s  where s.name = :name", Book.class);
            query.setParameter("name", bookName);
            query.setHint("javax.persistence.fetchgraph", entityGraph);
            return Optional.of(query.getSingleResult());
        } catch(NoResultException e){
            return Optional.empty();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getBookByAuthor(Author author) {
        try {
            EntityGraph<?> entityGraph = em.getEntityGraph("books-entity-graph");
            TypedQuery<Book> query = em.createQuery("select s from Book s  where s.author = :author", Book.class);
            query.setParameter("author", author);
            query.setHint("javax.persistence.fetchgraph", entityGraph);
            return query.getResultList();
        } catch (NoResultException e){
            return new ArrayList<>();
        }
    }

    @Override
    public List<Book> getBookByGenre(Genre genre) {
        try {
            EntityGraph<?> entityGraph = em.getEntityGraph("books-entity-graph");
            TypedQuery<Book> query = em.createQuery("select s from Book s  where s.genre = :genre", Book.class);
            query.setParameter("genre", genre);
            query.setHint("javax.persistence.fetchgraph", entityGraph);
            return query.getResultList();
        }  catch (NoResultException e){
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("books-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }


}
