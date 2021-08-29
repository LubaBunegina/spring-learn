package ru.diasoft.spring.service;

import org.springframework.core.io.Resource;
import ru.diasoft.spring.dao.LearnDao;
import ru.diasoft.spring.entity.AnswerLearn;
import ru.diasoft.spring.entity.TestLearn;

import java.io.IOException;
import java.util.List;

public class TestServiceImp implements TestService{
    private final LearnDao dao;

    public TestServiceImp(LearnDao dao) {
        this.dao = dao;
    }

    public List<TestLearn> getAllQuestions(Resource res) throws IOException {
        return dao.getAllQuestions(res);
    }

    public List<AnswerLearn> getAllAnswers(Resource res) throws IOException {
        return dao.getAllAnswers(res);
    }
}
