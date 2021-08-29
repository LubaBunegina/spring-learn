package ru.diasoft.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.junit.jupiter.api.Test;
import ru.diasoft.spring.config.TestConfig;
import ru.diasoft.spring.dao.LearnDao;
import ru.diasoft.spring.entity.AnswerLearn;
import ru.diasoft.spring.entity.TestLearn;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@DisplayName("Сервис по начитке вопросов/ответов из файла")
@ExtendWith(MockitoExtension.class)
public class TestServiceImpTest  {

    Resource resQ;
    Resource resA;

    @Mock
    LearnDao dao;

    @InjectMocks
    TestServiceImp learnService;

    @InjectMocks
    CsvLoaderImp loader;

    @MockBean
    TestConfig config;

   @DisplayName("Тест. Возвращает все вопросы")
   @Test
    public void getAllTest() throws IOException {

       resQ = loader.getResource("question");

       List<TestLearn> testingList = dao.getAllQuestions(resQ);
       List<TestLearn> tList = learnService.getAllQuestions(resQ);

       assertEquals(tList, testingList);
   }

    @DisplayName("Тест. Возвращает все ответы")
    @Test
    public void getAllAnswers() throws IOException {

        resA = loader.getResource("answer");

        List<AnswerLearn> rightAnswerList = dao.getAllAnswers(resA);
        List<AnswerLearn> tList = learnService.getAllAnswers(resA);
        assertEquals(tList, rightAnswerList);
    }
}