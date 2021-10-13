package ru.diasoft.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import ru.diasoft.spring.entity.TestLearn;
import ru.diasoft.spring.service.LoaderService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LearnDaoSimple implements LearnDao {

    @Autowired
    LoaderService res;

    public List<TestLearn> getAllQuestions() throws IOException {
        BufferedReader is =  new BufferedReader(new InputStreamReader(res.getResource().getInputStream()));
        List<TestLearn> testList = new ArrayList<TestLearn>();
        String line;
        int count = 0;
        while((line = is.readLine()) != null){
            List lineArr = Arrays.asList(line.split(";"));
            if(lineArr != null && lineArr.size() > 1){
                TestLearn newTest = new TestLearn( ++ count, (String) lineArr.get(0), (String) lineArr.get(1), (String) lineArr.get(2));
                testList.add(newTest);
            }
        }
        is.close();
        return testList;
    }


}
