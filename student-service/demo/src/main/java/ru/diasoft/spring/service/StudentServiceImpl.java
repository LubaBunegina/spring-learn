package ru.diasoft.spring.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.diasoft.spring.domain.Student;
import ru.diasoft.spring.dto.StudentCommentDto;
import ru.diasoft.spring.dto.StudentDto;
import ru.diasoft.spring.repository.StudentRepository;

@AllArgsConstructor
@Service("student-service")
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final MapperService mapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public StudentDto getStudentByName(String name) {
        return mapper.toStudentDto(repository.findStudentByName(name));
    }

    @Override
    public StudentDto getById(long id) {
        return mapper.toStudentDto(repository.getById(id));
    }

    @Override
    public void insertStudentCommentForBook(StudentCommentDto studentCommentDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        kafkaTemplate.send("BookComment", objectMapper.writeValueAsString(studentCommentDto));
    }
}
