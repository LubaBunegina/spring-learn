package ru.diasoft.spring.service;

import ru.diasoft.spring.domain.Student;
import ru.diasoft.spring.dto.StudentDto;

public interface StudentService {
    StudentDto getStudentByName(String name);
    StudentDto getById(long id);
}
