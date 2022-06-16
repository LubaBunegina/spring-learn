package ru.diasoft.spring.service;

import ru.diasoft.spring.dto.LibraryDto;

import java.util.List;

public interface LibraryService {
    List<LibraryDto> getLibraryByBookId(long bookId);
    List<LibraryDto> getLibraryByStudentId(long studentId);
    List<LibraryDto> getLibraryByStudentIdAndBookId(long studentId, long bookId);
    List<LibraryDto> getAllLibrary();
    LibraryDto create(LibraryDto dto);
    void deleteById(long id);
}