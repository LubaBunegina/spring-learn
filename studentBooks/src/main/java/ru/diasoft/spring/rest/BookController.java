package ru.diasoft.spring.rest;

import org.springframework.web.bind.annotation.*;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.service.BookService;
import ru.diasoft.spring.service.MapStructMapper;
import ru.diasoft.spring.service.MapStructMapperImpl;

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

    @GetMapping("/api/books/{id}")
    public BookDto getBookById(@PathVariable("id") long id) {
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
