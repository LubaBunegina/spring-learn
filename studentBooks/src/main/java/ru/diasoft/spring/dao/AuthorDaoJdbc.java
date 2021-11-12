package ru.diasoft.spring.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(Author author) {
        Map<String, Object> argMap = new HashMap<>();
        argMap.put("id", author.getId());
        argMap.put("name", author.getName());
        namedParameterJdbcOperations.update("insert into authors (id, `name`) values (:id, :name)", argMap);
    }


    @Override
    public Author getById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject("select * from authors where id = :id", params,
                new AuthorMapper());
    }

    @Override
    public Author getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        try {
            return namedParameterJdbcOperations.queryForObject("select * from authors where name = :name", params,
                    new AuthorMapper());
        } catch(EmptyResultDataAccessException dataAccessException){
            return null;
        }
    }


    @Override
    public Long getMaxId() {
        return namedParameterJdbcOperations.queryForObject("select MAX(id) from authors", new HashMap<>(),
                Long.class);
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }

}
