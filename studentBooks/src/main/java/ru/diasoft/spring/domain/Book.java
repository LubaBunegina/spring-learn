package ru.diasoft.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Data
public class Book {
    private final long id;
    private final String name;
    private final Genre genre;
    private final Author author;

    @Override
    public String toString(){
        return id + ". " + name;
    }
}
