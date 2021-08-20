package ru.diasoft.spring.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.diasoft.spring.dao.TestDao;
import ru.diasoft.spring.entity.AnswerLearn;
import ru.diasoft.spring.entity.TestLearn;

import java.io.IOException;
import java.util.List;

public class TestServiceImp implements TestService{
    private final TestDao dao;

    public TestServiceImp(TestDao dao) {
        this.dao = dao;
    }

    public List<TestLearn> getAllQuestions(Resource res) throws IOException {
        return dao.getAllQuestions(res);
    }

    public List<AnswerLearn> getAllAnswers(Resource res) throws IOException {
        return dao.getAllAnswers(res);
    }
}
