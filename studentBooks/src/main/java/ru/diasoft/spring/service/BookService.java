package ru.diasoft.spring.service;

import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.dto.BookDto;
import ru.diasoft.spring.dto.LibraryDto;
import ru.diasoft.spring.dto.StudentDto;
import ru.diasoft.spring.exception.NotFoundException;

import java.util.List;

public interface BookService {
    Book insert(BookDto dto) throws Exception;
    void update(Long bookId, String bookName, String authorName, String genreName) throws Exception;
    void delete(Long id) throws Exception;
    Book getById(Long id) throws NotFoundException;
    List<Book> getBookByAuthor(String authorName) throws Exception;
    List<Book> getBookByGenre(String genreName) throws Exception;
    List<BookDto> getAllByIds(List<Long> ids);
    List<Book> getAll();
    List<BookDto> getAllDto();
    List<BookDto> getAllByStudent(String studentName);
    StudentDto getStudentByBookName(String bookName);
    LibraryDto addLibraryLink(LibraryDto dto);
    void deleteLibraryLink(LibraryDto dto);
}
