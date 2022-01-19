package ru.diasoft.spring.service;

import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.rest.BookDto;

import java.util.List;

public interface MapStructMapper {
    BookDto bookToBookDto(Book book);
    List<BookDto> booksToBookDtoList(List<Book> books);
}
