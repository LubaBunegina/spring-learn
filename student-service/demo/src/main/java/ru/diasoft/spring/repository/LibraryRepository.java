package ru.diasoft.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.diasoft.spring.domain.Library;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    List<Library> findAllByStudentId(long studentId);
    List<Library> findAllByBookId(long bookId);
    List<Library> findAllByBookIdAndAndStudentId(long bookId, long studentId);
}