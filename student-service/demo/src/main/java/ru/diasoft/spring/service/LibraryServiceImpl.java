package ru.diasoft.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diasoft.spring.domain.Library;
import ru.diasoft.spring.dto.LibraryDto;
import ru.diasoft.spring.repository.LibraryRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class LibraryServiceImpl implements  LibraryService {

    private final LibraryRepository libraryRepository;
    private final MapperService mapper;


    @Override
    public List<LibraryDto> getLibraryByBookId(long bookId) {
         return mapper.toLibraryDtoList(
                libraryRepository.findAllByBookId(
                        bookId));
    }

    @Override
    public List<LibraryDto> getLibraryByStudentId(long studentId) {
        return  mapper.toLibraryDtoList(
                libraryRepository.findAllByStudentId(
                        studentId));
    }

    @Override
    public List<LibraryDto> getLibraryByStudentIdAndBookId(long studentId, long bookId) {
        return mapper.toLibraryDtoList(libraryRepository.findAllByBookIdAndAndStudentId(
                bookId, studentId
        ));
    }

    @Override
    public List<LibraryDto> getAllLibrary() {
        return mapper.toLibraryDtoList(libraryRepository.findAll());
    }

    @Override
    public LibraryDto create(LibraryDto dto) {
        List<Library> lib = libraryRepository.findAllByBookId(dto.getBookId());
        if(lib != null && lib.size() > 0) {
            //книгу уже кто-то читает
            return null;
        } else {
            if(dto.getBookId() != 0L && dto.getStudentId() != 0L) {
                Library library = new Library();
                library.setStudentId(dto.getStudentId());
                library.setBookId(dto.getBookId());
                return mapper.toLibraryDto(
                        libraryRepository.save(
                                library));
            }
        }
        return null;
    }

    @Override
    public void deleteById(long id) {
        libraryRepository.deleteById(id);
    }
}
