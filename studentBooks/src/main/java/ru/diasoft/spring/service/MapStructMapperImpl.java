package ru.diasoft.spring.service;

import org.springframework.stereotype.Service;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.rest.BookDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapStructMapperImpl implements MapStructMapper {
    @Override
    public BookDto bookToBookDto(Book book) {
        return new BookDto(book.getId(), book.getName(), book.getGenre().getName(), book.getAuthor().getName());
    }

    @Override
    public List<BookDto> booksToBookDtoList(List<Book> books) {
        List<BookDto> bookDtos = new ArrayList<>();
        for(Book book : books) {
            bookDtos.add(bookToBookDto(book));
        }
        return bookDtos;
    }
}
