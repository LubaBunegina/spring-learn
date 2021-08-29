package ru.diasoft.spring.service;

import org.junit.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.junit.Test;
import ru.diasoft.spring.entity.AnswerLearn;
import ru.diasoft.spring.entity.TestLearn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestServiceImpTest  {

    private Resource resQ;
    private Resource resA;
    private CsvLoaderImp loader;
    TestService service;

    @Before
    public void beforeTest(){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TestMain.class);
        service = context.getBean(TestService.class);
        loader = context.getBean(CsvLoaderImp.class);
        resQ = loader.getResource("question");
        resA = loader.getResource("answer");
   }

   @Test
    public void getAllTest() throws IOException {
       List<TestLearn> testingList = new ArrayList<TestLearn>();
       TestLearn t1 = new TestLearn(1,"q1", "a1");
       TestLearn t2 = new TestLearn(2,"q2", "a2");
       TestLearn t3 = new TestLearn(3,"q3", "a3");
       TestLearn t4 = new TestLearn(4,"q4", "a4");
       TestLearn t5 = new TestLearn(5,"q5", "a5");
       testingList.add(t1);
       testingList.add(t2);
       testingList.add(t3);
       testingList.add(t4);
       testingList.add(t5);

       List<TestLearn> tList = service.getAllQuestions(resQ);
       assertEquals(tList, testingList);
   }

    @Test
    public void getAllAnswers() throws IOException {
        List<AnswerLearn> rightAnswerList = new ArrayList<AnswerLearn>();
        AnswerLearn a1 = new AnswerLearn(1,"1");
        AnswerLearn a2 = new AnswerLearn(2,"2");
        AnswerLearn a3 = new AnswerLearn(3,"3");
        AnswerLearn a4 = new AnswerLearn(4,"3");
        AnswerLearn a5 = new AnswerLearn(5,"2");
        rightAnswerList.add(a1);
        rightAnswerList.add(a2);
        rightAnswerList.add(a3);
        rightAnswerList.add(a4);
        rightAnswerList.add(a5);

        List<AnswerLearn> tList = service.getAllAnswers(resA);
        assertEquals(tList, rightAnswerList);
    }
}