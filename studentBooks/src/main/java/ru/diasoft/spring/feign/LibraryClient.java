package ru.diasoft.spring.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.diasoft.spring.config.FeignConfig;
import ru.diasoft.spring.dto.LibraryDto;

import java.util.List;

@FeignClient( value = "libraries",
        url  = "${library.service.url}",
        configuration = FeignConfig.class
)
public interface LibraryClient {

    @RequestMapping(value = "/api/library/books/{bookId}", method = RequestMethod.GET)
    List<LibraryDto> getLibraryByBookId(@PathVariable long bookId);

    @RequestMapping(value = "/api/library/students/{studentId}", method = RequestMethod.GET)
    List<LibraryDto> getLibraryByStudentId(@PathVariable long studentId);

    @RequestMapping(value = "/api/library/students/{studentId}/books/{bookId}", method = RequestMethod.GET)
    List<LibraryDto> getLibraryByStudentIdAndBookId(@PathVariable long studentId, @PathVariable long bookId);


    @RequestMapping(value = "/api/library", method = RequestMethod.POST)
    LibraryDto create(@RequestBody LibraryDto dto);

    @RequestMapping(value = "/api/library/{id}", method = RequestMethod.DELETE)
    void deleteById(@PathVariable long id);

}
