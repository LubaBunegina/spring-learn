package ru.diasoft.spring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.diasoft.spring.domain.Student;
import ru.diasoft.spring.dto.StudentCommentDto;
import ru.diasoft.spring.dto.StudentDto;

public interface StudentService {
    StudentDto getStudentByName(String name);
    StudentDto getById(long id);
    void insertStudentCommentForBook(StudentCommentDto studentCommentDto) throws JsonProcessingException;
}
