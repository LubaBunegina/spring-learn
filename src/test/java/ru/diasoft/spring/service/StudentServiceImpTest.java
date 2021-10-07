package ru.diasoft.spring.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;



import java.io.IOException;

@DisplayName("Сервис подсчета правильных ответов и анализа результатов")
@RunWith(SpringRunner.class)
@SpringBootTest
class StudentServiceImpTest {

    @MockBean
    ScannerService scanner;

    @MockBean
    LoaderService loaderService;

    @MockBean
    PrintService printService;

    @MockBean
    MessageService messRes;

    @MockBean
    Starter starter;

    StudentServiceImp testingStudent;

    @BeforeEach
    void setUp(){
        testingStudent = new StudentServiceImp(scanner, loaderService, printService, messRes);
    }

    @Test
    @DisplayName("Набрано маленькое количество баллов. Студент НЕ прошел тестирование.")
    void executeWhenTestingStudentFailed() throws Exception {

        Mockito.when(scanner.getCommonScore()).thenReturn(2);
        Mockito.when(loaderService.getTestScore()).thenReturn(5);

        int res = testingStudent.execute();
        Assert.assertEquals(0, res);
    }

    @Test
    @DisplayName("Набрано достаточное количество баллов. Студент прошел тестирование.")
    void executeWhenTestingStudentSuccess() throws IOException {

        Mockito.when(scanner.getCommonScore()).thenReturn(6);
        Mockito.when(loaderService.getTestScore()).thenReturn(5);

        int res = testingStudent.execute();
        Assert.assertEquals(1, res);
    }
}