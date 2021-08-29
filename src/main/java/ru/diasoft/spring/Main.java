package ru.diasoft.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.Resource;
import ru.diasoft.spring.config.TestConfig;
import ru.diasoft.spring.entity.AnswerLearn;
import ru.diasoft.spring.entity.TestLearn;
import ru.diasoft.spring.service.*;

import java.io.IOException;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "ru.diasoft",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = TestConfig.class))
public class Main {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);
        TestService service = context.getBean(TestService.class);

        CsvLoaderImp loader = context.getBean(CsvLoaderImp.class);
        Resource resQues = loader.getResource("question");
        Resource resAns = loader.getResource("answer");
        int testScore = loader.getTestScore();

        List<TestLearn> testList = service.getAllQuestions(resQues);
        List<AnswerLearn> rightAnswerList = service.getAllAnswers(resAns);

        TestingStudentService testing = context.getBean(TestingStudentService.class);
        testing.execute(testList, rightAnswerList, testScore);

        context.close();
    }
}
