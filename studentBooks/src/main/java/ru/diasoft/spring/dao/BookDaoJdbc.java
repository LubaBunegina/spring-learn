package ru.diasoft.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(Book book) {
        Map<String, Object> argMap = new HashMap<>();
        argMap.put("id", book.getId());
        argMap.put("name", book.getName());
        argMap.put("genre", book.getGenre().getId());
        argMap.put("author", book.getAuthor().getId());
        namedParameterJdbcOperations.update("insert into books (id, `name`, author, genre) values (:id, :name, :author, :genre)", argMap);
    }

    @Override
    public void update(Book book) {
        Map<String, Object> argMap = new HashMap<>();
        argMap.put("id", book.getId());
        argMap.put("name", book.getName());
        argMap.put("genre", book.getGenre().getId());
        argMap.put("author", book.getAuthor().getId());
        namedParameterJdbcOperations.update("update books set `author` = :author, `genre` = :genre, `name` = :name where id = :id", argMap);

    }

    @Override
    public void delete(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from books where id = :id", params
        );

    }

    @Override
    public Book getById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                    "select  books.*," +
                        " authors.name authorName, genres.name genreName from books " +
                        "inner join authors on authors.id = books.author " +
                        "inner join genres on genres.id = books.genre where books.id = :id",
                params, new BookMapper());
    }

    @Override
    public List<Book> getByAuthorId(Long authorId) {
        Map<String, Object> params = Collections.singletonMap("authorId", authorId);
        return namedParameterJdbcOperations.query("select  books.*," +
                        " authors.name authorName, genres.name genreName from books " +
                        "inner join authors on authors.id = books.author " +
                        "inner join genres on genres.id = books.genre where books.author = :authorId",
                params, new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("select  books.*," +
                " authors.name authorName, genres.name genreName from books " +
                "inner join authors on authors.id = books.author " +
                "inner join genres on genres.id = books.genre", new BookMapper());
    }

    @Override
    public Long getMaxId() {
        return namedParameterJdbcOperations.queryForObject("select MAX(id) from books", new HashMap<>(),
                Long.class);
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            String name = resultSet.getString("name");
            String authorName = resultSet.getString("authorName");
            String genreName = resultSet.getString("genreName");

            long author = resultSet.getLong("author");
            long genre = resultSet.getLong("genre");
            long id = resultSet.getLong("id");

            return new Book(id, name, new Genre(genre, genreName), new Author(author, authorName));
        }
    }
}
