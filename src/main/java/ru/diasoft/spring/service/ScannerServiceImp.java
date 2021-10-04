package ru.diasoft.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.diasoft.spring.entity.TestLearn;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Service
public class ScannerServiceImp implements ScannerService{

    private Scanner in;

    @Autowired
    TestService testService;

    @Autowired
    PrintService printService;

    public ScannerServiceImp(){
        in = new Scanner(System.in);
    }

    public String scanStudentName() {
        System.out.println("Enter your name: ");
        String name = in.nextLine();
        return name;
    }

    public String scanAnswerOnQuestion(String question, String answers) {
        System.out.println("Question: " + question);
        System.out.println("Answers: " + answers);
        String answer = in.nextLine();
        return answer;
    }

    public int getCommonScore() throws IOException {
        List<TestLearn> questionsList = testService.getAllQuestions();
        scanStudentName();
        int score = 0;
        for(TestLearn questionItem : questionsList){
            String studentAnswer = scanAnswerOnQuestion(questionItem.getQuestion(), questionItem.getAnswers());
            if(studentAnswer.equals(questionItem.getRightAnswer())){
                score ++;
            }
        }
        printService.printResult(String.valueOf(score));
        return score;
    }
}
