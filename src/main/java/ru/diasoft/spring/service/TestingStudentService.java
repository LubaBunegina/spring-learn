package ru.diasoft.spring.service;

import ru.diasoft.spring.entity.AnswerLearn;
import ru.diasoft.spring.entity.TestLearn;

import java.util.List;

public interface TestingStudentService {
    void execute(List<TestLearn> questionsList, List<AnswerLearn> rightAnswersList, int testScore);
}
