package ru.diasoft.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoaderService  {
    
    private final ResourceLoader resourceLoader;

    @Value("${questions.dir}")
    private String locationQuestions;

    @Value("${questions.testScore}")
    private Integer testScore;

    public Resource getResource()
    {
        return resourceLoader.getResource(locationQuestions);
    }

    public Integer getTestScore(){
        return testScore;
    }

}
