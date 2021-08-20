package ru.diasoft.spring.service;

import ru.diasoft.spring.entity.AnswerLearn;
import ru.diasoft.spring.entity.StudentLearn;
import ru.diasoft.spring.entity.TestLearn;

import java.util.List;

public class TestingStudentServiceImp implements TestingStudentService {

    public TestingStudentServiceImp() {

    }

    public void execute(List<TestLearn> questionsList, List<AnswerLearn> rightAnswersList, int testScore) {

        ScannerTestStudentServiceImp scanner = new ScannerTestStudentServiceImp();
        StudentLearn student = new StudentLearn(scanner.scanStudentName(), 0);
        PrintAnswerServiceImp printer = new PrintAnswerServiceImp();

        for(TestLearn questionItem : questionsList){
            String answer = scanner.scanAnswerOnQuestion(questionItem.getQuestion(), questionItem.getAnswers());
            AnswerLearn studAnsw = new AnswerLearn(questionItem.getNumber(), answer);
            if(studAnsw.isRightAnswer(rightAnswersList)){
                student.score ++;
            }
        }
        printer.printResult(String.valueOf(student.score));
        if(student.score >= testScore){
            printer.printCommonInfo("Congratulations. you passed the test successfully!");
        } else {
            printer.printCommonInfo("Test failed!");
        }
    }
}
