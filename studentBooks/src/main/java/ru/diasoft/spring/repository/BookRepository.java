package ru.diasoft.spring.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = {"author", "genre"})
    @Override
    List<Book> findAll();

    Optional<Book> findBookByName(String n);

    List<Book> findByAuthor(Author a);
    List<Book> findByGenre(Genre g);

    @Modifying
    @Transactional
    @Query("delete from Book e  where e.id = :id")
    void deleteBookById(@Param("id") long id);

}
