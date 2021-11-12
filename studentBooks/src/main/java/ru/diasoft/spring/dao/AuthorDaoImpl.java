package ru.diasoft.spring.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.spring.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void insert(Author author) {
        if (author.getId() <= 0) {
            em.persist(author);
        } else {
            em.merge(author);
        }
    }

    @Transactional
    @Override
    public Optional<Author> getById(Long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Author> getByName(String name) {
        try {
            TypedQuery<Author> query = em.createQuery("select s from Author s  where s.name like :name", Author.class);
            query.setParameter("name", "%" + name + "%");
            return Optional.of(query.getSingleResult());
        }  catch (NoResultException e){
            return Optional.empty();
        }
    }
}
