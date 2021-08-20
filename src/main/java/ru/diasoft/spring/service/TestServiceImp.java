package ru.diasoft.spring.service;

import org.springframework.core.io.Resource;
import ru.diasoft.spring.dao.TestDao;
import ru.diasoft.spring.entity.TestLearn;

import java.io.IOException;
import java.util.List;

public class TestServiceImp implements TestService{
    private final TestDao dao;

    public TestServiceImp(TestDao dao) {
        this.dao = dao;
    }

    public List<TestLearn> getAll(Resource res) throws IOException {
        return dao.getAll(res);
    }
}
