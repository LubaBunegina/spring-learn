package ru.diasoft.spring.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.diasoft.spring.dao.LearnDao;
import ru.diasoft.spring.entity.TestLearn;

import java.io.IOException;
import java.util.List;

@Service
public class TestServiceImp implements TestService{
    private final LearnDao dao;

    public TestServiceImp(LearnDao dao) {
        this.dao = dao;
    }

    public List<TestLearn> getAllQuestions() throws IOException {
        return dao.getAllQuestions();
    }

}
