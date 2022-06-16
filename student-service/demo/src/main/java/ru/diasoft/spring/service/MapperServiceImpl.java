package ru.diasoft.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diasoft.spring.domain.Library;
import ru.diasoft.spring.domain.Student;
import ru.diasoft.spring.dto.LibraryDto;
import ru.diasoft.spring.dto.StudentDto;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class MapperServiceImpl implements MapperService {


    @Override
    public LibraryDto toLibraryDto(Library lib) {
        LibraryDto libraryDto = new LibraryDto();
        libraryDto.setBookId(lib.getBookId());
        libraryDto.setStudentId(lib.getStudentId());
        libraryDto.setId(lib.getId());
        return  libraryDto;
    }

    @Override
    public List<LibraryDto> toLibraryDtoList(List<Library> libraryList) {
        List<LibraryDto> libraryDtos = new ArrayList<>();
        if(libraryList != null) {
            for(Library lib : libraryList) {
                libraryDtos.add(toLibraryDto(lib));
            }
        }
        return libraryDtos;
    }

    @Override
    public StudentDto toStudentDto(Student s) {
        if(s != null) {
            return new StudentDto(s.getId(), s.getName());
        } else {
            return null;
        }
    }
}
