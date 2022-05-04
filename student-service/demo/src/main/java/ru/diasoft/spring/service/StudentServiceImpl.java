package ru.diasoft.spring.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diasoft.spring.domain.Student;
import ru.diasoft.spring.dto.StudentDto;
import ru.diasoft.spring.repository.StudentRepository;

@AllArgsConstructor
@Service("student-service")
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final MapperService mapper;

    @Override
    public StudentDto getStudentByName(String name) {
        return mapper.toStudentDto(repository.findStudentByName(name));
    }

    @Override
    public StudentDto getById(long id) {
        return mapper.toStudentDto(repository.getById(id));
    }
}
