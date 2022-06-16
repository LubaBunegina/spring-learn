package ru.diasoft.spring.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Genre;

@RequiredArgsConstructor
@Data
public class BookDto {

    private long id;

    private String name;

    private String genreName;

    private String authorName;

    public BookDto(long id, String name, String genreName, String authorName) {
        this.id = id;
        this.name = name;
        this.genreName = genreName;
        this.authorName = authorName;
    }

}
