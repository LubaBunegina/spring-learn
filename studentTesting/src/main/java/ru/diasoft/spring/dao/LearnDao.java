package ru.diasoft.spring.dao;

import org.springframework.core.io.Resource;
import ru.diasoft.spring.entity.TestLearn;

import java.io.IOException;
import java.util.List;

public interface LearnDao {
    List<TestLearn> getAllQuestions() throws IOException;
}
