package ru.diasoft.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ru.diasoft.spring.config.QuestionConfig;

@Service
@RequiredArgsConstructor
public class LoaderService  {

    private final ResourceLoader resourceLoader;

    private final QuestionConfig config;

    public Resource getResource()
    {
        return resourceLoader.getResource(config.getDir());
    }

    public Integer getTestScore(){
        return config.getTestScore();
    }

}
