package ru.diasoft.spring.rest;

import org.springframework.web.bind.annotation.*;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.repository.BookRepository;
import ru.diasoft.spring.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @RequestMapping(value = "/api/books", method = RequestMethod.GET)
    public List<BookDto> getAllBooks() {
        return service.getAll().stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/books/{id}")
    public BookDto getBookById(@PathVariable("id") long id) {
        Book book = service.getById(id);
        return BookDto.toDto(book);
    }

    @PostMapping("/api/books")
    public BookDto createNewBook(@RequestBody BookDto dto) throws Exception {
        Book book = service.insert(dto.getName(), dto.getAuthorName(), dto.getGenreName());
        return BookDto.toDto(book);
    }

    @PutMapping("/api/books/{id}")
    public BookDto update(@PathVariable String id, @RequestBody BookDto dto) throws Exception {
        service.update(Long.valueOf(id), dto.getName(), dto.getAuthorName(), dto.getGenreName());
        return BookDto.toDto(service.getById(Long.valueOf(id)));
    }

    @DeleteMapping("/api/books/{id}")
    public void deleteById(@PathVariable("id") long id) throws Exception {
        service.delete(id);
    }

}
