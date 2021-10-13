package ru.diasoft.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.diasoft.spring.config.MessageConfig;
import ru.diasoft.spring.entity.TestLearn;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class ScannerServiceImp implements ScannerService{

    private Scanner in;

    private final MessageService messageService;

    private final TestService testService;

    private final PrintService printService;


    @Autowired
    public ScannerServiceImp(MessageConfig props,
                             TestService testService,
                             PrintService printService,
                             MessageService messageService){
        this.testService = testService;
        this.printService = printService;
        this.messageService = messageService;
        in = new Scanner(System.in);
    }

    public String scanStudentName() {
        System.out.println(messageService.getMessage("askName"));
        String name = in.nextLine();
        return name;
    }

    public String scanAnswerOnQuestion(String question, String answers) {

        System.out.println(messageService.getMessage("question") + ": "
                + messageService.getMessage(question));

        System.out.println(messageService.getMessage("answers") + ": "
                + messageService.getMessage(answers));
        String answer = in.nextLine();
        return answer;
    }

    public int getCommonScore() throws IOException {
        List<TestLearn> questionsList = testService.getAllQuestions();
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
