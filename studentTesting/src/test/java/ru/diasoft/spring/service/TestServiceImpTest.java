package ru.diasoft.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.diasoft.spring.dao.LearnDao;
import ru.diasoft.spring.entity.TestLearn;
import org.mockito.Mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@DisplayName("Сервис по начитке вопросов/ответов из файла")
@ExtendWith(SpringExtension.class)
public class TestServiceImpTest  {

    @Mock
    LearnDao dao;

    private TestService learnService;

    @BeforeEach
    void setUp(){
        learnService = new TestServiceImp(dao);
    }

   @DisplayName("Тест. Возвращает все вопросы")
   @Test
    public void getAllTest() throws IOException {

       List<TestLearn> daoList = new ArrayList<>();
       daoList.add(new TestLearn(1,"q", "a", "r"));

       Mockito.when(dao.getAllQuestions()).thenReturn(daoList);
       List<TestLearn> tList = learnService.getAllQuestions();

       assertEquals(tList, daoList);

   }

}