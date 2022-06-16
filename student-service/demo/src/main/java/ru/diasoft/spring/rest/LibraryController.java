package ru.diasoft.spring.rest;

import org.springframework.web.bind.annotation.*;
import ru.diasoft.spring.dto.LibraryDto;
import ru.diasoft.spring.service.LibraryService;

import java.util.List;

@RestController
public class LibraryController {

    private final LibraryService service;

    public LibraryController(LibraryService service) {
        this.service = service;
    }


    @RequestMapping(value = "/api/library/books/{bookId}", method = RequestMethod.GET)
    List<LibraryDto> getLibraryByBookId(@PathVariable long bookId) {
        return service.getLibraryByBookId(bookId);
    }

    @RequestMapping(value = "/api/library/students/{studentId}", method = RequestMethod.GET)
    List<LibraryDto> getLibraryByStudentId(@PathVariable long studentId) {
        return service.getLibraryByStudentId(studentId);
    }

    @RequestMapping(value = "/api/library/students/{studentId}/books/{bookId}", method = RequestMethod.GET)
    List<LibraryDto> getLibraryByStudentIdAndBookId(@PathVariable long studentId, @PathVariable long bookId) {
        return service.getLibraryByStudentIdAndBookId(studentId, bookId);
    }

    @RequestMapping(value = "/api/library/all", method = RequestMethod.GET)
    List<LibraryDto> getAll() {
        return service.getAllLibrary();
    }

    @RequestMapping(value = "/api/library", method = RequestMethod.POST)
    LibraryDto create(@RequestBody LibraryDto dto) {
        return service.create(dto);
    }

    @DeleteMapping("/api/library/{id}")
    public void deleteById(@PathVariable("id") long id) {
        service.deleteById(id);
    }

}