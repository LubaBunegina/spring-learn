package ru.diasoft.spring.service;

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
public class ScannerServiceImp implements ScannerService{

    private Scanner in;
    private final MessageConfig props;

    @Autowired
    TestService testService;

    @Autowired
    PrintService printService;

    @Autowired
    @Qualifier("messageResourceSB")
    MessageSource messRes;

    public ScannerServiceImp(MessageConfig props){
        this.props = props;
        in = new Scanner(System.in);
    }

    public String scanStudentName() {
        System.out.println(messRes.getMessage("askName", null, new Locale(props.getLocale())));
        String name = in.nextLine();
        return name;
    }

    public String scanAnswerOnQuestion(String question, String answers) {

        System.out.println(messRes.getMessage("question", null, new Locale(props.getLocale())) + ": "
                + messRes.getMessage(question, null, new Locale(props.getLocale())));

        System.out.println(messRes.getMessage("answers", null, new Locale(props.getLocale())) + ": "
                + messRes.getMessage(answers, null, new Locale(props.getLocale())));
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
