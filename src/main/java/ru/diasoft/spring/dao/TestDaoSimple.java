package ru.diasoft.spring.dao;

import org.springframework.core.io.Resource;
import ru.diasoft.spring.entity.AnswerLearn;
import ru.diasoft.spring.entity.TestLearn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDaoSimple implements TestDao {

    public List<TestLearn> getAllQuestions(Resource res) throws IOException {
        BufferedReader is =  new BufferedReader(new InputStreamReader(res.getInputStream()));
        List<TestLearn> testList = new ArrayList<TestLearn>();
        String line;
        int count = 0;
        while((line = is.readLine()) != null){
            List lineArr = Arrays.asList(line.split(";"));
            if(lineArr != null && lineArr.size() > 1){
                TestLearn newTest = new TestLearn( ++ count, (String) lineArr.get(0), (String) lineArr.get(1));
                testList.add(newTest);
            }
        }
        is.close();
        return testList;
    }

    public List<AnswerLearn> getAllAnswers(Resource res) throws IOException {
        BufferedReader is =  new BufferedReader(new InputStreamReader(res.getInputStream()));
        List<AnswerLearn> answersList = new ArrayList<AnswerLearn>();
        String line;
        int count = 0;
        while((line = is.readLine()) != null){
            List lineArr = Arrays.asList(line.split(";"));
            if(lineArr != null && lineArr.size() > 1){
                for(int i = 0; i < lineArr.size(); i ++) {
                    AnswerLearn newAns = new AnswerLearn(++count, (String) lineArr.get(i));
                    answersList.add(newAns);
                }
            }
        }
        is.close();
        return answersList;
    }

}
