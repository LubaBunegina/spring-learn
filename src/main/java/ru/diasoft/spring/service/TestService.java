package ru.diasoft.spring.service;

import org.springframework.core.io.Resource;
import ru.diasoft.spring.entity.AnswerLearn;
import ru.diasoft.spring.entity.TestLearn;

import java.io.IOException;
import java.util.List;

public interface TestService {
    List<TestLearn> getAllQuestions(Resource res) throws IOException;
    List<AnswerLearn> getAllAnswers(Resource res) throws IOException;
}
