package ru.diasoft.spring.service;

import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.junit.Test;
import ru.diasoft.spring.entity.TestLearn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestServiceImpTest  {

    private Resource res;
    private CsvLoaderImp loader;
    TestService service;

    @Before
    public void beforeTest(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context-test.xml");
        service = context.getBean(TestService.class);
        loader = context.getBean(CsvLoaderImp.class);
        res = loader.getResource();
   }

   @Test
    public void getAllTest() throws IOException {
       List<TestLearn> testingList = new ArrayList<TestLearn>();
       TestLearn t1 = new TestLearn("q1", "a1");
       TestLearn t2 = new TestLearn("q2", "a2");
       TestLearn t3 = new TestLearn("q3", "a3");
       testingList.add(t1);
       testingList.add(t2);
       testingList.add(t3);

       List<TestLearn> tList = service.getAll(res);
       assertEquals(tList, testingList);
   }
}