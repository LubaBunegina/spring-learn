package ru.diasoft.spring.service;

import ru.diasoft.spring.domain.Library;
import ru.diasoft.spring.domain.Student;
import ru.diasoft.spring.dto.LibraryDto;
import ru.diasoft.spring.dto.StudentDto;

import java.util.List;

public interface MapperService {
    LibraryDto toLibraryDto(Library lib);
    List<LibraryDto> toLibraryDtoList(List<Library> libraryList);
    StudentDto toStudentDto(Student s);
}
