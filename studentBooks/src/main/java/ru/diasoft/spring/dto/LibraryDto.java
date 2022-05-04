package ru.diasoft.spring.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Data
public class LibraryDto {
    private long id;
    private long bookId;
    private long studentId;
}
