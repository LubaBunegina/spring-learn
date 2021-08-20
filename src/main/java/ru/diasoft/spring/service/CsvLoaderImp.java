package ru.diasoft.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class CsvLoaderImp implements ResourceLoaderAware {
    private ResourceLoader resourceLoader;

    @Value("${questions.dir}")
    private String locationQuestions;

    @Value("${answers.dir}")
    private String locationAnswers;

    @Value("${questions.testScore}")
    private Integer testScore;

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Resource getResource(String type)
    {
        if(type.equals("question")) {
            return resourceLoader.getResource(locationQuestions);
        } else if(type.equals("answer")) {
            return resourceLoader.getResource(locationAnswers);
        }
        return null;
    }

    public Integer getTestScore(){
        return testScore;
    }

}
