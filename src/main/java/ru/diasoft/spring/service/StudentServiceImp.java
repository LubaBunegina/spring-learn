package ru.diasoft.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.diasoft.spring.entity.TestLearn;

import java.io.IOException;
import java.util.List;

@Service
public class TestingStudentServiceImp implements TestingStudentService {

    private ScannerService scanner;

    private LoaderService loaderService;

    private TestService testService;

    private PrintService printService;

    @Autowired
    public TestingStudentServiceImp(ScannerService scanner, LoaderService loaderService, TestService testService,
                                    PrintService printService) {
        this.testService = testService;
        this.scanner = scanner;
        this.loaderService = loaderService;
        this.printService = printService;
    }

    public int getCommonScore() throws IOException {
        List<TestLearn> questionsList = testService.getAllQuestions();
        scanner.scanStudentName();
        int score = 0;
        for(TestLearn questionItem : questionsList){
            String studentAnswer = scanner.scanAnswerOnQuestion(questionItem.getQuestion(), questionItem.getAnswers());
            if(studentAnswer.equals(questionItem.getRightAnswer())){
                score ++;
            }
        }
        printService.printResult(String.valueOf(score));
        return score;
    }

    public int execute() throws IOException {
        int score = getCommonScore();

        if(score >= loaderService.getTestScore()){
            printService.printCommonInfo("Congratulations. you passed the test successfully!");
            return 1;
        } else {
            printService.printCommonInfo("Test failed!");
            return 0;
        }
    }
}
