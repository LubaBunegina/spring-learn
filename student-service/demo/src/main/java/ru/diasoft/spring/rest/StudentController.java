package ru.diasoft.spring.rest;

import org.springframework.web.bind.annotation.*;
import ru.diasoft.spring.domain.Student;
import ru.diasoft.spring.dto.StudentDto;
import ru.diasoft.spring.service.StudentService;


@RestController
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/students")
    StudentDto getStudentByName(@RequestParam String studentName) {
        return service.getStudentByName(studentName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/students/{id}")
    StudentDto getStudentById(@PathVariable long id) {
        return service.getById(id);
    }

}