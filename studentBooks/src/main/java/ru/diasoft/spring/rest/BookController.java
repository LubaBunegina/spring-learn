package ru.diasoft.spring.rest;

import org.springframework.web.bind.annotation.*;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.dto.BookDto;
import ru.diasoft.spring.dto.LibraryDto;
import ru.diasoft.spring.dto.StudentDto;
import ru.diasoft.spring.exception.NotFoundException;
import ru.diasoft.spring.service.BookService;
import ru.diasoft.spring.service.MapStructMapper;

import java.util.List;

@RestController
public class BookController {

    private final BookService service;
    private final MapStructMapper mapper;

    public BookController(BookService service, MapStructMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @RequestMapping(value = "/api/books", method = RequestMethod.GET)
    public List<BookDto> getAllBooks() {
        return service.getAllDto();
    }

    //книги, которые читает студент
    @RequestMapping(value = "/api/books/student", method = RequestMethod.GET)
    public List<BookDto> getAllBooksByStudent(@RequestParam String studentName) {
        return service.getAllByStudent(studentName);
    }

    //кто, читает эту книгу
    @RequestMapping(value = "/api/students", method = RequestMethod.GET)
    public StudentDto getStudentByBook(@RequestParam String bookName) {
        return service.getStudentByBookName(bookName);
    }

    //студент взял книгу из бибилиотеки
    @RequestMapping(value = "/api/addLibraryLink", method = RequestMethod.GET)
    public LibraryDto addLibraryLink(@RequestBody LibraryDto dto) {
        return service.addLibraryLink(dto);
    }

    //студент сдал книгу в библиотеку
    @RequestMapping(value = "/api/deleteLibraryLink", method = RequestMethod.GET)
    public void deleteLibraryLink(@RequestBody LibraryDto dto) {
        service.deleteLibraryLink(dto);
    }

    @GetMapping("/api/books/{id}")
    public BookDto getBookById(@PathVariable("id") long id) throws NotFoundException {
        Book book = service.getById(id);
        return mapper.bookToBookDto(book);
    }

    @PostMapping("/api/books")
    public BookDto createNewBook(@RequestBody BookDto dto) throws Exception {
        Book book = service.insert(dto);
        return mapper.bookToBookDto(book);
    }

    @PutMapping("/api/books/{id}")
    public BookDto update(@PathVariable String id, @RequestBody BookDto dto) throws Exception {
        service.update(Long.valueOf(id), dto.getName(), dto.getAuthorName(), dto.getGenreName());
        return mapper.bookToBookDto(service.getById(Long.valueOf(id)));
    }

    @DeleteMapping("/api/books/{id}")
    public void deleteById(@PathVariable("id") long id) throws Exception {
        service.delete(id);
    }

}
